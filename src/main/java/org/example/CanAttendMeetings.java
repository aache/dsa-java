package org.example;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CanAttendMeetings {
    public static void main(String[] args) {
       boolean result = new CanAttendMeetings().canAttendMeetings(new int[][] {{1,2},{8,10}, {3,7}});
        System.out.println(result);
    }
    public Boolean canAttendMeetings(int[][] intervals) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < intervals.length; i++){
            map.put(intervals[i][0], intervals[i][1]);
        }
        // sort by start time
        LinkedHashMap<Integer, Integer> sortedMap = map.entrySet().stream().
                sorted(Map.Entry.<Integer, Integer>comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));

        int previous = -1;
        for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
            if(entry.getKey() < previous){
                return false;
            }
            previous = entry.getValue();
        }
        //System.out.println(map);
        return true;
    }
}


