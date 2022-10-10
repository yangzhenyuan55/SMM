package com.example.mybatis.mapper;

import com.example.mybatis.pojo.Car;
import org.apache.ibatis.annotations.MapKey;
import java.util.List;
import java.util.Map;

/**
 * @Author: yzy
 * @Date: 2022/9/23-13:03
 * @Description:
 */
public interface CarMapper {

    /**
     * 根据id来查询结果唯一
     * @param id id
     * @return Car实体类
     */
    Car selectById(Long id);

    /**
     * 查询所有Car
     * @return CarList
     */
    List<Car> selectAll();

    /**
     * 根据品牌来模糊查询
     * @param brand 品牌
     * @return
     */
    List<Car> selectByBrandLike(String brand);

    /**
     * 根据id查询，返回Map集合
     * @param id 唯一id
     * @return key为属性名，value为属性值的Car的Map集合
     */
    Map<String, Object> selectByIdRetMap(Long id);

    /**
     * 查询所有
     * @return 返回一个存放Map集合的List集合
     * 形如:
     * +----+---------+----------+-------------+--------------+----------+
     * | id | car_num | brand    | guide_price | produce_time | car_type |
     * +----+---------+----------+-------------+--------------+----------+
     * | 16 | 1007    | 比亚迪秦  |       30.00 | 2022-12-23   | 新能源
     *
     * key      |  value
     * id       |  16
     * car_num  |  1007
     * ....
     */
    List<Map<String, Object>> selectAllRetMapList();


    /**
     * 查询所有元素
     *
     * @return 返回一个存放Map的Map，Map的key为查询出的字段的主键（id），而value为所有字段的一个Map集合
     *+----+---------+----------+-------------+--------------+----------+
     * | id | car_num | brand    | guide_price | produce_time | car_type |
     * +----+---------+----------+-------------+--------------+----------+
     * | 16 | 1007    | 比亚迪秦   |       30.00 | 2022-12-23   | 新能源   |
     * | 17 | 1008    | 比亚迪汉   |       35.00 | 2022-12-24   | 新能源   |
     * +----+---------+----------+-------------+--------------+----------+
     * Map
     * key      |       value
     * id       |       Map<String,Object>
     *
     *
     */
    @MapKey("id") // 将查询结果里的id作为Map的key
    Map<Long, Map<String, Object>> selectAllRetMapIdAsKey();

    /**
     * 使用resultMap来进行结果映射查询所有数据
     * @return
     */
    List<Car> selectAllByResultMap();

    /**
     * 使用驼峰自动映射来查询所有数据
     * @return
     */
    List<Car> selectAllByMapUnderscoreToCamelCase();

    /**
     * 获取总记录条数
     * @return 返回数据库有多少条记录（按照本例子，返回值为有多少辆车记录数据）
     */
    Long getTotal();

}

























