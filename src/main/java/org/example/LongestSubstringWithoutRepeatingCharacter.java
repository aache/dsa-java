package org.example;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacter {
    public static void main(String[] args) {
        int result = new LongestSubstringWithoutRepeatingCharacter().longestSubstringWithoutRepeat("aabbcc");
        System.out.println(result);
    }

    public Integer longestSubstringWithoutRepeat(String s) {
        Set<Character> set = new HashSet<>();
        // Your code goes here
        if (s.isEmpty()) return 0;
        int left = 0;
        int right = 0;
        int maxLength = 0;
        while (right < s.length()) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            maxLength = Math.max(right - left + 1, maxLength);
            right++;
        }
            return maxLength;
        }

}
