package com.example.spring.proxy.impl;

import com.example.spring.proxy.Calculator;

/**
 * @Author: yzy
 * @Date: 2022/9/7-18:50
 * @Description: 简单计算器实现类
 */
public class CalculatorImpl implements Calculator {


    @Override
    public int add(int i, int j) {
        return i + j;
    }

    @Override
    public int sub(int i, int j) {


        return i - j;
    }

    @Override
    public int mul(int i, int j) {
       return i * j;
    }

    @Override
    public int div(int i, int j) {
        return i / j;
    }
}
