package com.example.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: yzy
 * @Date: 2022/9/19-23:00
 * @Description: MyBatis工具类
 */
public class SqlSessionUtil {

    /**
     * 构造方法私有化，防止实例化
     */
    private SqlSessionUtil() {
    }

    private static SqlSessionFactory sqlSessionFactory;

    static {
        // 类加载时创建SqlSessionFactory对象

        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取与数据库的会话对象
     * @return 会话对象
     */
    public static SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }



}
