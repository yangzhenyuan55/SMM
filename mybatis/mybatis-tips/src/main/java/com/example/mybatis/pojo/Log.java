package com.example.mybatis.pojo;

import java.util.Objects;

/**
 * @Author: yzy
 * @Date: 2022/9/23-14:53
 * @Description:
 */
public class Log {
    private Integer id;

    private String log;

    private String time;

    public Log() {
    }

    public Log(Integer id, String log, String time) {
        this.id = id;
        this.log = log;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", log='" + log + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log1 = (Log) o;
        return Objects.equals(id, log1.id) && Objects.equals(log, log1.log) && Objects.equals(time, log1.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, log, time);
    }
}
