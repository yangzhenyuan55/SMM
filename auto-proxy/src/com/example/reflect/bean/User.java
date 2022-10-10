package com.example.reflect.bean;

/**
 * @Author: yzy
 * @Date: 2022/9/8-17:08
 * @Description:
 */
public class User {
    public String id;
    private String name;
    protected int age;
    boolean sex;


    public User() {
        System.out.println("User对象创建");
    }


}
