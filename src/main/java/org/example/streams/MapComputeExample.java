package org.example.streams;

import java.util.HashMap;
import java.util.Map;

public class MapComputeExample {
    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>(
          Map.of("Apple", 12, "Banana" , 10)
        );
        map.compute("Oranges", (k, v) -> v == null ? 1 : v + 1);
        map.putIfAbsent("Apple", 100);
        System.out.println(map);
    }
}
