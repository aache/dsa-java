package org.example;
import java.lang.management.ManagementFactory;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class FakeProductionApp {

    private static final List<byte[]> MEMORY_LEAK = new ArrayList<>();
    private static final ExecutorService executor =
            Executors.newFixedThreadPool(10);

    private static final AtomicLong requestCounter = new AtomicLong();

    public static void main(String[] args) throws Exception {

        String pid = ManagementFactory.getRuntimeMXBean()
                .getName()
                .split("@")[0];

        System.out.println("====================================");
        System.out.println(" Fake Production Java App Started");
        System.out.println(" PID: " + pid);
        System.out.println("====================================");

        startCpuLoad();
        startMemoryLeak();
        startDeadlock();
        startRequestSimulator();
        startScheduler();

        // Keep application alive forever
        Thread.currentThread().join();
    }

    // Simulates API traffic
    private static void startRequestSimulator() {
        Runnable requestTask = () -> {
            Random random = new Random();

            while (true) {
                try {
                    long requestId = requestCounter.incrementAndGet();

                    Map<String, Object> request = new HashMap<>();
                    request.put("id", requestId);
                    request.put("timestamp", LocalDateTime.now());
                    request.put("payload", UUID.randomUUID().toString());

                    Thread.sleep(random.nextInt(500));

                    if (requestId % 100 == 0) {
                        System.out.println("Processed requests: " + requestId);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 5; i++) {
            executor.submit(requestTask);
        }
    }

    // Simulates memory leak
    private static void startMemoryLeak() {

        Thread t = new Thread(() -> {
            while (true) {
                try {
                    byte[] data = new byte[1024 * 1024]; // 1MB
                    MEMORY_LEAK.add(data);

                    System.out.println("Memory objects: " + MEMORY_LEAK.size());

                    Thread.sleep(2000);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        t.setName("memory-leak-thread");
        t.start();
    }

    // Simulates CPU spike
    private static void startCpuLoad() {

        Runnable cpuTask = () -> {

            while (true) {
                double value = Math.sqrt(Math.random() * 999999);

                if (value > 99999999) {
                    System.out.println(value);
                }
            }
        };

        for (int i = 0; i < 4; i++) {
            Thread t = new Thread(cpuTask);
            t.setName("cpu-worker-" + i);
            t.start();
        }
    }

    // Simulates deadlock
    private static void startDeadlock() {

        Object lock1 = new Object();
        Object lock2 = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lock1) {

                sleep(100);

                synchronized (lock2) {
                    System.out.println("Thread1 acquired locks");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {

                sleep(100);

                synchronized (lock1) {
                    System.out.println("Thread2 acquired locks");
                }
            }
        });

        t1.setName("deadlock-thread-1");
        t2.setName("deadlock-thread-2");

        t1.start();
        t2.start();
    }

    // Scheduled background job
    private static void startScheduler() {

        ScheduledExecutorService scheduler =
                Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {

            System.out.println(
                    "[SCHEDULER] Running cleanup job at "
                            + LocalDateTime.now());

            List<String> data = new ArrayList<>();

            for (int i = 0; i < 100000; i++) {
                data.add(UUID.randomUUID().toString());
            }

        }, 5, 10, TimeUnit.SECONDS);
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}
