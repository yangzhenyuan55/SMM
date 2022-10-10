package com.example.mybatis.test;

import com.example.mybatis.mapper.CarMapper;
import com.example.mybatis.pojo.Car;
import com.example.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @Author: yzy
 * @Date: 2022/9/23-12:57
 * @Description:
 */
public class CarMapperTest {

    @Test
    public void testInsert() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        // 获取CarMapper实现类(面向接口，获取接口的代理对象，接口的实现类)
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(null, "8654", "比亚迪汉", 20.0, "2021-12-14", "燃油车");
        int count = carMapper.insert(car);
        System.out.println(count);

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testDeleteById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        // 获取CarMapper实现类(面向接口，获取接口的代理对象，接口的实现类)
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        int count = carMapper.deleteById(15L);
        System.out.println(count);

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testUpdate() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        // 获取CarMapper实现类(面向接口，获取接口的代理对象，接口的实现类)
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(14L, "8654", "比亚迪汉", 20.0, "2021-12-14", "燃油车");
        int cnt = carMapper.update(car);
        System.out.println(cnt);

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        // 获取CarMapper实现类(面向接口，获取接口的代理对象，接口的实现类)
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Car car = carMapper.selectById(14L);
        System.out.println(car); // Car{id=14, carNum='8654', brand='比亚迪汉', guidePrice=20.0, produceTime='2021-12-14', carType='燃油车'}

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        // 获取CarMapper实现类(面向接口，获取接口的代理对象，接口的实现类)
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> carList = carMapper.selectAllCar();
        carList.forEach(System.out::println);

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }


}
