package com.example.thread;

import com.example.pojo.Account;



/**
 * @Author: yzy
 * @Date: 2022/10/4-20:03
 * @Description: 线程安全，两个线程去操作同一个共享对象
 */
public class ThreadSafeTest {
    public static void main(String[] args) {
        Account account = new Account("act-001", 10000);

        Thread t1 = new AccountThread(account);
        Thread t2 = new AccountThread(account);
        t1.setName("t1");
        t2.setName("t2");

        t1.start();
        t2.start();

        System.out.println(account);

    }


}
