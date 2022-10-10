package com.example.spring.controller;

import com.example.spring.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author yzy
 * @date 2022/09/07
 */
@Controller(value = "userController") // 可以在此设置bean的id
public class UserController {


    private UserService userService;

    public UserController() {
        System.out.println("构造方法执行");
    }

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    public void saveUser() {
        userService.saveUser();
    }
}
