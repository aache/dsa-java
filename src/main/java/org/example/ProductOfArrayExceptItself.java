package org.example;

import java.util.Arrays;
/***
 * This is not an efficient solution since the time complexity is O(square of n)
 */
public class ProductOfArrayExceptItself {
    public static void main(String[] args) {
        Arrays.stream(new ProductOfArrayExceptItself().productArray(new int[]{1,2,3,4})).forEach(System.out::println);
    }

    public int[] productArray(int arr[]) {
        int[] resultArray = new int[arr.length];
        for(int i = 0; i < arr.length ; i++){
            int start = i + 1;
            int n = arr.length;
            int product = 1;
            start = ((start % n) + n) % n;
            for (int k = 0; k < n - 1; k++) {
                int idx = (start + k) % n;
                product = product * arr[idx];
            }
            resultArray[i] = product;
        }

        return resultArray;
    }


}
