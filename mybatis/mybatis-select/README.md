MyBatis 查询专题
=============


1) 数据库表字段名和Java实体类的属性名不一致解决
   * 第一种方法就是在查询的时候为数据库表的字段名起别名
     ```xml
     <select id="selectById" resultType="Car">
        select id,
               car_num as carNum,
               brand,
               guide_price as guidePrice,
               produce_time as produceTime,
               car_type as carType
        from t_car
        where id=#{id}
     </select>
     ```
   * 第二种方法：使用resultMap进行结果映射
     ```xml
     <mapper>
        <resultMap id="carResultMap" type="Car">
            <!--
                property: pojo类的属性名
                column: 数据库表的字段名
                | id | car_num | brand    | guide_price | produce_time | car_type |
            -->
            <!--如果数据库表中有主键，建议配置一个id标签-->
            <id property="id" column="id"/>
            <result property="carNum" column="car_num"/>
            <result property="brand" column="brand"/>
            <result property="guidePrice" column="guide_price"/>
            <result property="produceTime" column="produce_time"/>
            <result property="carType" column="car_type"/>
        </resultMap>
        <select id="selectAllByResultMap" resultMap="carResultMap">
            select * from t_car
        </select>
     </mapper>
     ```
   * 第三种方式：开启驼峰命名自动映射
     * 使用条件：
       * Java命名规范：首字母小写，后面每个单词的首字母大写，小驼峰命名方式
       * SQL命名规范：全部小写，单词之间只用下划线分割
     * 配置方法：在mybatis-config.xml文件中，在properties标签的后面进行配置
     ```xml
     <settings>
        <setting name="mapUnderscoreToCamelCase" value="true"/>
     </settings>
     ```
     * 注意：还是需要填写resultType来指定相应的Java类
       
2) 查询返回总记录条数
