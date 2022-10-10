package com.example.spring;

import com.example.spring.proxy.Calculator;
import com.example.spring.proxy.CalculatorStaticProxy;
import com.example.spring.proxy.factory.ProxyFactory;
import com.example.spring.proxy.impl.CalculatorImpl;

import java.util.Arrays;

/**
 * @Author: yzy
 * @Date: 2022/9/7-18:57
 * @Description:
 */
public class App {
    public static void main(String[] args) {
        /*
        静态代理测试
         */
        CalculatorStaticProxy proxy1 = new CalculatorStaticProxy(new CalculatorImpl());
        System.out.println(proxy1.add(1, 2));
        System.out.println("==============================");

        System.out.println("动态代理测试");
        ProxyFactory proxyFactory = new ProxyFactory(new CalculatorImpl());
        Calculator proxy2 = (Calculator) proxyFactory.getProxy();
        proxy2.add(1, 2);

    }
}
