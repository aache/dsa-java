package org.example;

import java.util.Arrays;

public class MinHarvest {
        public Integer minHarvestRate(int[] apples, Integer h) {

            int maxApples = Arrays.stream(apples).max().getAsInt();
            int result = 0;
            for(int i = 1; i <= maxApples; i++){ // Unoptimized
                int hours = 0;
                for(int j = 0; j < apples.length; j++){
                    int x = apples[j]/ i;
                    if(apples[j] % i != 0){
                        x = x + 1;
                    }
                    hours = hours + x;
                }
                if(hours <= h) {
                    result = i;
                    break;
                }
            }
            return result;
        }
}
