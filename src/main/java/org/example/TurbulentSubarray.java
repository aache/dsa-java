package org.example;

public class TurbulentSubarray {
    public int maxTurbulenceSize(int[] arr) {
       return 1;
    }

    public static void main(String[] args) {
        System.out.println(new TurbulentSubarray().maxTurbulenceSize(new int[] {9,4,2,10,7,8,8,1,9}));
        System.out.println(new TurbulentSubarray().maxTurbulenceSize(new int[] {4,8,12,16}));
        System.out.println(new TurbulentSubarray().maxTurbulenceSize(new int[] {100}));
    }
}
