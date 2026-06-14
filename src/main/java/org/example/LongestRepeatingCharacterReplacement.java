package org.example;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharacterReplacement {

    public static void main(String[] args) {
        new LongestRepeatingCharacterReplacement().characterReplacement("AABABCC", 2);
    }

    public int characterReplacement(String s, int k) {

        Map<Character, Integer> charFrequency = new HashMap<>();

        // max window when changes allowed within k range
        int maxWindow = 0;
        int left = 0;
        int right = 0;

        while(right < s.length()){
            charFrequency.put(s.charAt(right), charFrequency.getOrDefault(s.charAt(right), 0) + 1);
            int maxCharFreq = Collections.max(charFrequency.values());
            int char_to_be_replaced =  (right - left + 1) - maxCharFreq;
            System.out.println(s.substring(left,right));
            while(char_to_be_replaced > k){
                charFrequency.put(s.charAt(left), charFrequency.get(s.charAt(left)) - 1);
                left++;
                maxCharFreq = Collections.max(charFrequency.values());
                char_to_be_replaced = (right - left + 1) - maxCharFreq;
            }
            maxWindow = Math.max(maxWindow, right - left + 1);
            right++;
        }
        //System.out.println(charFrequency);
        System.out.println(maxWindow);
        return maxWindow;
    }


}
