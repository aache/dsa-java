package org.example;

public class MaximumSubArray {
    public static void main(String[] args) {
        System.out.println(new MaximumSubArray().maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
        public int maxSubArray(int[] nums) {
            int maxSum = nums[0];
            int currSum = 0;
            for(int num : nums){
                if(currSum < 0){
                    currSum = 0;
                }
                currSum = currSum + num;
                maxSum = Math.max(currSum, maxSum);
            }
            return maxSum;
        }
}
