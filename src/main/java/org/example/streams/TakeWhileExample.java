package org.example.streams;

import java.util.List;

public class TakeWhileExample {
    public static void main(String[] args) {
        List<Integer> list = List.of(1,2,3,10,4,5);
        System.out.println("====Take While ====");
        list.stream()
                .takeWhile(n -> n < 5)
                .forEach(System.out::println);

        System.out.println("===== Filter ====");

        list.stream()
                .filter(n -> n < 5)
                .forEach(System.out::println);
    }
}
