package com.example.mybatis.pojo;

import java.util.List;

/**
 * @Author: yzy
 * @Date: 2022/9/30-17:14
 * @Description: 班级实体
 */
public class Clazz {
    private Integer cid;

    private String cname;

    private List<Student> studentList; // 一个班级对应多个学生对象，一对多

    public Clazz(){}

    public Clazz(Integer cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    public Clazz(Integer cid, String cname, List<Student> studentList) {
        this.cid = cid;
        this.cname = cname;
        this.studentList = studentList;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", studentList=" + studentList +
                '}';
    }
}
