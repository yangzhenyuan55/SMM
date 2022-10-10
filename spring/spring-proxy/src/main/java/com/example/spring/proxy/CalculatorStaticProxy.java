package com.example.spring.proxy;

/**
 * @Author: yzy
 * @Date: 2022/9/8-14:56
 * @Description: 简单计算器实现类的静态代理类
 */
public class CalculatorStaticProxy implements Calculator{
    /**
     * 目标对象
     */
    private final Calculator calculator;

    public CalculatorStaticProxy(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public int add(int i, int j) {
        System.out.println("日志--->方法: add, 参数: " + i + ", " + j);
        int result = calculator.add(i, j);
        System.out.println("日志--->方法: add, result=" + result);
        return result;
    }

    @Override
    public int sub(int i, int j) {
        System.out.println("日志--->方法: sub, 参数: " + i + ", " + j);
        int result = calculator.sub(i, j);
        System.out.println("日志--->方法: sub, result=" + result);
        return result;
    }

    @Override
    public int mul(int i, int j) {
        System.out.println("日志--->方法: mul, 参数: " + i + ", " + j);
        int result = calculator.mul(i, j);
        System.out.println("日志--->方法: mul, result=" + result);
        return result;
    }

    @Override
    public int div(int i, int j) {
        System.out.println("日志--->方法: div, 参数: " + i + ", " + j);
        int result = calculator.div(i, j);
        System.out.println("日志--->方法: div, result=" + result);
        return result;
    }
}
