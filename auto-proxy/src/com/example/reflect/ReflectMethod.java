package com.example.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

/**
 * @Author: yzy
 * @Date: 2022/9/8-18:48
 * @Description: 反射机制----Method
 */
public class ReflectMethod {
    public static void main(String[] args) throws Exception {
        Class<?> userServiceClass = Class.forName("com.example.reflect.service.UserService");
        Object obj = userServiceClass.newInstance();

        // 获取所有方法(包括私有的)
        Method[] methods = userServiceClass.getDeclaredMethods();

        for (Method method : methods) {
            // 获取修饰符列表
            System.out.print(Modifier.toString(method.getModifiers()) + " ");
            // 获取返回值类型
            System.out.print(method.getReturnType().getSimpleName() + " ");
            // 获取方法名
            System.out.print(method.getName() + " (");
            // 获取方法参数列表
            // 参数类型列表

            Parameter[] parameters = method.getParameters();
            for (int i = 0; i < parameters.length; i++) {

                String param = parameters[i].getType().getSimpleName() + " " +  parameters[i].getName();
                if(i != parameters.length - 1) {
                    param = param + ", ";
                }
                System.out.print(param);
            }


            System.out.println(")");
        }

        // 反射来执行方法
        Method login = userServiceClass.getMethod("login", String.class, String.class);
        login.invoke(obj, "admin", "123");

    }
}
