package org.example;

import java.util.Arrays;

public class CandyProblem {

    public static void main(String[] args) {
      int result =  new CandyProblem().candy(new int[]{1,0,2});
        System.out.println(result);
    }

    public int candy(int[] ratings) {

        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);

        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = Math.max(candies[i], candies[i - 1] + 1);
            }
        }

        for (int j = n - 2; j >= 0; j--) {
            if (ratings[j] > ratings[j + 1]) {
                candies[j] = Math.max(candies[j], candies[j + 1] + 1);
            }
        }

        return Arrays.stream(candies).sum();
    }
}
