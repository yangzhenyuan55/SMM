package com.example.mybatis.bank.exception;

/**
 * @Author: yzy
 * @Date: 2022/9/22-15:31
 * @Description:
 */
public class TransferAccountException extends Exception{
    public TransferAccountException(){}

    public TransferAccountException(String msg) {
        super(msg);
    }

}
