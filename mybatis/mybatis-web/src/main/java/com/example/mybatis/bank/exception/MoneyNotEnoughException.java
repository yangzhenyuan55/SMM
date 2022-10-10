package com.example.mybatis.bank.exception;

/**
 * @Author: yzy
 * @Date: 2022/9/22-15:24
 * @Description:
 */
public class MoneyNotEnoughException extends Exception{
    public MoneyNotEnoughException(){}

    public MoneyNotEnoughException(String msg) {
        super(msg);
    }
}
