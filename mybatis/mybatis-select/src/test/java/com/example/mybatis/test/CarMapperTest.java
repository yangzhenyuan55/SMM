package com.example.mybatis.test;

import com.example.mybatis.mapper.CarMapper;
import com.example.mybatis.pojo.Car;
import com.example.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @Author: yzy
 * @Date: 2022/9/29-20:06
 * @Description:
 */
public class CarMapperTest {

    @Test
    public void testGetTotal() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Long total = carMapper.getTotal();
        System.out.println("总记录条数: " + total);

        SqlSessionUtil.close(sqlSession);
    }


    @Test
    public void testSelectAllByMapUnderscoreToCameCase() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = carMapper.selectAllByMapUnderscoreToCamelCase();
        cars.forEach(System.out::println);


        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectAllByResultMap(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = carMapper.selectAllByResultMap();
        cars.forEach(System.out::println);


        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectAllRetMapIdAsKey() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Map<Long, Map<String, Object>> maps = carMapper.selectAllRetMapIdAsKey();

        maps.entrySet().forEach(System.out::println);

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    /**
     * 返回一个存放Map集合的List集合
     */
    @Test
    public void testSelectAllRetMapList() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Map<String, Object>> maps = carMapper.selectAllRetMapList();
        maps.forEach(System.out::println);

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    /**
     * 根据id查询，返回唯一一个Map集合
     */
    @Test
    public void testSelectByIdRetMap() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);

        Map<String, Object> carMap = carMapper.selectByIdRetMap(17L);

        carMap.entrySet().forEach(entry ->{
            System.out.println(entry.getKey() + ": " + entry.getValue());
        });
        /*
            car_num: 1008
            id: 17
            guide_price: 35.00
            produce_time: 2022-12-24
            brand: 比亚迪汉
            car_type: 新能源
         */

        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectByBrandLike() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = carMapper.selectByBrandLike("比亚迪");
        System.out.println(cars);
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    /**
     * 获取所有的Car
     */
    @Test
    public void testSelectAll() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = carMapper.selectAll();
        cars.forEach(System.out::println);

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }


    /**
     * 获取一个Car
     */
    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper carMapper = sqlSession.getMapper(CarMapper.class);
        Car car = carMapper.selectById(17L);
        System.out.println(car);

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }
}
