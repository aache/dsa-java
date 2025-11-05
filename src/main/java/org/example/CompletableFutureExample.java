package org.example;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {
    public static void main(String[] args) {
        create(4)
                .thenApply(data -> data + 1)
                .thenAccept(System.out::println);
        System.out.println("Started the computation");
    }

    public static int compute(int n) {
        for( int i = 0; i < 100000000 ; i++){}
        return n * 2;
    }

    public static CompletableFuture<Integer> create(int n) {
        return CompletableFuture.supplyAsync(() -> compute(n));
    }
}
