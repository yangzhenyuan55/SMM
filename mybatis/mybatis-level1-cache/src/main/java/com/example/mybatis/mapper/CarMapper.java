package com.example.mybatis.mapper;

import com.example.mybatis.pojo.Car;

/**
 * @Author: yzy
 * @Date: 2022/10/3-17:40
 * @Description:
 */
public interface CarMapper {

    /**
     * 根据id获取car信息
     * @param id car的id
     * @return
     */
    Car selectById(Long id);


    int insert(Car car);
}
