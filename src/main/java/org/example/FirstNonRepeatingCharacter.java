package org.example;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/first-unique-character-in-a-string/
public class FirstNonRepeatingCharacter {
    public int firstUniqChar(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c,0) + 1);
        }
        int index = 0;
        for(char c : s.toCharArray()){
            if(freqMap.get(c).equals(1)){
                return index;
            }
            index++;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new FirstNonRepeatingCharacter().firstUniqChar("leetcode"));
        System.out.println(new FirstNonRepeatingCharacter().firstUniqChar("loveleetcode"));
        System.out.println(new FirstNonRepeatingCharacter().firstUniqChar("aabb"));
    }
}
