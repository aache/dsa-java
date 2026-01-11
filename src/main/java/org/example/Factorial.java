package org.example;

public class Factorial {

    public static void main(String[] args) {
        System.out.println(new Factorial().factorial(5));
    }

    public int factorial(int num) {
        if(num == 1){
            return 1;
        }
        return num * factorial(num - 1);
    }
}
