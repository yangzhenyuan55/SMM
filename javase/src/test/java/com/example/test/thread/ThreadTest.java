package com.example.test.thread;

import com.example.pojo.Account;
import com.example.thread.AccountThread;
import com.example.thread.MyRunnable;
import com.example.thread.MyThread;
import org.junit.Test;


/**
 * @Author: yzy
 * @Date: 2022/10/3-20:00
 * @Description:
 */
public class ThreadTest {


    /**
     * 线程安全
     */
    @Test
    public void testThreadSafe() {

        Account account = new Account("act-001", 10000);

        Thread t1 = new AccountThread(account);
        Thread t2 = new AccountThread(account);
        t1.setName("t1");
        t2.setName("t2");

        t1.start();
        t2.start();
        System.out.println("main");

        System.out.println(account);
    }


    @Test
    public void testJoin() {
        System.out.println("main start");

        Thread t = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "===> begin...");


            System.out.println(Thread.currentThread().getName() + "===> end...");
        });
        t.setName("tThread");
        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("main end");
    }


    /**
     * 线程调度优先级
     */
    @Test
    public void testThreadPriority() {
        System.out.println("优先级: " + Thread.currentThread().getPriority());

    }


    /**
     * 更合理的去终止线程
     * 在Runnable中添加一个属性，由此属性来决定是否结束线程
     */
    @Test
    public void testCloseThread() {

        class MyRunnable implements Runnable{
            boolean run = true;

            @Override
            public void run() {
                if(run) {
                    for (int i = 1; i <= 10; i++) {
                        System.out.println(Thread.currentThread().getName() + "===>" + i);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    // return 之前进行一些数据保存的操作，保证数据不会丢失
                    return;
                }
            }
        }


        MyRunnable r = new MyRunnable();

        Thread t = new Thread(r);
        t.setName("t");
        t.start();

        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 5s后终止t线程
        r.run = false;


    }

    /**
     * 强行终止线程（是终止线程而不是终止睡眠）
     *
     */
    @Test
    public void testForceCloseThread() {
        Thread t = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "===> begin...");
            try {
                Thread.sleep(1000L * 60 * 60 * 24 * 365);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }

            System.out.println(Thread.currentThread().getName() + "===> end...");
        });
        t.setName("tThread");
        t.start();

        // 模拟5s
        // 5s之后强行终止t线程
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.stop(); // 已过时，不建议使用
        // tThread===> begin...
        // 直接终止线程，并未输出线程结束语句
    }


    /**
     * 终止线程的睡眠(不是中断线程执行)
     */
    @Test
    public void testCloseSleep() {
        Thread t = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "===> begin...");
            try {
                Thread.sleep(1000L * 60 * 60 * 24 * 365);
            } catch (InterruptedException e) {
                System.out.println(e);
            }

            System.out.println(Thread.currentThread().getName() + "===> end...");
        });
        t.setName("tThread");
        t.start();

        // 希望5s之后t线程醒来
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 5s之后
        // 干扰，会让正在睡眠的线程出异常，一旦报异常，会进到catch语句块中执行，然后try catch就结束了
        // 这种中断睡眠的方式依靠了Java的异常处理机制
        t.interrupt();
        //tThread===> begin...
        //java.lang.InterruptedException: sleep interrupted
        //tThread===> end...


    }


    /**
     * 关于sleep的面试题
     * t.sleep()并不会让该线程阻塞，因为sleep()是一个静态方法，t.sleep()相当于Thread.sleep()，
     * 只会让当前线程阻塞
     */
    @Test
    public void testSleepMethodOfThread2() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread() + "====>" + i);
                }
            }
        });
        t.start();

        try {
            t.sleep(1000 * 5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        System.out.println("main hello world");
    }

    /**
     * 关于线程的sleep()方法
     */
    @Test
    public void testSleepMethodOfThread1() {
        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(i);
        }
    }


    /**
     * 使用匿名内部类的方式来传入Runnable实现类参数
     */
    @Test
    public void testCreateThreadByImplRunnable2() {
        new Thread(() -> {
            System.out.println("[MyThread] begin...");

            for (int i = 0; i < 100; i++) {

                System.out.println("["+ Thread.currentThread().getName() +"] -----> " + i);
            }

            System.out.println("["+ Thread.currentThread().getName() +"] over");
        }).start();

        // 主线程
        for (int i = 0; i < 100; i++) {
            System.out.println("["+ Thread.currentThread().getName() +"] ----> " + i);
        }
    }



    /**
     * 编写一个类实现Runnable，将此实现类作为参数传入Thread构造方法来创建线程类
     */
    @Test
    public void testCreateThreadByImplRunnable1() {
        Thread myThread = new Thread(new MyRunnable());
        myThread.start();

        // 主线程
        for (int i = 0; i < 100; i++) {
            System.out.println("["+ Thread.currentThread().getName() +"] ----> " + i);
        }
    }

    @Test
    public void testCreateThreadByExtendsThread(){
        MyThread myThread = new MyThread();
        myThread.setName("MyThread");
        // 分支线程启动
        myThread.start();

        // 主线程
        for (int i = 0; i < 10; i++) {
            System.out.println("["+ Thread.currentThread().getName() +"] ----> " + i);
        }
    }
}
