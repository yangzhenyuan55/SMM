package com.example.spring.service.impl;

import com.example.spring.dao.UserDao;
import com.example.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @Author: 杨镇远
 * @Date: 2022/9/6-18:34
 */
@Service("userService")
public class UserServiceImpl implements UserService {


    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void saveUser() {
        userDao.saveUser();
    }
}
