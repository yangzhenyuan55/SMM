package com.example;

import com.example.func.MyPredicate;

import com.example.pojo.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * @Author: yzy
 * @Date: 2022/9/21-13:55
 * @Description:
 */
public class LambdaDemo {

    public static List<Employee> employeeAgeFilter(List<? extends Employee> list, MyPredicate<? super Employee> predicate) {
        List<Employee> filterSet = new ArrayList<>();

        for (Employee e : list) {
            if(predicate.filter(e)) {
                filterSet.add(e);
            }
        }
        return filterSet;
    }

    public static List<Employee> employeeSalaryFilter(List<? extends Employee> list, MyPredicate<? super Employee> predicate) {
        List<Employee> filterSet = new ArrayList<>();

        for (Employee e : list) {
            if(predicate.filter(e)) {
                filterSet.add(e);
            }
        }
        return filterSet;
    }

    public static void main(String[] args) {

        /*new LambdaDemo().printSomething("sss", val -> System.out.println(val));*/


        List<Employee> employees = Arrays.asList(
                new Employee("张三", 23, 9000.0),
                new Employee("李四", 38, 8000.0),
                new Employee("王五", 60, 5000.0),
                new Employee("赵六", 16, 4000.0),
                new Employee("田七", 18, 3000.0)
        );

        List<Employee> ageFilterEmp = employeeAgeFilter(employees, new MyPredicate<Employee>() {
            @Override
            public boolean filter(Employee employee) {
                return employee.getAge() > 30;
            }
        });
        System.out.println(ageFilterEmp);

        employeeSalaryFilter(employees, employee -> employee.getSalary() >= 5000).forEach(System.out::println);

    }

}
