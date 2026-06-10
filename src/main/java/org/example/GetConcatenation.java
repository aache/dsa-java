package org.example;

import java.util.Arrays;

public class GetConcatenation {
    public int[] getConcatenation(int[] nums) {
        int[] result = new int[nums.length*2];
        int i = 0;
        for(i = 0; i < nums.length ; i++){
            result[i] = nums[i];
        }

        for(i = nums.length ; i < nums.length * 2 ; i++){
            result[i] = nums[i - nums.length];
        }

        return result;
    }

    public static void main(String[] args) {
        GetConcatenation getConcatenation = new GetConcatenation();
        Arrays.stream(getConcatenation.getConcatenation(new int[]{1,2,3,4,5})).forEach(System.out::println);
    }
}
