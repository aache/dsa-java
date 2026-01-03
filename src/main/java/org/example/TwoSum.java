package org.example;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {

    }

    public int[] twoSum(int[] arr, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        int[] response = new int[2];
        for(int i = 0; i < arr.length ; i++){
            int compliment = target - arr[i];
            if(map.containsKey(compliment)){
                response[0] = i;
                response[1] = map.get(compliment);
            } else {
                map.put(arr[i], i);
            }
        }
        return response;
    }
}
