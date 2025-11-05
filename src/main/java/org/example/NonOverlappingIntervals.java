package org.example;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/non-overlapping-intervals/description/

public class NonOverlappingIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        // Sort by the start-point
        Arrays.sort(intervals, Comparator.comparing(a -> a[0]));

        int deleteCount = 0;
        int ending = intervals[0][1];

        for(int i = 1; i < intervals.length; i++){
            if(intervals[i][0] >= ending){ // Does nt overlap - then set new ending
                ending = intervals[i][1];
            } else {
                // If overlapping increase deleteCount and delete the interval with greater ending
                deleteCount++;
                ending = Math.min(intervals[i][1], ending);
            }
        }
        return deleteCount;
    }

    public static void main(String[] args) {
        NonOverlappingIntervals sol = new NonOverlappingIntervals();

        int[][] intervals1 = {{1,2},{2,3},{3,4},{1,3}};
        int[][] intervals2 = {{1,2},{1,2},{1,2}};
        int[][] intervals3 = {{1,2},{2,3}};

        System.out.println(sol.eraseOverlapIntervals(intervals1)); // 1
        System.out.println(sol.eraseOverlapIntervals(intervals2)); // 2
        System.out.println(sol.eraseOverlapIntervals(intervals3)); // 0
    }
}
