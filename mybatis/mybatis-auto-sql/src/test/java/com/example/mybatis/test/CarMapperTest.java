package com.example.mybatis.test;

import com.example.mybatis.mapper.CarMapper;
import com.example.mybatis.pojo.Car;
import com.example.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: yzy
 * @Date: 2022/9/29-22:16
 * @Description:
 */
public class CarMapperTest {


    @Test
    public void testInsertBatch() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = Arrays.asList(
                new Car(null, "1007", "比亚迪秦", 20.0, "2022-12-23", "新能源"),
                new Car(null, "1008", "比亚迪汉", 12.0, "2022-12-22", "新能源")
        );

        int count = mapper.insertBatch(cars);
        System.out.println("插入条数: " + count);

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    /**
     * foreach标签
     */
    @Test
    public void testDeleteById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        int count = mapper.deleteById(new Long[]{16L, 17L});
        System.out.println("删除条数:" + count);

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }


    /**
     * choose标签
     */
    @Test
    public void testSelectByChoose() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByChoose("", null, "燃油车");
        cars.forEach(System.out::println);

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    /**
     * 使用set标签更新数据
     */
    @Test
    public void testUpdateBySet() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(1L, "", "", 20.0, null, "");
        int count = mapper.update(car);
        // SQL 语句
        // update t_car SET guide_price = ? where id=?

        // 更新前

        System.out.println("影响条数: " + count);

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }


    @Test
    public void testSelectByMultiConditionWithTrim() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByMultiConditionWithTrim("", 20.0, "燃油车");
        cars.forEach(System.out::println);


        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    /**
     * where标签
     */
    @Test
    public void testSelectByMultiConditionWithWhere() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByMultiConditionWithWhere("", 20.0, "燃油车");
        cars.forEach(System.out::println);

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectByMultiCondition() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByMultiCondition("比亚迪", null, "");
        cars.forEach(System.out::println);
        // 三个条件都不为空
        //Car{id=16, carNum='1007', brand='比亚迪秦', guidePrice=30.0, produceTime='2022-12-23', carType='新能源'}
        //Car{id=17, carNum='1008', brand='比亚迪汉', guidePrice=35.0, produceTime='2022-12-24', carType='新能源'}

        // 三个条件都为空
        // sql 语句为：select * from t_car where
        // 多出一个where，但是后面没有条件，所以出错
        // 解决方法，在where子句后面加一个 1=1
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

}
