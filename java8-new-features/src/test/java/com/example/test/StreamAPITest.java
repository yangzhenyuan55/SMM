package com.example.test;

import com.example.pojo.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: yzy
 * @Date: 2022/9/21-15:26
 * @Description:
 */
public class StreamAPITest {
    protected List<Employee> employees = Arrays.asList(
            new Employee("张三", 23, 9000.0),
            new Employee("李四", 38, 8000.0),
            new Employee("王五", 60, 5000.0),
            new Employee("赵六", 16, 4000.0),
            new Employee("田七", 18, 3000.0)
    );

    /**
     * 使用Stream过滤集合
     */
    @Test
    public void testStreamFilter() {

        // 使用Stream API来过滤并输出结果
        employees.stream().filter(e -> e.getSalary() >= 5000.0).forEach(System.out::println);
    }


    /**
     * 限制过滤数量
     */
    @Test
    public void testStreamLimit() {
        employees.stream().filter(e -> e.getSalary()>= 5000.0).limit(2).forEach(System.out::println);
    }

    /**
     * 获取集合的成员的属性
     */
    @Test
    public void testStreamGetValue() {
        employees.stream().map(Employee::getName).forEach(System.out::println);
    }

    /**
     * 获取过滤之后的List集合
     */
    @Test
    public void testGetFilterList() {
        List<Double> list = employees.stream()
                .map(Employee::getSalary)
                .filter(salary -> salary >= 4000.0)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(list);
    }

    /**
     * 使用Stream来操作数组
     */
    @Test
    public void testGetStreamFromArray() {
        String[] person = {"jack", "marry", "bob", "zhangsan", "lisi"};

        // 将数组转换成Stream
        List<String> list = Stream.of(person)
                .filter(e -> e.length() >= 4)
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(list);

    }


}
