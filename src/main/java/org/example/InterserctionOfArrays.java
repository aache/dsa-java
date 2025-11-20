package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class InterserctionOfArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
//        List<Integer> result = new ArrayList<>();
//        List<Integer> list1 = new ArrayList<>(new HashSet<>(Arrays.stream(nums1).boxed().toList()));
//        List<Integer> list2 = new ArrayList<>(new HashSet<>(Arrays.stream(nums2).boxed().toList()));
//
//        if(list1.size() < list2.size()){
//            for(int i = 0; i < list1.size() ; i++){
//                if(list2.contains(list1.get(i))){
//                 result.add(list1.get(i));
//                }
//            }
//        } else {
//            for(int i = 0; i < list2.size() ; i++){
//                if(list1.contains(list2.get(i))){
//                    result.add(list2.get(i));
//                }
//            }
//        }
//
//        int[] finalResult = new int[result.size()];
//        int x = 0;
//        for(Integer i : result){
//            finalResult[x] = i;
//            x++;
//        }
//        return finalResult;
        Set<Integer> set2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());
        return Arrays.stream(nums1).distinct().filter(set2::contains).toArray();
    }
}
