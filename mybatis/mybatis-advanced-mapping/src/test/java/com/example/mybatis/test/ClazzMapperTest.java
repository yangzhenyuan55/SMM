package com.example.mybatis.test;

import ch.qos.logback.classic.Logger;
import com.example.mybatis.mapper.ClazzMapper;
import com.example.mybatis.pojo.Clazz;
import com.example.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;



/**
 * @Author: yzy
 * @Date: 2022/9/30-17:24
 * @Description:
 */
public class ClazzMapperTest {
    @Test
    public void testSelectByStep(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ClazzMapper mapper = sqlSession.getMapper(ClazzMapper.class);
        Clazz clazz = mapper.selectByIdWithStep(1001);
        // 延迟加载

        System.out.println(clazz.getCname());

        System.out.println(clazz.getStudentList());
        // 2022-10-03 15:41:58.569 [main] DEBUG c.e.mybatis.mapper.ClazzMapper.selectByIdWithStep - <==      Total: 1
        //高三二班
        //2022-10-03 15:41:58.572 [main] DEBUG c.e.m.mapper.StudentMapper.selectByIdWithStep - ==>  Preparing: select sid,sname from t_stu where cid = ?
        //2022-10-03 15:41:58.573 [main] DEBUG c.e.m.mapper.StudentMapper.selectByIdWithStep - ==> Parameters: 1001(Integer)
        //2022-10-03 15:41:58.574 [main] DEBUG c.e.m.mapper.StudentMapper.selectByIdWithStep - <==      Total: 2
        //[Student{sid=4, sname='赵六', clazz=null}, Student{sid=5, sname='田七', clazz=null}]
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);


    }


    @Test
    public void testSelectByCollection(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ClazzMapper mapper = sqlSession.getMapper(ClazzMapper.class);
        Clazz clazz = mapper.selectByCollection(1001);
        System.out.println(clazz);
        // Clazz{cid=1001, cname='高三二班', studentList=[Student{sid=4, sname='赵六', clazz=null}, Student{sid=5, sname='田七', clazz=null}]}

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }


}
