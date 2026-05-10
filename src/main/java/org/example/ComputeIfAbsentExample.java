package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ComputeIfAbsentExample {
    public static void main(String[] args) {
        String s = "The system processes data by collecting data, validating data, transforming data, storing data, analyzing data, monitoring data, securing data, optimizing data, transmitting data, archiving data, recovering data, synchronizing data, indexing data, aggregating data, visualizing data, auditing data, encrypting data, backing up data, scaling data, and reporting data across distributed systems, cloud systems, enterprise systems, financial systems, healthcare systems, logistics systems, monitoring systems, security systems, analytics systems, and customer systems while ensuring performance, reliability, scalability, availability, maintainability, observability, flexibility, interoperability, consistency, and stability in modern applications.";
        s = s.replaceAll(",", "");
        s = s.replaceAll("\\.", "");
        System.out.println("Sentence : " + s);
        Map<String, List<Integer>> wordIndex = new HashMap<>();
        String[] words = s.split("\\s+");
        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            wordIndex.computeIfAbsent(word, k -> new ArrayList<>()).add(i);
        }
        System.out.println("Word Indices : " + wordIndex);
        Map<String, Integer> wordCount = new LinkedHashMap<>();
        for(String key : wordIndex.keySet()) {
            wordCount.put(key, wordIndex.get(key).size());
        }
        System.out.println("Word Count : " + wordCount);


    }
}
