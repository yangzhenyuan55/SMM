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

    List<Car> selectByType(String carType);

    /**
     * 查询所有汽车信息，并根据传入的参数判断升序排列还是降序排列
     * @param ascOrDesc 传入asc字符串升序，desc降序
     * @return 根据生产日期排列好的汽车集合
     */
    List<Car> selectAllOrderByProduceTime(String ascOrDesc);

    /**
     * 根据id批量删除数据
     * @param ids id集合，形如: 1,2,3,4,5
     * @return 影响条数
     */
    int deleteBatch(String ids);

    /**
     * 根据汽车品牌进行模糊查询
     * @param brand 品牌
     * @return 同一个品牌的汽车集合
     */
    List<Car> selectByBrandLike(String brand);

    /**
     * 插入数据并且获取自动生成的主键，将其赋值给car的id属性
     * @param car 汽车实体类
     * @return 返回影响条数
     */
    int insertCarUseGeneratedKeys(Car car);

}

























