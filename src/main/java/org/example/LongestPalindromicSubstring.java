package org.example;

public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        System.out.println("Longest Palindromic Substring" + new LongestPalindromicSubstring().longestPalindrome("bcaba"));
    }

    public String longestPalindrome(String s) {
        int i = 0; int j = 1;
        String result = "";
        while (j <= s.length()) {
            String str = s.substring(i,j);
            System.out.println("Substring = (" +i +", " + j +")"+ str);
            if(isPalindrome(str)){
                result = result.length() > str.length() ? result : str;
                j++;
            }else {
                i++;
            }
        }
        return result;
    }

    public boolean isPalindrome(String s) {
       return new StringBuilder(s).reverse().toString().equals(s);
    }
}

