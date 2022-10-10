package com.example.reflect.service;

/**
 * @Author: yzy
 * @Date: 2022/9/8-18:52
 * @Description:
 */
public class UserService {

    /**
     * 登录
     * @param id 用户id
     * @param pwd 用户密码
     * @return 成功返回true，失败返回false
     */
    public boolean login(String id, String pwd) {
        return "admin".equals(id) && "123".equals(pwd);
    }

    public void logout() {
        System.out.println("成功退出");
    }

}
