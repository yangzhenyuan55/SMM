MyBatis参数
================

## 1.参数类型：Long(单个参数)
<p id="1">

```xml
<select id="selectById" resultType="Student" parameterType="java.lang.Long">
    select * from t_student where id=#{id}
</select>
```
* parameterType属性的作用：告诉mybatis执行这条语句的方法的参数类型是什么类型<br>
mybatis框架带有类型自动推断机制，所以大部分情况下parameterType这个属性可以<br>
不写。底层的代码大致如下
```text
String sql = select * from t_student where id=?;
ps = conn.preparedStatement(sql);
ps.setLong(1, 1L);
ps.setName(1,"张三");
ps.setBirth(1,new Date());
ps.setInt(1,100);
mybatis底层调用setXXX()哪个方法取决于parameterType属性的值，你不写框架会自动推断
....
```
* 所以对于其他的单个简单类型也是同理,虽然Map和pojo实例不是简单类型，但是插入的时候MyBatis同样会推断类型，这里就不一一列出代码。
* 注意：MyBatis内置了很多的别名

| 别名        | 类型         | 别名         | 类型          |
|-----------|------------|------------|-------------|
| byte      | byte       | _long      | long        |
| _short    | short      | _int       | int         | 
| _integer  | 	int       | _double    | double      |
| _float    | float      | _boolean   | 	boolean    |
| string    | String     | byte       | Byte        |
| long      | Long       | short      | Short       |
| int       | Integer    | integer    | Integer     |
| double    | Double     | float      | Float       |
| boolean   | Boolean    | date       | Date        |
| decimal   | BigDecimal | bigdecimal | 	BigDecimal |
| object    | Object     | map        | Map         |
| hashmap   | HashMap    | list	      | List        |
| arraylist | 	ArrayList | collection | 	Collection |
| iterator  | 	Iterator  |            |             |

* 指定java类型和数据库类型
```text
name=#{name,javaType=String,jdbcType=VARCHAR}
```

## 2.参数类型：java.util.Date
* 注意：如果Java中字段属性为Date类型，并且数据库中对应的类型也是date类型，在进行映射时，mybatis会默认将Java里的DATE类型映射为数据库中的Timestamp类型。
```sql
# 通过生日来查询
select * from t_student where birth = #{birth}
# 但是实际执行的代码是
select * from t_student where to_timestamp(birth) = #{birth}
# 解决方案:
select * from t_student where birth = #{birth,javaType=date,jdbcType=DATE}
```

## 3.多个参数
* 需求：根据名字和性别来查询(这个时候就需要传入多个参数了)
* 此时的parameterType就不确定填写哪一种类型了
```xml
<select id="selectByNameAndSex" resultType="Student">
    select * from t_student where name=#{} and sex=#{}
</select> 
```
* 多个参数时，MyBatis底层会自动创建一个Map集合，并且Map集合是以map.put("arg0",param1),map.put("arg1",param2)....<br>
  这种形式来存储数据的,所以这个时候#{}里面应该写为#{arg0},#{arg1}....
```xml
<select id="selectByNameAndSex" resultType="Student">
    select * from t_student where name=#{arg0} and sex=#{arg1}
  <!--或者-->
  select * from t_student where name=#{param1} and sex=#{param2}
</select> 
```
* 通过注解来实现，通过注解来指定它的名字之后，@Param注解的value是什么，#{}里面就要写什么
```java
interface StudentMapper { 
    List<Student> selectByNameAndSexUseAnnotation(@Param("name") String name, @Param("sex") String sex);
}
```
```xml
<select id="selectByNameAndSexUseAnnotation" resultType="Student">
        select * from t_student where name=#{name} and sex=#{sex}
</select>
```

