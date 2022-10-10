package com.example.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author: yzy
 * @Date: 2022/10/6-14:41
 * @Description: 定时器，间隔一定时间执行特定的程序
 */
public class TimerTest {
    public static void main(String[] args) throws ParseException {
        Timer logTimer = new Timer("Log");
        logTimer.schedule(new LogTimerTask(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-10-06 15:04:00"), 1000 * 2);

    }
}

class LogTimerTask extends TimerTask {

    @Override
    public void run() {
        // 编写需要执行的任务
        String time = new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date());
        System.out.println(time + " [" + Thread.currentThread().getName() + "] " + "====> Log");
    }
}
