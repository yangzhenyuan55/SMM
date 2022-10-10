package com.example.spring.dao;

/**
 * @Author: yzy
 * @Date: 2022/9/16-17:13
 * @Description:
 */
public interface BookDao {

    /**
     * 根据书的id来获取书的价格
     * @param bookId 书的id
     */

    Integer getPriceByBookId(Integer bookId);

    /**
     * 更新书的库存
     * @param bookId 书的id
     */
    void updateBookStock(Integer bookId);

    /**
     * 更新用户的余额
     * @param userId 用户id
     * @param bookPrice 书的价格
     */
    void updateUserBalance(Integer userId, int bookPrice);
}
