package com.example.mybatis.mapper;

import com.example.mybatis.pojo.Clazz;

/**
 * @Author: yzy
 * @Date: 2022/9/30-17:21
 * @Description:
 */
public interface ClazzMapper {

    /**
     * 一对多分布查询
     * @param cid
     * @return
     */
    Clazz selectByIdWithStep(Integer cid);


    /**
     * 根据班级编号查询班级信息
     * @param cid 班级编号
     * @return
     */
    Clazz selectByCollection(Integer cid);

    /**
     * 分步查询第二步，根据cid获取班级信息
     * @param cid
     * @return
     */
    Clazz selectByIdStep2(Integer cid);

}
