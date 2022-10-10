package com.example.bank.dao;

import com.example.bank.pojo.Account;
import com.example.bank.utils.DBUtil;

import java.sql.Connection;
import java.util.List;

/**
 * @Author: yzy
 * @Date: 2022/9/21-18:58
 * @Description:
 */
public interface Dao<T> {

    /**
     * 向数据库中插入t
     * @param t 实体类
     * @return 返回影响记录条数
     */
    Integer insert(T t);

    /**
     * 根据账号删除数据库中的数据
     * @param actNo 账号
     * @return 影响数据库条数
     */
    Integer deleteByActNo(String actNo);

    /**
     * 根据id删除数据
     * @param id 用户id
     * @return 影响条数
     */
    Integer deleteById(Long id);

    /**
     * 更新账户
     * @param account 账户实体
     * @return 影响条数
     */
    Integer update(Account account);

    /**
     * 根据账号查询数据
     * @param actNo 账号
     * @return 将查询得到的数据封装成对象返回
     */
    public T selectByActNo(String actNo);

    /**
     * 查询表中所有数据
     * @return 封装成List集合返回
     */
    public List<T> selectAll();


    void setAutoCommit(boolean autoCommit);

    void rollBack();

    void commit();

    void closeConnection();
}
