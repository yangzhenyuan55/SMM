package com.example.spring.service;

/**
 * @Author: yzy
 * @Date: 2022/9/16-17:11
 * @Description:
 */
public interface BookService {
    /**
     * 买书
     * @param userId 用户id
     * @param bookId 书的id
     */
    void buyBook(Integer userId, Integer bookId);
}
