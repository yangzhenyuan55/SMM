package com.example.func.impl;

import com.example.func.MyPredicate;
import com.example.pojo.Employee;

/**
 * @Author: yzy
 * @Date: 2022/9/21-15:01
 * @Description:
 */
public class EmployeeSalaryFilter implements MyPredicate<Employee> {


    @Override
    public boolean filter(Employee employee) {
        return employee.getSalary() >= 5000.0;
    }
}
