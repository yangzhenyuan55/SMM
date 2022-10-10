# ORM 思想

## ORM(对象关系映射)

* ### O(Object): JVM中的Java对象
* ### R(Relational): 关系数据库
* ### M(Mapping): 映射(数据库表中的数据与Java对象进行转换的过程)
###
#### 关系型数据库表：
| &nbsp; id &nbsp; | name      | age  |
|------------------|-----------|------|
 | &nbsp; 1         | 张三        | 20   |
 | &nbsp; 2         | 李四        | 30   |
 | &nbsp; 3         | 王五        | 30   |

#### JVM中的Java对象
```java
public class User{
    private int id;
    private String name;
    private int age;
    
    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
```




