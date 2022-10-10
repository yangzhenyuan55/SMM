package com.example.threadlocal.utils;

import com.example.threadlocal.sql.MyConnection;

/**
 * @Author: yzy
 * @Date: 2022/9/21-22:52
 * @Description:
 */
public class DBUtil {
    private static MyThreadLocal<MyConnection> local = new MyThreadLocal<>();

    public static MyConnection getConnection(){
        MyConnection conn = local.get();
        if(conn == null) {
            // 第一次调用get()一定是空
            conn = new MyConnection();
            local.set(conn);
        }
        return conn;
    }
}
