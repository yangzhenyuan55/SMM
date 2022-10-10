package com.example.bank.service;

import com.example.bank.exception.MoneyNotEnoughException;

/**
 * @Author: yzy
 * @Date: 2022/9/21-19:45
 * @Description:
 */
public interface AccountService {
    /**
     * 转账
     * @param fromActNo 转出账户
     * @param toActNo 转入账户
     * @param money 转账金额
     */
    void transferAccounts(String fromActNo, String toActNo, Double money) throws Exception;


}
