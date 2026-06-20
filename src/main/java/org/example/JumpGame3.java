package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class JumpGame3 {

    public static void main(String[] args) {
        boolean result = new JumpGame3().canReach(new int[]{4,2,3,0,3,1,2}, 5);
        System.out.println(result);
    }

    public boolean canReach(int[] arr, int start) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        boolean result = false;
        while(!queue.isEmpty()){
            int element = queue.poll();
            if(arr[element] == 0){
                result = true;
                break;
            }

            if(arr[element] < 0){
                continue;
            }

            if(element + arr[element] < arr.length){
                queue.add(element + arr[element]);
            }
            if(element - arr[element] >= 0){
                queue.add(element - arr[element]);
            }

            arr[element] = -arr[element];
        }

        return result;
    }
}