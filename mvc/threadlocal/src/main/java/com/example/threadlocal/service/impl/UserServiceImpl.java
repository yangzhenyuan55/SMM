package com.example.threadlocal.service.impl;

import com.example.threadlocal.dao.UserDao;
import com.example.threadlocal.dao.impl.UserDaoImpl;
import com.example.threadlocal.service.UserService;
import com.example.threadlocal.sql.MyConnection;
import com.example.threadlocal.utils.DBUtil;

/**
 * @Author: yzy
 * @Date: 2022/9/21-22:31
 * @Description:
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void save() {
        MyConnection conn = DBUtil.getConnection();
        System.out.println("UserService Connection: " + conn);
        userDao.insert();
    }
}
