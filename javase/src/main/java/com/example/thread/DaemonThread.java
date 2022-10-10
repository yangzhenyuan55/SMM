package com.example.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: yzy
 * @Date: 2022/10/6-14:07
 * @Description: 守护线程
 */
public class DaemonThread {
    public static void main(String[] args) throws ParseException {
        Thread t = new BakDataThread();
        t.setName("备份数据线程");
        // 希望main方法结束，这个备份线程也结束，可以将这个备份线程设置为守护线程
        // 即使是死循环，但由于该线程是守护线程，当用户线程结束，守护线程会自动终止
        t.setDaemon(true);
        t.start();


        // 主线程
        for (int i = 0; i < 10; i++) {
            System.out.println(
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
                            + " [" + Thread.currentThread().getName() + "] ====>" + (++i));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


class BakDataThread extends Thread{

    @Override
    public void run() {
        int i = 1;
        while(true) {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+ " [" + this.getName() + "] ====>" + (++i));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
