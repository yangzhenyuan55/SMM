package com.example.thread;

/**
 * @Author: yzy
 * @Date: 2022/10/3-20:14
 * @Description:
 */
public class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("[MyThread] begin...");
        for (int i = 0; i < 10; i++) {
            System.out.println("["+ Thread.currentThread().getName() +"] -----> " + i);
        }

        System.out.println("["+ Thread.currentThread().getName() +"] over");
    }
}
