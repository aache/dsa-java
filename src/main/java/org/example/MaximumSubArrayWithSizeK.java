package org.example;

public class MaximumSubArrayWithSizeK {

    public static void main(String[] args) {
       int result = new MaximumSubArrayWithSizeK().maxSubarrayWithSizeK(new int[] {1,4,1,10,25,3,5,0,6}, 4);
        System.out.println(result);
        result = new MaximumSubArrayWithSizeK().maxSubarrayWithSizeK(new int[] {1,4,1,10,25,3,5,0,6}, 1);
        System.out.println(result);
    }

    public int maxSubarrayWithSizeK(int[] num, int k) {

        int maxSum = Integer.MIN_VALUE;
        for(int i = 0; i < num.length - k; i++){
            int currSum = 0;
            for(int j = i; j < i + k; j++){
                currSum = currSum + num[j];
            }
            maxSum = Math.max(currSum, maxSum);
        }
        return maxSum;
    }
}
