package org.example;

public class NeedleHaystack {
    public int strStr(String haystack, String needle) {

        if(needle.length() > haystack.length()){
            return -1;
        }
        int ptrNeedle = 0;
        int ptrHaystack = 0;
        while(ptrHaystack < haystack.length()){
            if(haystack.charAt(ptrHaystack) == needle.charAt(ptrNeedle)){
                ptrNeedle++;
                if(ptrNeedle == needle.length()){
                    return ptrHaystack - needle.length() + 1;
                }
            } else if(haystack.charAt(ptrHaystack) != needle.charAt(ptrNeedle) && ptrNeedle > 0){
                ptrHaystack = ptrHaystack - ptrNeedle;
                ptrNeedle = 0;
            }
            ptrHaystack++;
        }
        return -1;
    }

    public static void main(String[] args) {
       // System.out.println(new NeedleHaystack().strStr("sadbutsad", "sad"));
        // System.out.println(new NeedleHaystack().strStr("abcsadbutsad", "sad"));
         System.out.println(new NeedleHaystack().strStr("mississippi", "issip"));
    }
}
