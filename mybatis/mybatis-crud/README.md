使用 MyBatis CURD
================================


<p id="content"/>

* ### CRUD
  [1. C：Create 增](#1) <br>
  [2. R：Retrieve 检索](#2) <br>
  [3. U：Update 改](#3) <br>
  [4. D：Delete 删](#4) <br>
  [5. namespace相关问题](#5)<br>
   
<p id="1"/>

* ### Create 
  ```xml
  <insert id="insertCar">
        INSERT INTO t_car(id,car_num, brand, guide_price, produce_time, car_type) 
        VALUES (null,'1007','比亚迪汉',40.0,'2022-12-12','新能源');
  </insert>
  ```
  
  * 注意：上面的代码写死了，在实际的开发中是不合理的，在传统的jdbc的代码中我们会使用占位符来对代码进行复用
  * 在mybatis中使用 #{} 当作占位符，相当于jdbc的占位符?
  * 使用Map来存储数据，进行INSERT
    ```xml
    <insert id="insertCar">
        INSERT INTO t_car(id,car_num, brand, guide_price, produce_time, car_type)
        VALUES (null,#{carNum},#{brand},#{guidePrice},#{produceTime},#{carType});
    </insert>
    ```
    ```java
    public class CarMapperTest {
        /**
         * 插入
         */
        @Test
        public void testInsertCar() {
            SqlSession sqlSession = SqlSessionUtil.openSession();
    
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("carNum", "1008");
            dataMap.put("brand", "比亚迪秦");
            dataMap.put("guidePrice", 10);
            dataMap.put("produceTime", "2020-11-11");
            dataMap.put("carType", "电车");
    
            // insert() 方法的参数
            // 第一个参数：statement ---- 指定sql语句在配置文件中的id
            // 第二个参数：object ----- 封装数据的对象（传入的对象的属性对应数据库中的字段然后进行数据插入），先使用Map来进行数据封装
            sqlSession.insert("insertCar", dataMap);
            sqlSession.commit();
            sqlSession.close();
        }
    }
    ```
      * 注意：这里的#{}中填写的是存入的Map中数据的key，底层会调用map.get(key)来从map中获取数据然后为字段赋值。如果写错，那么map.get(key)会取出null，那么对应的字段会被赋值为null，如果该字段有not null约束会报错

  * 使用Pojo类来进行INSERT
    ```java
    class Test{
         @Test
         public void testInsertByPojo() {
             SqlSession sqlSession = SqlSessionUtil.openSession();
     
             Car car = new Car(null, "1009", "比亚迪明", 30.0, "2020-01-12", "新能源");
     
             sqlSession.insert("insertCar", car);
             sqlSession.commit();
             sqlSession.close();
         }
    }
    ```
    ```xml
    <insert id="insertCar">
        INSERT INTO t_car(id,car_num, brand, guide_price, produce_time, car_type)
        VALUES (null,#{carNum},#{brand},#{guidePrice},#{produceTime},#{carType});
    </insert>
    ```
    * 注意：在使用Pojo来进行INSERT的时候，#{}里面写的是Pojo类的属性名，底层会调用属性相应的getter方法，写错属性会找不到相应的getter方法，所以写错属性名会报错。
    * 严格来说：#{} 里面应该写的是getter方法的方法名去掉get，然后剩余部分首字母小写，例如: getXyx() ----> #{xyz}。
    
[返回目录](#content)

<p id="4"/>

* ### Delete
  * 需求：将id=12的数据删除
    ```java
    public class Test{
        @Test
        public void testDeleteById() {
            SqlSession sqlSession = SqlSessionUtil.openSession();
    
            sqlSession.delete("deleteById", 12);
            sqlSession.commit();
            sqlSession.close();
        }
    }
    ```
    ```xml
    <delete id="deleteById">
        DELETE FROM t_car WHERE id=#{id}
    </delete>
    ```
[返回目录](#content)    

<p id="3">

* ### Update
  * 需求：根据id修改某条记录
    ```java
    class Test{
        @Test
        public void testUpdateById() {
            SqlSession sqlSession = SqlSessionUtil.openSession();
    
            sqlSession.update("updateById", new Car(14L, "9999", "凯美瑞", 30.3, "1999-11-10", "燃油车"));
            sqlSession.commit();
            sqlSession.close();
        }
    }
    ```
    ```xml
    <update id="updateById">
        UPDATE t_car SET car_num=#{carNum},brand=#{brand},guide_price=#{guidePrice},produce_time=#{produceTime},car_type=#{carType} WHERE id=#{id}
    </update>
    ```
    [返回目录](#content)

<p id="2"/>

* ### Retrieve
  * select one(查询一个，根据主键查询，返回结果一定是一个)
    * 需求：根据id取查询
  ```java
  class Test{
      @Test
      public void testSelectById() {
          SqlSession sqlSession = SqlSessionUtil.openSession();
  
          Object car = sqlSession.selectOne("selectById", 13);
          System.out.println("查询结果: " + car);
  
          sqlSession.commit();
          sqlSession.close();
      }
  }  
  ```
  ```xml
  <select id="selectById" resultType="com.example.mybatis.pojo.Car">
        SELECT * FROM t_car WHERE id=#{id}
  </select>
  ```
    * 注意：在select标签中需要指定查询结果类型，通过resulType来指定类型，这样查询结果会封装成指定类型的Java类，resultType写的是全限定类名
    * 出现问题：查询结果: Car{id=13, carNum='null', brand='比亚迪秦', guidePrice=null, produceTime='null', carType='null'}，有些属性的值仍未null
    * 原因：数据库中的字段car_num 和Car类的carNum不一样，所以赋值失败，其他的属性同理。
    * 解决方法：为查询语句中的字段起别名，别名跟Java类的属性名一致
      ```xml
      <select id="selectById" resultType="com.example.mybatis.pojo.Car">
        select
            id,
            car_num as carNum,
            brand,
            guide_price as guidePrice,
            produce_time as produceTime,
            car_type as carType
        from t_car where id=#{id}
      </select>
      ```
      * 查询结果：Car{id=13, carNum='1008', brand='比亚迪秦', guidePrice=10.0, produceTime='2020-11-11', carType='电车'}

  * select all(查询所以数据)
    ```java
    class Test{
        @Test
        public void testSelectAll() {
            SqlSession sqlSession = SqlSessionUtil.openSession();
    
            List<Object> cars = sqlSession.selectList("selectAll");
            cars.forEach(car -> System.out.println(car));
    
            sqlSession.commit();
            sqlSession.close();
        }
    }
    ```
    ```xml
    <select id="selectAll" resultType="com.example.mybatis.pojo.Car">
        select
            id,
            car_num as carNum,
            brand,
            guide_price as guidePrice,
            produce_time as produceTime,
            car_type as carType
        from t_car
    </select>
    ```
<p id="5"/>

* ### XXXMapper.xml文件中mapper标签的namespace属性
  * 对于加入有两个Mapper.xml文件，并且两个文件中有Id相同的查询语句标签，那么此时Id不唯一，所以要在id前面加上namespace。
  * 例如：UserMapper.xml中的namespace="userMapper"，其中有一个查询语句id=selectAll，在Java代码中写法应为
    ```text
    sqlSession.selectList("userMapper.selectAll");
    ```

[返回目录](#content)



