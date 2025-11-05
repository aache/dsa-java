package org.example;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

record Sale(String customer, String product, double amount) {}
record Summary(double min, double max, double avg) {}

public class StreamExample {
    public static void main(String[] args) {
        // Sample data (Java 10 'var' for brevity)
        var sales = List.of(
                new Sale("Alice", "Mouse", 19.99),
                new Sale("Bob", "Keyboard", 49.99),
                new Sale("Alice", "Monitor", 129.99),
                new Sale("Cara", "Mouse", 18.50),
                new Sale("Bob", "Mousepad", 9.99),
                new Sale("Alice", "USB-C Cable", 7.49),
                new Sale("Cara", "Keyboard", 44.99),
                new Sale("Bob", "Monitor", 119.99),
                new Sale("Derek", "Laptop Stand", 29.99)
        );

        // 1) Per-customer total revenue (Java 8: groupingBy + summingDouble)
        Map<String, Double> revenueByCustomer =
                sales.stream().collect(groupingBy(Sale::customer, summingDouble(Sale::amount)));
        System.out.println(revenueByCustomer);
        // 2) Products bought per customer, sorted & unmodifiable
        //    (Java 8: groupingBy + mapping; Java 9: collectingAndThen + unmodifiableList)
        Map<String, List<String>> productsByCustomer =
                sales.stream().collect(groupingBy(
                        Sale::customer,
                        collectingAndThen(
                                mapping(Sale::product, collectingAndThen(toList(),
                                        lst -> lst.stream().sorted().toList() // Java 16: Stream.toList()
                                )),
                                Collections::unmodifiableList
                        )
                ));
        System.out.println(productsByCustomer);

    }
}
