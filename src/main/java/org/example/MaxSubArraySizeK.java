package org.example;

public class MaxSubArraySizeK {

    public static void main(String[] args) {
        int result = new MaximumSubArrayWithSizeK().maxSubarrayWithSizeK(new int[]{100, 200, 300, 400}, 2);
        System.out.println(result);
    }
    public int maxSubarraySum(int[] arr, int k) {
        // Code here

        if (arr == null || k <= 0 || k > arr.length) {
            return 0; // or throw IllegalArgumentException
        }
        int i = 0;
        int j = i + k;
        int sum = 0;
        while (i < j) {
            sum += arr[i];
            i++;
        }
        int maxSum = sum;
        i = 0; // Resetting i
        while(j < arr.length){
            sum += arr[j];
            sum -= arr[i];
            i++;
            j++;
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }
}
