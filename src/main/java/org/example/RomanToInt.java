package org.example;

import java.util.Map;

public class RomanToInt {

    public int romanToInt(String s) {
        Map<Character, Integer> romanToIntMap =
                Map.of( 'I', 1,
                        'V', 5,
                        'X', 10,
                        'L', 50,
                        'C', 100,
                        'D', 500,
                        'M', 1000);
        int result = 0;
        for(int i = 0; i < s.length() - 1; i++){
            if(romanToIntMap.get(s.charAt(i)) >= romanToIntMap.get(s.charAt(i+1))){
                result = result + romanToIntMap.get(s.charAt(i));
            } else {
                result = result - romanToIntMap.get(s.charAt(i));
            }
        }
        result = result + romanToIntMap.get(s.charAt(s.length()-1));
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new RomanToInt().romanToInt("XIII")); //13
        System.out.println(new RomanToInt().romanToInt("XIV"));  //14
    }
}
