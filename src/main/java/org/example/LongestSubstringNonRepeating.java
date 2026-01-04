package org.example;

import java.util.HashSet;

public class LongestSubstringNonRepeating {
    public static void main(String[] args) {
        System.out.println(
                new LongestSubstringNonRepeating().longestSubstring("ababacadacabc")
        );
    }

    public int longestSubstring(String s) {
        int max = 0;
        int left = 0;
        HashSet<Character> set = new HashSet<>();
        for(int right = 0; right < s.length() ; right++){
           while(set.contains(s.charAt(right))){
               set.remove(s.charAt(left));
               left++;
           }
           set.add(s.charAt(right));
           int length = right - left + 1;
           if(length > max){
               max = length;
           }
        }
        return max;
    }
}
