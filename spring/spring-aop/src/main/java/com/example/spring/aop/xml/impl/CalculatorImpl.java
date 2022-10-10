package com.example.spring.aop.xml.impl;


import com.example.spring.aop.xml.Calculator;
import org.springframework.stereotype.Component;

/**
 * @Author: yzy
 * @Date: 2022/9/7-18:50
 * @Description: 简单计算器实现类
 */
@Component
public class CalculatorImpl implements Calculator {


    @Override
    public int add(int i, int j) {
        int res = i + j;
        System.out.println("方法内部: " + res);
        return res;
    }

    @Override
    public int sub(int i, int j) {
        int res = i - j;
        System.out.println("方法内部: " + res);
        return res;
    }

    @Override
    public int mul(int i, int j) {
        int res = i * j;
        System.out.println("方法内部: " + res);
        return res;
    }

    @Override
    public int div(int i, int j) {
        int res = i / j;
        System.out.println("方法内部: " + res);
        return res;
    }
}
