Mybatis 小技巧
=================================
## 目录
* [#{} 和 ${} 的区别](#1)
* [${} 的一些使用场景](#2)
* [批量删除，一次删除多条记录](#3)
* [模糊查询](#4)
* [别名机制](#5)
* [mybatis-config.xml文件种的mappers标签](#6)
* [插入数据时获取自动生成的主键](#7)
* [](#8)


## 1. #{} 和 ${} 的区别
<p id="1"/>

* #### 使用 #{} 执行结果如下:
```text
2022-09-23 14:03:48.022 [main] DEBUG com.example.mybatis.mapper.CarMapper.selectByType - ==>  
Preparing: select id, 
car_num as carNum, 
brand, 
guide_price as guidePrice, 
produce_time as produceTime, 
car_type as carType 
from t_car where car_type=?
2022-09-23 14:03:48.057 [main] DEBUG com.example.mybatis.mapper.CarMapper.selectByType - ==> Parameters: 燃油车(String)
2022-09-23 14:03:48.208 [main] DEBUG com.example.mybatis.mapper.CarMapper.selectByType - <==      Total: 6
```
* #### 使用${} 执行结果如下
```text
org.apache.ibatis.exceptions.PersistenceException: 
### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column '燃油车' in 'where clause'
### The error may exist in CarMapper.xml
### The error may involve defaultParameterMap
### The error occurred while setting parameters
### SQL: 
select id,
car_num as carNum,
brand,
guide_price as guidePrice,
produce_time as produceTime
,car_type as carType 
from t_car where car_type=燃油车
### Cause: java.sql.SQLSyntaxErrorException: Unknown column '燃油车' in 'where clause'
```
```text
查看两者生成的SQL语句可以看出两者的区别
#{}：底层使用PreparedStatement，特点是先进行SQL语句的编译，然后给SQL语句的占位符?传值
${}: 底层使用Statement，先进行SQL语句的拼接，然后再进行SQL语句的编译，存在SQL注入的风险
在这个案例中，传入的燃油车被当作语句直接拼接进去然不是一个字符串，所以会报错

优先使用#{}，避免SQL注入风险，那么何时使用${}呢？我们来完成一个需求，对于查询的结果要求降序排列或者升序排列，
这个时候我们就需要将desc或者asc这个SQL语句传进去，这个时候就可以使用${}，这个时候就可以根据我们传入的参数的desc或asc
来判断是降序排列还是升序排列，实例代码如下
```
```xml
<select id="selectAllOrderByProduceTime" resultType="com.example.mybatis.pojo.Car">
        select id,
               car_num as carNum,
               brand,
               guide_price as guidePrice,
               produce_time as produceTime,
               car_type as carType
        from t_car
        order by produceTime ${ascOrDesc}
</select>
```
## 2. ${} 的一些使用场景：
<p id="2"/>

* 将SQL语句的关键字传入SQL语句中时需要使用${}
* 向SQL语句中拼接表名，需要${}
  * 现实业务中，可能会存在分表存储数据的情况。如日志表，如果只有一个日志表，表中数据会很多
  * 可以每天生成一个新的日志表，以当天的日期作为表名，如：t_log_20220923，t_log_20220924
  * 如果你想查看某一天的日志，假设这天的日期为20220923，直接查t_log_20220923就可以了，这天的日期作为参数传入，如下代码
  * 具体实现与此项目中
* 下面还有${}的应用场景
```xml
<select id="selectAllByDate" resultType="com.example.mybatis.pojo.Log">
        select * from t_log_${date}
</select>
```

## 3. 批量删除，一次删除多条记录
<p id="3"/>

* #### 批量删除的SQL语句有两种(in使用${}可以完成)
  * 第一种 or：delete from t_car where id=1 or id=2 ...
  * 第二种 in：delete from t_car where id in(1,2,3...)

```xml
<!--使用${}-->
<delete id="deleteBatch">
  delete from t_car where id in(${ids})
</delete>
```

## 4. 模糊查询
<p id="4"/>

* #### 需求：根据汽车品牌进行模糊查询(${}的应用场景)
```sql
# 查询所有奥迪品牌的车
select * from t_car where brand like '%奥迪%'
# 查询所有比亚迪品牌的车
select * from t_car where brand like '%比亚迪%'
```
* #### 第一种方案：使用${}来拼接需要查询的字段
```xml
 <select id="selectByBrandLike" resultType="com.example.mybatis.pojo.Car">
    select id,
    car_num as carNum,
    brand,
    guide_price as guidePrice,
    produce_time as produceTime,
    car_type as carType
    from t_car where brand like '%${brand}%' 
</select>
```
* #### 第二种方案：concat函数，MySQL数据库中的一个函数，专门进行字符串拼接
```sql
# 使用这个函数才可以使用#{}来传参,因为'%#{brand}%'最终会变成'%?%',而放在''里面的?会被当成普通字符,不再是占位符
concat('%',#{},'%')
```
* #### 第三种方案：concat与${}配合使用，但是需要将${}用引号括起来
```sql
concat('%','${brand}','%')
```
* #### 第四种方案："%"#{brand}"%"，推荐使用

## 5. 别名机制
<p id="5"/>

```text
    当我们在使用select标签的时候，都需要指定returnType，并且要写上全限定类名，太麻烦
这个时候我们可以为这个全限定类名起一个别名，在mybatis-config.xml文件种进行配置如下，
并且别名不区分大小写。alias属性可以省略，省略后的别名为类的简单名，如：com.example.mybatis.pojo.Car
的默认别名为Car,car,....，因为不区分大小写
```
```xml
<typeAliases>
      <!--
          type：指定给哪个类型起别名
          alias：指定别名
      -->
      <typeAlias type="com.example.mybatis.pojo.Car" alias="Car"/>
      <typeAlias type="com.example.mybatis.pojo.Log" alias="Log"/>
</typeAliases>
```
```xml
    <!--但是这种写法还是太麻烦了，如果有很多的类，那么也是一个不小的工作量
MyBatis提供了一个新标签package，它会将这个包下的类自动起别名-->
<typeAliases>
        <package name="com.example.mybatis.pojo"/>
</typeAliases>
```

## 6. mybatis-config.xml文件种的mappers标签
<p id="6"/>

```xml
<mappers>
    <mapper resource=""/>
    <mapper url=""/>
    <mapper class=""/>
</mappers>
```
* resource属性：从类的根路径查找资源
* url属性：这是一种绝对路径的方式来查找资源
* class属性：这个位置提供的是mapper接口的全限定接口名，必须带有包名。
  * ```text
    假设你写的接口名为：
    <mapper class="com.example.mybatis.mapper.CarMapper"/>
    那么MyBatis框架会自动取com/example/mybatis/mapper/目录下查找CarMapper.xml文件
    注意：也就是说，如果你采用这种方式，那么你必须保证CarMapper.xml和C  arMapper接口在同一个目录下
    你可以在resources目录下创建com/example/mybatis/mapper目录然后将其放进去，在编译之后，就会被放进同一个包中
    ```
## 7. 插入数据时获取自动生成的主键
<p id="7"/>

说明：比如说id是一个自增主键，我们在插入数据的时候并不需要管这个自增的主键。那当我们需要要获取这个自增主键的值时应该怎么做?
具体实现如下:
```xml
<insert id="insertCarUseGeneratedKeys" useGeneratedKeys="true" keyProperty="id">
    insert into t_car values values(null,#{carNum},#{brand},#{guidePrice},#{produceTime},#{carType})
</insert>
```
说明：useGeneratedKeys=true ---- 使用自动生成的主键值（false则是不使用）
     keyProperty="id" ---- 指定自动生成的主键值赋值给对象的哪个属性，这里指定主键值赋值给Car对象的id属性