package org.example;
class JMMExample {
    private static boolean flag = false;
    private static int counter = 0;

    public static void main(String[] args) throws Exception {
        Thread writer = new Thread(() -> {
            counter = 42;
            flag = true; // volatile write
        });

        Thread reader = new Thread(() -> {
            while (!flag) {}       // volatile read
            System.out.println(counter); // guaranteed to see 42
        });

        writer.start();
        reader.start();
        writer.join();
        reader.join();
    }
}