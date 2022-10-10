package com.example.threadlocal.dao.impl;

import com.example.threadlocal.dao.UserDao;
import com.example.threadlocal.sql.MyConnection;
import com.example.threadlocal.utils.DBUtil;

/**
 * @Author: yzy
 * @Date: 2022/9/21-22:31
 * @Description:
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void insert() {
        MyConnection conn = DBUtil.getConnection();
        System.out.println("UserDao Connection: " + conn);
        System.out.println("User Dao insert");

    }
}
