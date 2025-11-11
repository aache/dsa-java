package org.example;

public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0;
        int count = 0;
        for(int j = 0; j < nums.length; j++){
            if(nums[j] == 0){
                count = 0;
            } else {
                count++;
            }
            maxCount = Math.max(count, maxCount);
        }

        return maxCount;
    }

    public static void main(String[] args) {
        System.out.println(new MaxConsecutiveOnes().findMaxConsecutiveOnes(new int[]{1,1,0,1,1,1}));
    }
}
