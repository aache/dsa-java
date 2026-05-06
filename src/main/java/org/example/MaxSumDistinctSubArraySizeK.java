package org.example;

import java.util.HashMap;
import java.util.Map;

public class MaxSumDistinctSubArraySizeK {

    public static void main(String[] args) {
       int result = new MaxSumDistinctSubArraySizeK().maxSum(new int[] {1,2,3,4,5,6,7,8}, 3);
        System.out.println(result);
    }

    public int maxSum(int[] arr, int k) {
        int left = 0;
        int sum = 0;
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int right = 0; right < arr.length; right++) {

            sum = sum + arr[right];
            map.put(arr[right], map.getOrDefault(arr[right], 0) + 1);
            if (right - left + 1 > k) {
                sum = sum - arr[left];
                map.put(arr[left], map.get(arr[left]) - 1);
                if (map.get(arr[left]) == 0) {
                    map.remove(arr[left]);
                }

                left++;
            }

            if (right - left + 1 == k && map.size() == k) {
                max = Math.max(sum, max);
            }
        }
        return max;
    }
}


// 6
// 9
// 12
// 15
// 18
// 21