package org.example;

public class MaximumLengthOfRepeatedSubarray {
    public static void main(String[] args) {

    }

    public int findLength(int[] nums1, int[] nums2) {
        int[][] matrix = new int[nums1.length + 1][nums2.length + 1];
        int max = 0;
        for(int i = 1; i <= nums1.length; i++){
            for(int j = 1; j <= nums2.length; j++){
                if(nums1[i - 1] == nums2[j - 1]){
                    int temp = matrix[i-1][j-1] + 1;
                    matrix[i][j] = temp;
                    max = Math.max(max, matrix[i][j]);
                }
            }
        }

        return max;
    }
}
