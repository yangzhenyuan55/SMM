package com.example.spring.service;

/**
 * @Author: yzy
 * @Date: 2022/9/19-14:26
 * @Description:
 */
public interface CheckoutService {
    /**
     * 结账购买多本书
     * @param userId 用户id
     * @param bookIds 书id
     */
    void checkout(Integer userId, Integer[] bookIds);
}
