package com.example.spring.proxy.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @Author: yzy
 * @Date: 2022/9/8-15:14
 * @Description: 生成代理类的工厂
 */
public class ProxyFactory {
    /**
     * 目标对象
     */
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxy() {
        /*
        Proxy.newProxyInstance()方法参数解析
            ClassLoader loader: 类加载器(指定加载动态生成的代理类的类加载器)
            Class<?>[] interfaces: 获取目标对象实现的所有接口的class对象的数组
            InvocationHandler h: 设置代理类中的抽象方法如何重写
         */

        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
            /**
             * 执行代理类中的方法如何执行
             * @param proxy 代理对象
             * @param method 要重写的方法（要执行的方法）
             * @param args 要执行的方法的参数列表
             * @return 要执行的方法的返回值
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                System.out.println("日志---> method: " + method.getName() + ", 参数=" + Arrays.toString(args) + " <---");
                Object result = method.invoke(target, args);
                System.out.println("日志---> method: " + method.getName() + ", 结果=" + result + " <---");
                return result;
            }
        });
    }
}
