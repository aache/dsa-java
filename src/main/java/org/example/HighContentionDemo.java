package org.example;

public class HighContentionDemo {

    private static long counter = 0;

//    public static synchronized void increment() { // synchroniation causes contention
//        counter++;
//    }

    public static void increment() { //gives varying output
        counter++;
    }

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        Thread[] threads = new Thread[100];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1_000_000; j++) {
                    increment();
                }
            });
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();


        System.out.println("Counter =  " + counter);
        System.out.println("Time = " +
                (System.currentTimeMillis() - start) + " ms");
    }
}