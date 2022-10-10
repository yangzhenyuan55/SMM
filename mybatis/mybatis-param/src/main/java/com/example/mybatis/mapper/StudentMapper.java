package com.example.mybatis.mapper;

import com.example.mybatis.pojo.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: yzy
 * @Date: 2022/9/23-21:13
 * @Description:
 */
public interface StudentMapper {
    /**
     * 当接口方法的参数只有一个（单个参数），并且参数的类型都是简单类型。
     * 根据id查询，根据name查询，根据birth查询，根据sex查询等等
     */
    List<Student> selectById(Long id);


    List<Student> selectByName(String name);

    List<Student> selectByBrith(Date date);

    List<Student> selectBySex(Character sex);

    /**
     * Map保存学生信息，然后通过Map将数据插入数据库
     * @param sutInfo 学生信息Map集合
     * @return 影响条数
     */
    int insertByMap(Map<String, Object> sutInfo);

    /**
     * 根据姓名和性别查询学生
     * @param name 姓名
     * @param sex 性别
     * @return 学生集合
     */
    List<Student> selectByNameAndSex(String name, Character sex);

    /**
     * 使用param注解来完成通过姓名和性别查询学生
     * @param name
     * @param sex
     * @return
     */
    List<Student> selectByNameAndSexUseAnnotation(@Param("name") String name, @Param("sex") Character sex);


}
