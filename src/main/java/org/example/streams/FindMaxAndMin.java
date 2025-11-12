package org.example.streams;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindMaxAndMin {
    public static void main(String[] args) {
        List<Integer> integers = List.of(5,3,100,1067,43,11,342);
        int max = integers.stream().max(Integer::compareTo).get();
        int min = integers.stream().min(Integer::compareTo).get();

        System.out.println("Max :: " + max);
        System.out.println("Min :: " + min);
    }
}
