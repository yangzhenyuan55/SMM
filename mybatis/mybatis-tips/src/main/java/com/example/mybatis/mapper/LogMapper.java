package com.example.mybatis.mapper;

import com.example.mybatis.pojo.Log;

import java.util.List;

/**
 * @Author: yzy
 * @Date: 2022/9/23-14:57
 * @Description:
 */
public interface LogMapper {
    /**
     * 根据日期获取相应日期的日志表信息
     * 日志表的表名与日期有关
     * @param date 日期
     * @return
     */
    List<Log> selectAllByDate(String date);
}
