package com.example.mybatis.test;

import com.example.mybatis.mapper.CarMapper;
import com.example.mybatis.pojo.Car;
import com.example.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;

/**
 * @Author: yzy
 * @Date: 2022/10/3-17:43
 * @Description:
 */
public class CarMapperTest {


    /**
     * 一级缓存测试：使用同一个SqlSession对象来查询
     */
    @Test
    public void testOneLevelCache1() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car1 = mapper.selectById(20L);
        System.out.println("第一次查询: ");
        System.out.println(car1);
        // 第一次查询
        // Car{id=20, carNum='1008', brand='比亚迪汉', guidePrice=12.0, produceTime='2022-12-22', carType='新能源'}

        Car car2 = mapper.selectById(20L);
        System.out.println("第二次查询: ");
        System.out.println(car2);

        //2022-10-03 17:48:06.341 [main] DEBUG com.example.mybatis.mapper.CarMapper.selectById - ==>  Preparing: select * from t_car where id=?
        //2022-10-03 17:48:06.372 [main] DEBUG com.example.mybatis.mapper.CarMapper.selectById - ==> Parameters: 20(Long)
        //2022-10-03 17:48:06.399 [main] DEBUG com.example.mybatis.mapper.CarMapper.selectById - <==      Total: 1
        //第一次查询:
        //Car{id=20, carNum='1008', brand='比亚迪汉', guidePrice=12.0, produceTime='2022-12-22', carType='新能源'}
        //第二次查询:
        //Car{id=20, carNum='1008', brand='比亚迪汉', guidePrice=12.0, produceTime='2022-12-22', carType='新能源'}
        // 并未去执行一条新的SQL语句，而是直接输出
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    /**
     * 测试一级缓存：使用不同的SqlSession对象来查询
     */
    @Test
    public void testOneLevelCache2() throws Exception {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        // 分别创建两个SqlSession对象
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();

        CarMapper mapper1 = sqlSession1.getMapper(CarMapper.class);
        CarMapper mapper2 = sqlSession2.getMapper(CarMapper.class);

        System.out.println(mapper1.selectById(20L));
        System.out.println(mapper2.selectById(20L));
        //2022-10-03 18:01:05.677 [main] DEBUG com.example.mybatis.mapper.CarMapper.selectById - ==>  Preparing: select * from t_car where id=?
        //2022-10-03 18:01:05.711 [main] DEBUG com.example.mybatis.mapper.CarMapper.selectById - ==> Parameters: 20(Long)
        //2022-10-03 18:01:05.741 [main] DEBUG com.example.mybatis.mapper.CarMapper.selectById - <==      Total: 1
        //Car{id=20, carNum='1008', brand='比亚迪汉', guidePrice=12.0, produceTime='2022-12-22', carType='新能源'}

        //2022-10-03 18:01:05.873 [main] DEBUG com.example.mybatis.mapper.CarMapper.selectById - ==>  Preparing: select * from t_car where id=?
        //2022-10-03 18:01:05.874 [main] DEBUG com.example.mybatis.mapper.CarMapper.selectById - ==> Parameters: 20(Long)
        //2022-10-03 18:01:05.876 [main] DEBUG com.example.mybatis.mapper.CarMapper.selectById - <==      Total: 1
        //Car{id=20, carNum='1008', brand='比亚迪汉', guidePrice=12.0, produceTime='2022-12-22', carType='新能源'}

        // 由此可见，两个不同的SqlSession对象就会执行两次SQL语句，说明了，两个SqlSession对象就会有两个不同的缓存
    }

    /**
     * 一级缓存失效情况:
     * 1、手动清空缓存
     * 2、在两次相同的查询之间执行了insert、delete、update操作
     */
    @Test
    public void testOneLevelCache3() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car1 = mapper.selectById(20L);
        System.out.println(car1);
        // 手动清空缓存
        // sqlSession.clearCache();

        // 执行insert（delete，update）语句
        mapper.insert(new Car(null, "1009", "比亚迪宋", 15.0, "2020-12-11", "燃油车"));

        Car car2 = mapper.selectById(20L);
        System.out.println(car2);

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }
}
