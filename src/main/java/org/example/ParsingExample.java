package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class ParsingExample {
    public static void main(String[] args) {
        List<String> inputs = Arrays.asList("42", "", "100 ", null, "abc", "75");
        List<Integer> output = inputs.stream().flatMap(Stream::ofNullable).map(String::trim).map(ParsingExample::safeParseInt).flatMap(Optional::stream).toList();
        System.out.println(output);
    }

    public static Optional<Integer> safeParseInt(String s){
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (Exception e){
            return Optional.empty();
        }
    }
}
