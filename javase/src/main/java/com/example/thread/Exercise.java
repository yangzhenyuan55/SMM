package com.example.thread;

/**
 * @Author: yzy
 * @Date: 2022/10/11-20:18
 * @Description: 练习，两个线程交替输出数字，t1线程输出奇数，t2线程输出偶数
 */
public class Exercise {
    public static void main(String[] args) {
        Num num = new Num(1);
        Thread t1 = new Thread(new R1(num));
        Thread t2 = new Thread(new R2(num));

        t1.setName("T1");
        t2.setName("T2");

        t1.start();
        t2.start();

    }


    static class Num {
        private int i;

        public Num(int i) {
            this.i = i;
        }

        public void add(){
            i++;
        }

        /**
         * 判断数字是否是奇数
         * @return 是奇数返回true
         */
        public boolean isOdd() {
            return i % 2 == 1;
        }

        @Override
        public String toString() {
            return i+"";
        }
    }

    static class R1 implements Runnable {
        private Num num;

        public R1(Num num) {
            this.num = num;
        }

        @Override
        public void run() {
            while (true) {

                synchronized (num) {

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    // 当数字是偶数，当前线程进入等待，并且释放num的对象锁
                    if(!num.isOdd()) {
                        try {
                            num.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    // 当数字是奇数
                    System.out.println(Thread.currentThread().getName() + " ====> " + num);
                    num.add();
                    // 唤醒T2线程
                    num.notify();
                }
            }
        }
    }

    static class R2 implements Runnable {
        private Num num;

        public R2(Num num) {
            this.num = num;
        }

        @Override
        public void run() {
            while (true) {

                synchronized (num) {

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    // 当数字是奇数，当前线程进入等待，并且释放num的对象锁
                    if(num.isOdd()) {
                        try {
                            num.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    // 当数字是偶数
                    System.out.println(Thread.currentThread().getName() + " ====> " + num);
                    num.add();
                    // 唤醒T1线程
                    num.notify();
                }
            }
        }
    }

}
