package com.example.spring.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: yzy
 * @Date: 2022/10/29-21:20
 * @Description:
 */
public class OrderDao {

    private static final Logger logger = LoggerFactory.getLogger(OrderDao.class);


    public OrderDao(){}

    public void insert(){
        logger.info("正在生成订单...");
    }

}
