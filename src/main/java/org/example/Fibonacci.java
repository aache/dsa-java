package org.example;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(new Fibonacci().finonacci(10));
    }

    public int finonacci(int n){
        int n0 = 0;
        int n1 = 1;
        for(int i = 0; i < n ; i++){
            int swap = n1;
            n1 = n0 + n1;
            n0 = swap;
        }

        return n0;
    }
}
