package org.example;

import java.util.Arrays;

public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        // Use two pointers to solve
        // i -- moves when there is a swap
        // j -- always moves ahead
        // nums[i] swap with nums[j] where nums[j] gives a non-zero value
        int i = 0;
        for(int j = 0; j < nums.length; j++){
            if(nums[j] != 0){
                int c = nums[i];
                nums[i] = nums[j];
                nums[j] = c;
                i++;
            }
        }
        System.out.println("---------------------------");
        Arrays.stream(nums).forEach(System.out::println);
    }

    public static void main(String[] args) {
        new MoveZeroes().moveZeroes(new int[]{0,1,0,3,12});
        new MoveZeroes().moveZeroes(new int[]{1,0});
        new MoveZeroes().moveZeroes(new int[]{0,1});
        new MoveZeroes().moveZeroes(new int[]{1});
    }
}
