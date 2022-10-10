package com.example.reflect;

/**
 * @Author: yzy
 * @Date: 2022/9/8-18:44
 * @Description: 可变长度参数
 */
public class ArgsTest {
    public static void main(String[] args) {
        m(1, 2, 3, 4);
        m();
    }

    public static void m(int... args) {
        for (int e : args) {
            System.out.print(e + " ");
        }
    }
}
