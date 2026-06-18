package org.example.streams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OccurrenceOfEachWord {
    public static void main(String[] args) {

        List<String> words = List.of("java", "spring", "java", "aws");

        Map<String, Long> occurrenceOfEachWord = words.stream().collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));

        System.out.println(occurrenceOfEachWord);

        // Find Duplicate
        List<Integer> nums = List.of(1,2,3,2,4,5,3);

        List<Integer> repeated = nums.stream()
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting())).entrySet().stream().filter(e -> e.getValue() > 1).map(Map.Entry::getKey).toList();
        System.out.println(repeated);
    }
}