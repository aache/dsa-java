package org.example.streams;

import java.util.Arrays;
import java.util.List;

public class SumOfNumbers {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        double sum = numbers.stream().mapToDouble(n -> n).sum();
        System.out.println(sum);
    }
}
