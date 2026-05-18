package org.example.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SumOfNumbers {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        double sum = numbers.stream().mapToDouble(n -> n).sum();
        System.out.println(sum);

        // Group String by length
        List<String> words = Arrays.asList("Java", "Stream", "API", "Code", "Fun");
        Map<Integer, List<String>> map = words.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(map);

        String concatenated = words.stream().reduce("", (a,b) -> a + " "+ b).trim();
        System.out.println(concatenated);

        String longest = words.stream().reduce((a, b) -> a.length() > b.length() ? a : b).orElse("");
        System.out.println(longest);

        //Frequency of characters in a string
        String s = "success";
        Map<Character, Long> map1 =  s.chars().mapToObj(i -> (char)i).collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        System.out.println(map1);
    }
}
