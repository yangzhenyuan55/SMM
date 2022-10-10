package com.example.mybatis.mapper;

import com.example.mybatis.pojo.Student;

import java.util.List;

/**
 * @Author: yzy
 * @Date: 2022/9/30-17:21
 * @Description:
 */
public interface StudentMapper {

    /**
     * 分步查询第一步：根据学生的sid来查询学生信息
     * @param id
     * @return
     */
    Student selectByIdStep1(Integer id);

    /**
     * association方式的映射
     * @param id 学生id
     * @return Student对象（含有班级对象）
     */
    Student selectByIdWithAssociation(Integer id);

    /**
     * 根据Id查询学生信息，同时获取学生关联的班级
     * @param id 学生id
     * @return Student对象（含有班级对象）
     */
    Student selectById(Integer id);

    /**
     * 一对多的第二步查询
     * @param cid
     * @return
     */
    List<Student> selectByIdWithStep(Integer cid);
}
