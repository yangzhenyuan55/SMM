package com.example.spring.service;

import com.example.spring.dao.UserDao;

/**
 * @Author: yzy
 * @Date: 2022/10/29-20:12
 * @Description:
 */
public class UserService {

    private UserDao userDao;

    public UserService() {}


    public UserService(UserDao userDao) {

        this.userDao = userDao;
    }



    public void setUserDao(UserDao userDao) {

        this.userDao = userDao;
    }

    public void saveUser() {
        userDao.insert();
    }
}
