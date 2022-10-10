package com.example.func.impl;

import com.example.func.MyPredicate;
import com.example.pojo.Employee;

/**
 * @Author: yzy
 * @Date: 2022/9/21-14:31
 * @Description:
 */
public class EmployeeAgeFilter implements MyPredicate<Employee> {


    @Override
    public boolean filter(Employee employee) {
        return employee.getAge() >= 30;
    }
}
