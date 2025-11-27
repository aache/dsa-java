package org.example;

public class WaterBottles_Leetcode1518 {

    public static void main(String[] args) {
        System.out.println(new WaterBottles_Leetcode1518().numWaterBottles(15, 4));
    }

    public int numWaterBottles(int numBottles, int numExchange) {
        int result = numBottles;
        int remaining = 0;
        while(numBottles != 0) {
            int exch = (numBottles + remaining) / numExchange;
            remaining = (numBottles + remaining) % numExchange;
            result = result + exch;
            numBottles = exch;
        }

        return result;
    }
}
