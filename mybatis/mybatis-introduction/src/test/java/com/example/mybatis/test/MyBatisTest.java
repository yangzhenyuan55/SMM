package com.example.mybatis.test;

import com.example.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author: yzy
 * @Date: 2022/9/19-21:56
 * @Description:
 */
public class MyBatisTest {

    @Test
    public void testMyBatisInsert() {
        // 使用封装的工具类来插入数据
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionUtil.openSession();
            sqlSession.insert("insertCar");
            sqlSession.commit();
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
