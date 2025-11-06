package org.example;

import java.util.stream.Collectors;

public class RemoveVowels {
    public String removeVowels(String str){
        String result = str.chars().mapToObj(c -> (char) c)
                .filter(x -> !"aeiouAEIOU".contains(x.toString()))
                .map(String::valueOf)
                .collect(Collectors.joining());
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new RemoveVowels().removeVowels("Aaqib"));
    }
}
