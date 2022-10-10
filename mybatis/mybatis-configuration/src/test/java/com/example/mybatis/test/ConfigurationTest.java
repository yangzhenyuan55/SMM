package com.example.mybatis.test;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;

/**
 * @Author: yzy
 * @Date: 2022/9/20-18:35
 * @Description:
 */
public class ConfigurationTest {

    @Test
    public void testEnvironment() throws IOException {
        // 获取SqlSessionFactory对象(采用默认的方式)
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        // 获取default的环境(数据库)
        SqlSessionFactory sqlSessionFactory1 = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        // 通过环境的id来获取对应的环境(数据库)
        SqlSessionFactory sqlSessionFactory2 = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"), "company");
        System.out.println(sqlSessionFactory1.getConfiguration().getEnvironment().getId()); // powernode
        System.out.println(sqlSessionFactory2.getConfiguration().getEnvironment().getId()); // company


    }

    /**
     * 测试dataSource=UNPOOLED和POOLED的区别
     */
    @Test
    public void testDataSource() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));

        SqlSession sqlSession1 = sqlSessionFactory.openSession();

        Object car1 = sqlSession1.selectOne("selectById", 13);
        System.out.println(car1);
        sqlSession1.commit();
        sqlSession1.close();


        SqlSession sqlSession2 = sqlSessionFactory.openSession();

        Object car2 = sqlSession2.selectOne("selectById", 13);
        System.out.println(car2);
        sqlSession2.commit();
        sqlSession2.close();

        // 当dataSource=UNPOOLED ，也就是说不使用连接池
        // sqlSession1的连接对象Connection [com.mysql.cj.jdbc.ConnectionImpl@609e8838]
        // sqlSession2的连接对象 JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@821330f]
        // 两次获取的Connection不是同一个

        // 当dataSource=POOLED，使用连接池
        // sqlSession1的连接对象 JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@792b749c]
        // sqlSession2的连接对象 JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@792b749c]
        // 由此可见，当使用连接池时，两次会话获取的都是同一个连接对象
    }

    /**
     * 测试POOLED的属性
     * poolMaximumActiveConnections --- 最大连接数量 ,default=10
     * poolTimeToWait ---- 如果获取连接花费了相当长的时间，连接池会打印状态日志并重新尝试获取一个连接,设置打印日志时间，并且尝试获取连接
     * poolMaximumCheckoutTime ---- 超时时间，在被强制返回之前，池中连接被检出（checked out）时间，默认值：20000 毫秒（即 20 秒）,在20秒后还未取得连接就退出
     * poolMaximumIdleConnections ---- 任意时间可能存在的空闲连接数。
     * (
     * 假设最多的连接数量为 10 个， 最多空闲数量为 5 个；假设当前已经有5个连接是空闲的了，马上就要第6个空闲了。
     * 假设第6个空闲下来了，此时连接池为了保证最多空闲数量始终为 5个，会真正关闭多余的空闲的连接对象
     * )
     */
    @Test
    public void testPOOLEDConfiguration() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));

        // 此时 poolMaximumActiveConnections = 3
        // 模拟四个连接，前三个连接但是不关闭，看看第四个是否会等待有一个关闭然后才连接
        Integer id = 7;
        for (int i = 0; i < 4; i++) {
            SqlSession sqlSession = sqlSessionFactory.openSession();
            Object car2 = sqlSession.selectOne("selectById", id++);
            System.out.println(car2);
            // 不关闭连接
        }
    }
}
