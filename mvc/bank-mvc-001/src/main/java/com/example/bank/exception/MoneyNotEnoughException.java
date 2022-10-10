package com.example.bank.exception;

/**
 * @Author: yzy
 * @Date: 2022/9/21-17:14
 * @Description: 余额不足异常
 */
public class MoneyNotEnoughException extends Exception{

    public MoneyNotEnoughException(){}

    public MoneyNotEnoughException(String msg){
        super(msg);
    }
}
