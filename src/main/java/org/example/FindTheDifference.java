package org.example;

public class FindTheDifference {
    public char findTheDifference(String s, String t) {

        int xor = 0;
        for(char c : t.toCharArray()) {
            xor = xor + c;
           // System.out.println("[s]character : " + c + " xor : " + xor);
        }
        for(char c : s.toCharArray()) {
            xor = xor - c;
          //  System.out.println("[t]character : " + c + " xor : " + xor);
        }
        //System.out.println("xor : " + xor);
        return (char) xor;
    }

    public static void main(String[] args) {
        System.out.println( new FindTheDifference().findTheDifference("abcd", "abcde"));
    }
}
