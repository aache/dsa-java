package org.example;

import java.util.Arrays;

// https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
public class MaxPointsFromCards {
    public static void main(String[] args) {

    }

    public int maxScore(int[] cardPoints, int k) {

        if(cardPoints.length == k){
            return Arrays.stream(cardPoints).sum();
        }

        int total = 0;
        for(int i = 0 ; i < k ; i++){
            total = total + cardPoints[i];
        }

        int max = total;
        for(int i = 0 ; i < k ; i++){
            total = total - cardPoints[k - i - 1] + cardPoints[cardPoints.length - i - 1];
            max = Math.max(max, total);
        }

        return max;
    }
}
