package org.example;

public class FirstNonRepeatingCharacterInString {

    public static void main(String[] args) {
        String input = "swiss";
        input.chars().mapToObj(i -> (char)i)
                .filter(c -> input.indexOf(c) == input.lastIndexOf(c)) // Java Provides indexOf and lastIndexOf
                .findFirst()
                .ifPresent(System.out::println);
    }

}
