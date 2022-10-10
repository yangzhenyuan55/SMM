package com.example.mybatis.test;

import com.example.mybatis.mapper.StudentMapper;
import com.example.mybatis.pojo.Student;
import com.example.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author: yzy
 * @Date: 2022/9/23-21:18
 * @Description:
 */
public class StudentMapperTest {


    /**
     * 通过注解来指定参数的名字
     * 多个参数查询
     */
    @Test
    public void testSelectByNameAndSexUseAnnotation() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = studentMapper.selectByNameAndSexUseAnnotation("王红", '女');
        students.forEach(System.out::println);
        // Student{id=4, name='王红', age=25, height=1.65, *birth=Fri Sep 23 00:00:00 CST 2022, sex=女}


        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    /**
     * 多个参数的查询
     */
    @Test
    public void testSelectByNameAndSex() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = studentMapper.selectByNameAndSex("王红", '女');
        students.forEach(System.out::println);
        // Student{id=4, name='王红', age=25, height=1.65, birth=Fri Sep 23 00:00:00 CST 2022, sex=女}


        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testInsertByMap() throws ParseException {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        Map<String, Object> stuInfo = new HashMap<>();
        stuInfo.put("name", "花木兰");
        stuInfo.put("age", 25);
        stuInfo.put("height", 1.65);
        stuInfo.put("birth", new SimpleDateFormat("yyyy-MM-dd").parse("2000-07-11"));
        stuInfo.put("sex", '女');
        studentMapper.insertByMap(stuInfo);
        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectBySex() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = studentMapper.selectBySex('男');
        students.forEach(System.out::println);
        /*
        Student{id=1, name='张三', age=22, height=1.81, birth=Thu Dec 07 00:00:00 CST 2000, sex=男}
        Student{id=2, name='李四', age=23, height=1.75, birth=Wed Dec 08 00:00:00 CST 1999, sex=男}
         */

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectByBirth() throws ParseException {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("1999-12-08");
        List<Student> students = studentMapper.selectByBrith(date);
        students.forEach(System.out::println);
        System.out.println(date);
        // Student{id=2, name='李四', age=23, height=1.75, birth=Wed Dec 08 00:00:00 CST 1999, sex=男}


        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectByName() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = studentMapper.selectByName("李四");
        students.forEach(System.out::println);
        // Student{id=2, name='李四', age=23, height=1.75, birth=Wed Dec 08 00:00:00 CST 1999, sex=男}


        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);
    }

    @Test
    public void testSelectById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = studentMapper.selectById(1L);
        students.forEach(System.out::println);
        // Student{id=1, name='张三', age=22, height=1.81, birth=Thu Dec 07 00:00:00 CST 2000, sex=男}

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);

    }


}
