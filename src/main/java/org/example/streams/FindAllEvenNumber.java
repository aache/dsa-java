package org.example.streams;

import java.util.Arrays;

public class FindAllEvenNumber {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8};

        arr = Arrays.stream(arr).filter(num -> num % 2 == 0).toArray();
        Arrays.stream(arr).forEach(System.out::print);
    }
}
