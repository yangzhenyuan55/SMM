package com.example.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: yzy
 * @Date: 2022/9/19-21:38
 * @Description: 采用正规的方式写一个MyBatis程序
 */
public class MyBatisCompleteTest {
    public static void main(String[] args) {
        // 完整的MyBatis程序
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = null;
        SqlSession sqlSession = null;
        try {
            sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsReader("mybatis-config.xml"));
            sqlSession = sqlSessionFactory.openSession();
            sqlSession.insert("insertCar");
        } catch (Exception e) {
            if(sqlSession != null) {
                sqlSession.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }

    }
}
