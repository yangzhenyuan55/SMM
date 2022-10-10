package com.example.mybatis.test;

import com.example.mybatis.mapper.LogMapper;
import com.example.mybatis.pojo.Log;
import com.example.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @Author: yzy
 * @Date: 2022/9/23-14:57
 * @Description:
 */
public class LogMapperTest {

    @Test
    public void selectAllByDate() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        LogMapper logMapper = sqlSession.getMapper(LogMapper.class);
        List<Log> logList = logMapper.selectAllByDate("20220922");

        logList.forEach(System.out::println);

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);

    }

}
