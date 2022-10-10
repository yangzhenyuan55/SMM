package com.example;

import com.example.pojo.Employee;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: yzy
 * @Date: 2022/9/21-15:18
 * @Description:
 */
public class StreamDemo {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("张三", 23, 9000.0),
                new Employee("李四", 38, 8000.0),
                new Employee("王五", 60, 5000.0),
                new Employee("赵六", 16, 4000.0),
                new Employee("田七", 18, 3000.0)
        );

        // 使用Stream API来过滤并输出结果
        employees.stream().filter(e -> e.getSalary() >= 5000.0).forEach(System.out::println);

    }
}
