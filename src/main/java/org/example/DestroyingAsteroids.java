package org.example;

//https://leetcode.com/problems/destroying-asteroids/?envType=daily-question&envId=2026-05-31

import java.util.Arrays;

public class DestroyingAsteroids {

    public static void main(String[] args) {

    }

    public static boolean destroyingAsteroids(int mass,int[] asteroids) {

        Arrays.sort(asteroids);
        long newMass = mass;
        for(int i = 0; i < asteroids.length; i++){
            if(asteroids[i] > newMass){
                return false;
            }
            newMass += asteroids[i];
        }

        return true;
    }
}
