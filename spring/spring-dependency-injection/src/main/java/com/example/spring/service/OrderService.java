package com.example.spring.service;

import com.example.spring.dao.OrderDao;

/**
 * @Author: yzy
 * @Date: 2022/10/29-21:38
 * @Description:
 */
public class OrderService {

    private OrderDao orderDao;


    public OrderService(){}

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void generateOrder() {
        orderDao.insert();
    }
}
