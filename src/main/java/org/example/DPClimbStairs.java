package org.example;

import java.util.HashMap;
import java.util.Map;

public class DPClimbStairs {

    Map<Integer, Long> map = new HashMap<>();
    public long climbStairs(int n) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        if(n == 1) return 1;
        if(n == 2) return 2;
        long result = climbStairs(n-1) + climbStairs(n-2);
        map.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        DPClimbStairs dp = new DPClimbStairs();

        System.out.println(dp.climbStairs(1000));
        System.out.println(dp.map);
    }

}
