package com.example.mybatis.mapper;

import com.example.mybatis.pojo.Car;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Author: yzy
 * @Date: 2022/9/23-13:03
 * @Description:
 */
public interface CarMapper {

    /**
     * 批量插入多条记录
     * @param cars
     * @return
     */
    int insertBatch(@Param("cars") List<Car> cars);

    /**
     * 根据id来删除表中数据，批量删除
     * @param ids
     * @return
     */
    int deleteById(@Param("ids") Long[] ids);

    /**
     * 使用choose标签来查询
     * @param brand
     * @param guidePrice
     * @param carType
     * @return
     */
    List<Car> selectByChoose(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);


    /**
     * 使用set标签来实现update操作
     * 当我们不需要更新某个字段的时候，可以传入null或者空字符串
     * 使用if标签来实现传入的数据中为空的将不进行更新操作
     * @param car
     * @return
     */
    int update(Car car);

    /**
     * 使用trim来实现多条件查询
     * @param brand
     * @param guidePrice
     * @param carType
     * @return
     */
    List<Car> selectByMultiConditionWithTrim(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    /**
     * 多条件查询(使用if标签来实现动态SQL)
     *
     * @param brand      品牌
     * @param guidePrice 指导价
     * @param carType    汽车类型
     * @return
     */
    List<Car> selectByMultiCondition(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    /**
     * 使用where来实现多条件查询
     * @param brand
     * @param guidePrice
     * @param carType
     * @return
     */
    List<Car> selectByMultiConditionWithWhere(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);
}

























