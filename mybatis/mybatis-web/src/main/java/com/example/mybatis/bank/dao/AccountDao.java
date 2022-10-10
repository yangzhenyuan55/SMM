package com.example.mybatis.bank.dao;

import com.example.mybatis.bank.pojo.Account;

/**
 * @Author: yzy
 * @Date: 2022/9/22-15:11
 * @Description:
 */
public interface AccountDao {

    /**
     * 根据账号查询账户
     * @param actNo 账号
     * @return 返回一个账户类
     */
    Account selectByActNo(String actNo);

    /**
     * 更新账户信息
     * @param act 账户
     * @return 返回影响条数
     */
    int updateByActNo(Account act);
}
