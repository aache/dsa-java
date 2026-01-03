package org.example;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

record Job (String id, int duration){}

class Worker implements Runnable {
    int workerId;
    List<Job> assignedJobs;

    Worker(int workerId, List<Job> assignedJobs) {
        this.workerId = workerId;
        this.assignedJobs = assignedJobs;
    }

    @Override
    public void run() {
        System.out.println("Worker " + workerId + " starting with " + assignedJobs.size() + " jobs.");
        for (Job job : assignedJobs) {
            try {
                System.out.println("Worker " + workerId + " running job " + job.id() + " (" + job.duration() + " ms)");
                Thread.sleep(job.duration());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Worker " + workerId + " finished.");
    }
}

public class LPTScheduleDemo {
    public static void main(String[] args) {
        // Example jobs
        List<Job> jobs = Arrays.asList(
                new Job("A", 2000),
                new Job("B", 3000),
                new Job("C", 5000),
                new Job("D", 1500),
                new Job("E", 2500)
        );

        int workerCount = 3;
        Map<Integer, List<Job>> assignments = lptSchedule(jobs, workerCount);

        // Start worker threads
        ExecutorService executor = Executors.newFixedThreadPool(workerCount);
        for (int wid = 0; wid < workerCount; wid++) {
            executor.submit(new Worker(wid, assignments.get(wid)));
        }

        executor.shutdown();
    }

    // ---------------- LPT Scheduler -----------------
    public static Map<Integer, List<Job>> lptSchedule(List<Job> jobs, int m) {
        // Sort jobs by duration descending (LPT)
        jobs.sort((a, b) -> b.duration() - a.duration());

        // PriorityQueue of (currentLoad, workerId)
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        for (int i = 0; i < m; i++) {
            heap.offer(new int[]{0, i}); // load, workerId
        }

        Map<Integer, List<Job>> assignments = new HashMap<>();
        for (int i = 0; i < m; i++) assignments.put(i, new ArrayList<>());

        for (Job job : jobs) {
            int[] top = heap.poll();
            int load = top[0];
            int wid = top[1];

            assignments.get(wid).add(job);

            int newLoad = load + job.duration();
            heap.offer(new int[]{newLoad, wid});
        }
        System.out.println("Assignments : " + assignments);
        return assignments;
    }
}
