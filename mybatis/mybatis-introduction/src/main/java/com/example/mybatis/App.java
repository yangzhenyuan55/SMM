package com.example.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: yzy
 * @Date: 2022/9/19-17:20
 * @Description: Mybatis测试程序
 */
public class App {
    public static void main(String[] args) {
        try {
            SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
            // getResourceAsStream() 方法就是从类路径下查找资源
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            // 下面这种方法同样成功，其实Resources.getResourceAsStream底层调用的就是下面的方法
            // InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sessionFactory = factoryBuilder.build(is);
            // 开启会话
            SqlSession sqlSession = sessionFactory.openSession();
            // 返回值是影响数据库表中的记录条数
            int cnt = sqlSession.insert("insertCar");
            System.out.println("插入了" + cnt + "条记录");
            // 不会自动提交，需要手动提交，也可以在openSession()方法传入参数true来开启自动提交，但是并不建议，因为自动提交事务是没有开启的
            sqlSession.commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {

        }
    }
}
