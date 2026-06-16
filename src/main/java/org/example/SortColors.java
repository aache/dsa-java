package org.example;

import java.util.Arrays;

public class SortColors {

    public static void main(String[] args) {
        new SortColors().sortColors(new int[] {2,0,0,1,2,1,0,2});
    }

    public void sortColors(int[] nums) {
        int middle = 0;
        int left = 0;
        int right = nums.length - 1;
        while (middle <= right) {
            if(nums[middle] == 2){
                swap(nums, middle, right);
                right--;
            }
            else if(nums[middle] == 1){
                middle++;
            }
            else{
                    swap(nums, left, middle);
                left++;
                middle++;
            }
        }
        Arrays.stream(nums).forEach(System.out::print);
    }

    public void swap(int[] arr, int pos1, int pos2) {
        int temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }
}
