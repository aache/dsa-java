package org.example;
//https://leetcode.com/problems/word-pattern/

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordPattern {
    public static void main(String[] args) {
        System.out.println(new WordPattern().wordPattern("abba", "dog dog dog dog"));
    }

    public boolean wordPattern(String pattern, String s) {
        List<String> list = Arrays.stream(s.split(" ")).toList();
        List<String> patternChars = pattern.chars().mapToObj(c -> Character.valueOf((char)c).toString()).toList();
        Map<String, String> matcher = new HashMap<>();
        if(list.size() != patternChars.size()){
            return false;
        }
        if(list.stream().distinct().toList().size() != patternChars.stream().distinct().toList().size()){
            return false;
        }
        //patternChars.forEach(System.out::println);
        for(int i = 0 ; i < patternChars.size() ; i++){
            if(!matcher.containsKey(patternChars.get(i))){
                matcher.put(patternChars.get(i), list.get(i));
            } else {
                if(!matcher.get(patternChars.get(i)).equals(list.get(i))){
                    return false;
                }
            }
        }
        return true;
    }
}
