package org.example;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapSorting {
    public static void main(String[] args) {

        Map<String, Integer> map = Map.of("B", 10, "C", 30 , "A", 20 );
        LinkedHashMap<String, Integer> linkedHashMap = map.entrySet().stream().sorted(Map.Entry.<String,Integer> comparingByValue().reversed()).collect(Collectors.toMap( Map.Entry::getKey,
                Map.Entry::getValue,
                (a, b) -> a,
                LinkedHashMap::new));
        System.out.println(linkedHashMap);

       LinkedHashMap<String, Integer> linkedHashMap1 = map.entrySet().stream().
               sorted(Map.Entry.<String, Integer>comparingByKey().reversed()).
               collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a,b) -> a, LinkedHashMap::new));
        System.out.println(linkedHashMap1);
    }
}
