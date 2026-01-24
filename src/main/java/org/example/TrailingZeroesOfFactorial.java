package org.example;

public class TrailingZeroesOfFactorial {
    public static void main(String[] args) {
        System.out.println(new TrailingZeroesOfFactorial().trainlingZeroes(15));
    }
    public int trainlingZeroes(int n){
        int power = 5;
        int result = 0;
        while (n/ power > 0){
            result = result + n/power;
            power = power * 5;
        }

        return result;
    }
}
