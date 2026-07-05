package org.example;

import java.util.HashMap;
import java.util.Map;

public class LongestSubArrayWithSumK {
    public int longestSubarray(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int prefixSum = 0;
        int longest = 0;
        for(int i = 0; i < arr.length ; i++){
            prefixSum = prefixSum + arr[i];
            map.putIfAbsent(prefixSum, i);
            if(map.containsKey(prefixSum - k)){
                longest = Math.max(longest, i - map.get(prefixSum - k));
            }
        }

        return longest;
    }
}
