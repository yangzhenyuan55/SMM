package com.example.mybatis.bank.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * @Author: yzy
 * @Date: 2022/9/20-17:59
 * @Description:
 */
public class SqlSessionUtil {


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
     * 构造方法私有化，防止实例化
     */
    private SqlSessionUtil() {
    }

    private static ThreadLocal<SqlSession> local = new ThreadLocal<>();

    /**
     * 获取与数据库的会话对象
     * @return 会话对象
     */
    public static SqlSession openSession() {
        SqlSession sqlSession = local.get();
        if(sqlSession == null) {
            sqlSession = sqlSessionFactory.openSession();
            local.set(sqlSession);
        }

        return sqlSession;
    }

    public static void close(SqlSession sqlSession){
        if (sqlSession != null) {
            sqlSession.close();
            // 从当前线程中移除SqlSession对象
            local.remove();
        }
    }
}
