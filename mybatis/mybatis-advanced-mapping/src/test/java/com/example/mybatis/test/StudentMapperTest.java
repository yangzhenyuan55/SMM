package com.example.mybatis.test;

import com.example.mybatis.mapper.StudentMapper;
import com.example.mybatis.pojo.Student;
import com.example.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;




/**
 * @Author: yzy
 * @Date: 2022/9/30-17:22
 * @Description:
 */
public class StudentMapperTest {



    @Test
    public void testSelectByIdStep() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectByIdStep1(1);
        // System.out.println(student);
        // Student{sid=1, sname='张三', clazz=Clazz{cid=1000, cname='高三一班'}}
        // 当开启懒加载机制后，如果只输出学生名字时，并不需要班级信息，这个时候就不会去执行班级信息的查询（不使用的时候就不会执行查询语句）
        System.out.println(student.getSname());
        //  select sid,sname,cid from t_stu where sid=?
        // 张三
        // 只执行了一条SQL语句


        // 这个时候我想查看班级信息，他会再去执行相应的SQL语句
        System.out.println(student.getClazz());
        // 2022-10-03 14:00:32.582 [main] DEBUG c.e.mybatis.mapper.StudentMapper.selectByIdStep1 - ==>  Preparing: select sid,sname,cid from t_stu where sid=?
        //2022-10-03 14:00:32.625 [main] DEBUG c.e.mybatis.mapper.StudentMapper.selectByIdStep1 - ==> Parameters: 1(Integer)
        //2022-10-03 14:00:32.695 [main] DEBUG c.e.mybatis.mapper.StudentMapper.selectByIdStep1 - <==      Total: 1
        //张三
        //2022-10-03 14:00:32.697 [main] DEBUG c.e.mybatis.mapper.ClazzMapper.selectByIdStep2 - ==>  Preparing: select cid,cname from t_clazz where cid=?
        //2022-10-03 14:00:32.698 [main] DEBUG c.e.mybatis.mapper.ClazzMapper.selectByIdStep2 - ==> Parameters: 1000(Integer)
        //2022-10-03 14:00:32.699 [main] DEBUG c.e.mybatis.mapper.ClazzMapper.selectByIdStep2 - <==      Total: 1
        //Clazz{cid=1000, cname='高三一班'}
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void TestSelectByIdWithAssociation() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectByIdWithAssociation(1);
        System.out.println(student);
        // Student{sid=1, sname='张三', clazz=Clazz{cid=1000, cname='高三一班'}}

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectById(1);
        System.out.println(student);
        // select s.sid, s.sname,c.cid,c.cname from t_stu s left join t_clazz c on s.cid=c.cid where s.sid = ?
        // Student{sid=1, sname='张三', clazz=Clazz{cid=1000, cname='高三一班'}}

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

}
