package com.example.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: yzy
 * @Date: 2022/10/6-20:29
 * @Description: 实现线程的第三种方式
 */
public class CallableImplTest {
    public static void main(String[] args) throws Exception {
        // 第一步：创建一个“未来任务类”对象
        FutureTask<String> task = new FutureTask<>(() -> {

            System.out.println(Thread.currentThread().getName() + " ====> Hello, world");
            Thread.sleep(1000 * 3);
            return "Hello, world";
        });

        Thread t = new Thread(task);
        t.setName("t");
        t.start();

        // 主线程中，获取t线程的返回结果
        // 这个方法会导致主线程阻塞，因为主线程会等待get()方法结束
        // 这个方法的效率比较低
        String result = task.get();
        System.out.println(Thread.currentThread().getName() + " ====> " + result);

    }
}


