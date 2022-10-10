package com.example.thread;

/**
 * @Author: yzy
 * @Date: 2022/10/3-22:53
 * @Description:
 */
public class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("[MyThread] begin...");
        for (int i = 0; i < 100; i++) {
            System.out.println("["+ Thread.currentThread().getName() +"] -----> " + i);
        }

        System.out.println("["+ Thread.currentThread().getName() +"] over");
    }
}
