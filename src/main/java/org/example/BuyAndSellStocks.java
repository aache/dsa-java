package org.example;

public class BuyAndSellStocks {
    public static void main(String[] args) {
        System.out.println(new BuyAndSellStocks().maxProfit(new int[] {7,1,5,3,6,4}));
        System.out.println(new BuyAndSellStocks().maxProfit(new int[]{7,6,4,3,1}));
        System.out.println(new BuyAndSellStocks().maxProfit(new int[]{1,2,4,2,5,7,2,4,9,0,9})); //Failed why?
    }

    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int i = 0;
        for(int j = 1; j < prices.length ; j++){
            int currentProfit = prices[j] - prices[i];
            if(currentProfit <= 0){
                i = j;
            }
            maxProfit = Math.max(currentProfit, maxProfit);
        }

        return maxProfit;
    }
}
