package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamQuestion {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "IT", 70000, 28),
                new Employee("Bob", "HR", 50000, 35),
                new Employee("Charlie", "IT", 90000, 40),
                new Employee("David", "Finance", 60000, 32),
                new Employee("Eve", "HR", 80000, 29)
        );

        // 	1.	Get the list of employee names sorted by salary (descending).
        System.out.println("Get the list of employee names sorted by salary (descending).");
        List<Employee> sortedBySalaryEmployee = employees.stream().sorted(Comparator.comparing(Employee::salary)).toList();
        System.out.println(sortedBySalaryEmployee);

        // 	2.	Find the average salary per department.
        System.out.println("Find the average salary per department.");
        Map<String, Double> averageSalPerDept = employees.stream().collect(Collectors.groupingBy(Employee::department, Collectors.averagingDouble(Employee::salary)));
        System.out.println(averageSalPerDept);

        // 3. Average age per department
        System.out.println("Get the average age per department.");
        Map<String, Double> averageAgePerDept = employees.stream().collect(Collectors.groupingBy(Employee::department, Collectors.averagingDouble(Employee::age)));
        System.out.println(averageAgePerDept);
    }
}

record Employee(
    String name,
    String department,
    double salary,
    int age
){
}
