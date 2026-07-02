package org.example;

import java.util.HashMap;
import java.util.HashSet;

public class LongestSubstringExactlyKUniqueCharacter {
    public static void main(String[] args) {
        int result = new LongestSubstringExactlyKUniqueCharacter().longestSubstringWithoutRepeat("aabacbebebe",3);
        System.out.println(result);
    }

    public Integer longestSubstringWithoutRepeat(String s, int k) {
        HashMap<Character, Integer> map = new HashMap<>();
        int i = 0;
        int j = 0;
        int max = -1;
        while (j  < s.length()) {
            map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0) + 1);
            while(map.size() > k){
                if(map.get(s.charAt(i))>1){
                    map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
                } else {
                    map.remove(s.charAt(i));
                }
                i++;
            }

            if(map.size() == k){
                max = Math.max(max, j-i + 1);
            }
            j++;
        }
        return max;
    }
}
