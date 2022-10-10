package com.example.bank.dao;

/**
 * @Author: yzy
 * @Date: 2022/9/22-16:59
 * @Description:
 */
public interface AccountDao {
    void delete();
    int insert(String actNo);
    int update(String actNo, Double balance);
    String selectByActNo(String actNo);
}
