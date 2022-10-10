package com.example.mybatis.mapper;

import com.example.mybatis.pojo.Car;

import java.util.List;

/**
 * @Author: yzy
 * @Date: 2022/9/23-13:03
 * @Description:
 */
public interface CarMapper {

    /**
     * 新增Car
     * @param car 车实体类
     * @return 数据库影响条数
     */
    int insert(Car car);

    /**
     * 根据id删除车
     * @param id
     * @return
     */
    int deleteById(Long id);


    int update(Car car);


    Car selectById(Long id);

    /**
     * 获取所有的汽车
     * @return
     */
    List<Car> selectAllCar();


}
