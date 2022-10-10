package com.example.mybatis.bank.service;

import com.example.mybatis.bank.exception.MoneyNotEnoughException;
import com.example.mybatis.bank.exception.TransferAccountException;

/**
 * @Author: yzy
 * @Date: 2022/9/22-15:04
 * @Description: 账户业务，负责处理账户相关业务
 */
public interface AccountService {
    /**
     * 转账
     * @param fromActNo 转出账户账号
     * @param toActNo 转入账户账号
     * @param money 转账金额
     */
    void transferAccount(String fromActNo, String toActNo, Double money) throws MoneyNotEnoughException, TransferAccountException;
}
