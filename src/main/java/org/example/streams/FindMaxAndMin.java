package org.example.streams;

import java.util.IntSummaryStatistics;
import java.util.List;

public class FindMaxAndMin {
    public static void main(String[] args) {
        List<Integer> integers = List.of(5,3,100,1067,43,11,342);
        int max = integers.stream().max(Integer::compareTo).get();
        int min = integers.stream().min(Integer::compareTo).get();

        System.out.println("Max :: " + max);
        System.out.println("Min :: " + min);

        // Using summaryStatistics
        IntSummaryStatistics summaryStatistics = integers.stream().mapToInt(Integer::intValue).summaryStatistics();
        System.out.println("Summary Statistics min ::" + summaryStatistics.getMin());
        System.out.println("Summary Statistics max ::" + summaryStatistics.getMax());
        System.out.println("Summary Statistics average ::" + summaryStatistics.getAverage());
        System.out.println("Summary Statistics count ::" + summaryStatistics.getCount());
    }
}
