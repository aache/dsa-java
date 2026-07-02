package org.example;

import java.util.HashSet;

public class LongestSubstringAtMostKRepeatingCharacter {
    public static void main(String[] args) {
        int result = new LongestSubstringAtMostKRepeatingCharacter().longestSubstringWithoutRepeat("aabacbebebe",3);
        System.out.println(result);
    }

    public Integer longestSubstringWithoutRepeat(String s, int k) {
        HashSet<Character> set = new HashSet<>();
        int i = 0;
        int j = 0;
        int max = 0;
        while (j  < s.length()) {
            while(set.size() >= k) {
                set.remove(s.charAt(i));
                i++;
            }
            set.add(s.charAt(j));
            //System.out.println(s.substring(i, j));
            max = Math.max(max, j - i + 1);
            j++;
        }
        return max;
    }
}
