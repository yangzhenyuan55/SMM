package com.example.spring.sql;

/**
 * @Author: yzy
 * @Date: 2022/9/16-17:35
 * @Description:
 */
public interface Sql {
    String GET_PRICE_BY_BOOK_ID = "SELECT price FROM t_book WHERE book_id=?";
    String UPDATE_BOOK_STOCK = "UPDATE t_book SET stock=stock-1 WHERE book_id=?";
    String UPDATE_USER_BALANCE = "UPDATE t_user SET balance=balance-? WHERE user_id=?";
}
