package com.example.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yzy
 * @Date: 2022/10/10-20:02
 * @Description: Object 的 wait()方法和 notify()方法
 * <p>
 *
 * Object obj = new Object();
 * obj.wait()
 * obj.notify()
 * wait()方法作用：
 * 表示：让正在obj对象上活动的线程进入等待状态，并且释放占有obj对象的锁，无期限等待，知道被唤醒为止
 * notify()方法作用
 * 表示：唤醒正在obj对象上活动的线程，也就是唤醒obj的当前线程
 *
 *
 * 实现生产者和消费者
 * 生产线程和消费线程
 *
 * 模拟一个仓库，使用List来表示这个仓库，仓库容量未 1
 * 保证仓库中只存储一个线程，生产一个消费一个
 */
public class WaitAndNotifyMethod {


    public static void main(String[] args) {
        List<Object> storehouse = new ArrayList<>(1);
        Thread producerThread = new Thread(new Producer(storehouse));
        Thread consumerThread = new Thread(new Consumer(storehouse));

        producerThread.setName("producer-thread");
        consumerThread.setName("consumer-thread");

        producerThread.start();
        consumerThread.start();


    }

    /**
     * 生产者
     */
    static class Producer implements Runnable{
        private List<Object> storehouse; // 仓库，与消费者共享

        public Producer(List<Object> storehouse) {
            this.storehouse = storehouse;
        }

        @Override
        public void run() {
            // 一直生产线程
            while (true) {
                // 给仓库对象加锁
                synchronized (storehouse) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    // 容量为1，大于0相当于容量满了
                    if (storehouse.size() > 1) {
                        // 当容量满了就不生产了
                        // 等待一下
                        try {
                            // 当前线程进入等待状态，并且释放storehouse对象的锁
                            storehouse.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // 生产并放进仓库
                    Object obj = new Object();
                    System.out.println(Thread.currentThread().getName() + " produce ====> " + obj);
                    storehouse.add(obj);

                    // 唤醒消费者线程
                    storehouse.notify();

                }
            }
        }
    }

    /**
     * 消费者
     */
    static class Consumer implements Runnable{
        private List<Object> storehouse; // 仓库

        public Consumer(List<Object> storehouse) {
            this.storehouse = storehouse;
        }

        @Override
        public void run() {
            // 一直消费线程

            while (true) {
                synchronized(storehouse) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    // 仓库空了就不再消费
                    if(storehouse.size() == 0) {

                        try {
                            // 当前线程进入等待，并且释放storehouse的锁
                            storehouse.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    // 消费仓库存储的对象
                    Object obj = storehouse.remove(0);
                    System.out.println(Thread.currentThread().getName() + " consumer ====> " + obj);

                    // 唤醒生产者线程，但是不会释放storehouse的锁
                    storehouse.notify();
                    // 等待这个synchronized代码块代码执行结束，锁才会被释放掉
                }
            }
        }
    }
}


