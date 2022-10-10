package com.example.thread;

/**
 * @Author: yzy
 * @Date: 2022/10/4-20:48
 * @Description:
 */
public class DeadLock {

    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();


        Thread t1 = new DeadLockThead1(o1, o2);
        Thread t2 = new DeadLockThead2(o1, o2);

        t1.start();
        t2.start();
    }
}


class DeadLockThead1 extends Thread{
    private Object o1;
    private Object o2;

    public DeadLockThead1(Object o1, Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    @Override
    public void run() {
        synchronized(o1) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized(o2) {
                System.out.println(Thread.currentThread().getName());
            }
        }
    }
}

class DeadLockThead2 extends Thread{
    private Object o1;
    private Object o2;

    public DeadLockThead2(Object o1, Object o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    @Override
    public void run() {
        synchronized(o2) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized(o1) {
                System.out.println(Thread.currentThread().getName());
            }
        }
    }
}

