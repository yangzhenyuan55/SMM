package com.example.spring.dao.impl;

import com.example.spring.dao.UserDao;
import org.springframework.stereotype.Repository;

/**
 * @Author: 杨镇远
 * @Date: 2022/9/6-18:33
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Override
    public void saveUser() {
        System.out.println("保存成功");
    }
}


