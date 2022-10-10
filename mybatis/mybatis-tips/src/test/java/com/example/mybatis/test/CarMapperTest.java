package com.example.mybatis.test;

import com.example.mybatis.mapper.CarMapper;
import com.example.mybatis.pojo.Car;
import com.example.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @Author: yzy
 * @Date: 2022/9/23-13:52
 * @Description:
 */
public class CarMapperTest {

    /**
     * 插入数据并且获取自动生成的主键，将其赋值给car的id属性
     */
    @Test
    public void testInsertCarUseGeneratedKeys() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(null, "8654", "凯美瑞", 20.0, "2021-12-14", "燃油车");
        int cnt = carMapper.insertCarUseGeneratedKeys(car);
        System.out.println("影响条数: " + cnt);
        System.out.println(car);
        /*
        影响条数: 1
        Car{id=18, carNum='8654', brand='凯美瑞', guidePrice=20.0, produceTime='2021-12-14', carType='燃油车'}
         */
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    /**
     * 对汽车品牌进行模糊查询
     */
    @Test
    public void testSelectByBrandLike() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> carList = carMapper.selectByBrandLike("比亚迪");
        carList.forEach(System.out::println);

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectByCarType() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> carList = carMapper.selectByType("燃油车");
        carList.forEach(System.out::println);
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectAllOrderByProduceTime() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);

        List<Car> carList = carMapper.selectAllOrderByProduceTime("desc");
        carList.forEach(System.out::println);


        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testDeleteBatch(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        int count = carMapper.deleteBatch("13,14");
        System.out.println(count);


        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }
}
