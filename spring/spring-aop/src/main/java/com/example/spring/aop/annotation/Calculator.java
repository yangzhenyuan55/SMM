package com.example.spring.aop.annotation;

/**
 * @Author: yzy
 * @Date: 2022/9/7-18:47
 * @Description: 简单计算器接口
 */
public interface Calculator {
    /**
     * 加法
     * @param i 加数1
     * @param j 加数2
     * @return i和j的和
     */
    int add(int i, int j);

    /**
     * 减法
     * @param i 被减数
     * @param j 减数
     * @return i - j
     */
    int sub(int i, int j);

    /**
     * 乘法
     * @param i 乘数
     * @param j 乘数
     * @return i * j
     */
    int mul(int i, int j);

    /**
     * 除法
     * @param i 被除数
     * @param j 除数
     * @return i / j
     */
    int div(int i, int j);
}
