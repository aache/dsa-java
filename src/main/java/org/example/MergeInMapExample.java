package org.example;

import java.util.HashMap;
import java.util.Map;

public class MergeInMapExample {
    public static void main(String[] args) {
        String s = "hello";
        Map<Character, Integer>  map = new HashMap<>();
        for(Character c : s.toCharArray()){
            map.merge(c, 1, Integer::sum);
        }
        System.out.println(map);
    }

}
