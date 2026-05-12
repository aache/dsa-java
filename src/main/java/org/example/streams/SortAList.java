package org.example.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class SortAList {
    public static void main(String[] args) {
        List<Integer> list = List.of(3,2,4,1,6,7,5);
        //list.stream().sorted().forEach(System.out::println);
        //list.parallelStream().sorted().forEach(System.out::println); --> Allowed but incorrect result
        //list.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        //list.stream().sorted(Comparator.naturalOrder()).forEach(System.out::println);
        //list.stream().sorted(Comparator.comparing(Integer::intValue)).forEach(System.out::println);
    }
}
