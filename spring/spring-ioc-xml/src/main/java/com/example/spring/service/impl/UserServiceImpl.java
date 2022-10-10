package com.example.spring.service.impl;

import com.example.spring.dao.UserDao;
import com.example.spring.service.UserService;

/**
 * @Author: 杨镇远
 * @Date: 2022/9/6-17:32
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public void saveUser() {
        userDao.saveUser();
    }
}
