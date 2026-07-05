package org.example;

public class MaxConsecutiveOnesWithKFlips {

    public static void main(String[] args) {
        int result = new MaxConsecutiveOnesWithKFlips()
                            .findMaxConsecutiveOnes(new int[] {1,1,1,0,0,0,1,1,1,1,0}, 2);
        System.out.println(result);
    }

    public int findMaxConsecutiveOnes(int[] nums, int k) {

        int i = 0;
        int j = 0;
        int maxCount = 0;
        int flips = k;
        while (j < nums.length) {

            if(nums[j] == 0) {
                while (flips == 0) {
                    if(nums[i] == 0){
                        flips++;
                    }
                    i++;
                }
                flips--;
            }
            maxCount = Math.max(maxCount, j - i + 1);
            j++;
        }
        return maxCount;
    }
}
