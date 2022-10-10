package com.example.spring.pojo;

/**
 * @Author: 杨镇远
 * @Date: 2022/9/5-16:26
 */
public class Student {
    private Integer sid;

    private String sname;

    private Integer sage;

    private String gender;

    private Clazz clazz;

    public Student() {
    }



    public Student(Integer sid, String sname, Integer sage, String gender) {
        this.sid = sid;
        this.sname = sname;
        this.sage = sage;
        this.gender = gender;
    }

    public Student(Integer sid, String sname, Integer sage, String gender, Clazz clazz) {
        this.sid = sid;
        this.sname = sname;
        this.sage = sage;
        this.gender = gender;
        this.clazz = clazz;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Integer getSage() {
        return sage;
    }

    public void setSage(Integer sage) {
        this.sage = sage;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", sage=" + sage +
                ", gender='" + gender + '\'' +
                ", clazz=" + clazz +
                '}';
    }
}
