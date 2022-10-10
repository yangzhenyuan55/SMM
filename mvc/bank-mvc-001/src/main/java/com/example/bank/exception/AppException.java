package com.example.bank.exception;

/**
 * @Author: yzy
 * @Date: 2022/9/21-17:38
 * @Description:
 */
public class AppException extends Exception{

    public AppException(){}

    public AppException(String msg){
        super(msg);
    }
}
