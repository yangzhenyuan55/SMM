package com.example.mybatis.test;


import com.example.mybatis.pojo.Car;
import com.example.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yzy
 * @Date: 2022/9/19-23:41
 * @Description: 对t_car表进行增删改查
 */
public class CarMapperTest {
    /**
     * 插入
     */
    @Test
    public void testInsertCarByMap() {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("carNum", "1008");
        dataMap.put("brand", "比亚迪秦");
        dataMap.put("guidePrice", 10);
        dataMap.put("produceTime", "2020-11-11");
        dataMap.put("carType", "电车");

        // insert() 方法的参数
        // 第一个参数：statement ---- 指定sql语句在配置文件中的id
        // 第二个参数：object ----- 封装数据的对象（传入的对象的属性对应数据库中的字段然后进行数据插入），先使用Map来进行数据封装
        sqlSession.insert("insertCar", dataMap);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 通过实例化对象来插入数据
     * 例如插入car数据，通过实例化一个Car对象来插入数据
     */
    @Test
    public void testInsertByPojo() {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        Car car = new Car(null, "1009", "比亚迪明", 30.0, "2020-01-12", "新能源");

        sqlSession.insert("insertCar", car);
        sqlSession.commit();
        sqlSession.close();
    }


    @Test
    public void testDeleteById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        sqlSession.delete("deleteById", 12);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdateById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        sqlSession.update("updateById", new Car(14L, "9999", "凯美瑞", 30.3, "1999-11-10", "燃油车"));
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        Object car = sqlSession.selectOne("selectById", 13);
        System.out.println("查询结果: " + car);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        List<Object> cars = sqlSession.selectList("selectAll");
        cars.forEach(car -> System.out.println(car));

        sqlSession.commit();
        sqlSession.close();
    }
}
