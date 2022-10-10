package com.example.reflect;

import com.example.reflect.bean.User;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * @Author: yzy
 * @Date: 2022/9/8-17:06
 * @Description: 反射机制----Field
 */
public class ReflectField {
    public static void main(String[] args) throws Exception {
        // 通过反射来实例化对象
        Class<?> aClass = Class.forName("com.example.reflect.bean.User");
        // 在调用newInstance()方法的时候会调用User的午餐构造方法来完成对象的创建
        Object obj = aClass.newInstance();
        // 获取文件的绝对路径(要放在根路径下)
        String path = Thread.currentThread().getContextClassLoader().getResource("com/example/reflect/db.properties").getPath();
        System.out.println(path);
        System.out.println("=============================");

        // 获取Class的类名
        System.out.println("类名: " + aClass.getName());

        System.out.println("获取User的Field属性");
        // 只能获取public修饰的属性
        Field[] fields = aClass.getFields();
        System.out.println(Arrays.toString(fields));
        // 获取所有的属性
        Field[] declaredFields = aClass.getDeclaredFields();
        System.out.println(Arrays.toString(declaredFields));
        // 获取属性名
        System.out.println("属性名: " + fields[0].getName());
        // 获取属性类型
        System.out.println("属性类型: " + fields[0].getType());
        // 获取属性的修饰符
        String modifier = Modifier.toString(fields[0].getModifiers());
        System.out.println("属性修饰符: " + modifier);

        // 通过反射机制给属性赋值
        Field idField = aClass.getField("id");
        idField.set(obj, "1111");
        System.out.print("反射机制给id属性赋值:id=");
        System.out.println(((User) obj).id);

        // 获取属性的值
        System.out.println("获取id的值=" + idField.get(obj));

        // 访问私有变量name
        Field nameField = aClass.getDeclaredField("name");
        // 设置可以访问(不设置直接访问会报异常)
        nameField.setAccessible(true);
        nameField.set(obj, "张三");
        System.out.println("name=" + nameField.get(obj));
    }
}
