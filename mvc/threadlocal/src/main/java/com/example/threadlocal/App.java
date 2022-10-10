package com.example.threadlocal;

import com.example.threadlocal.service.UserService;
import com.example.threadlocal.service.impl.UserServiceImpl;
import com.example.threadlocal.sql.MyConnection;
import com.example.threadlocal.utils.MyThreadLocal;

/**
 * @Author: yzy
 * @Date: 2022/9/21-22:31
 * @Description:
 */
public class App {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        MyThreadLocal<MyConnection> threadLocal = new MyThreadLocal<>();

        userService.save();


    }
}
