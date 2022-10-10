MyBatis 核心配置文件
=========================

## 目录
<p id="content"></p>

* #### [environments 标签](#1)
* #### [environments 标签](#2)
* #### [transactionalManager 标签](#3)
* #### [dataSource 标签](#4)
* #### [properties标签和mapper标签](#5)


###
###


<h3 id="1">environments 标签：可以在里面配置多个环境，连接多个数据库</h3> 

<h3 id="2">environment 标签：environments其中的一个环境，连接一个指定的数据库(如下代码中连接的数据库就是powernode)</h3>

```xml
<!--default表示默认使用的环境，也就是说在没有指定环境的情况下，mybatis默认使用的数据库是id=development的那个数据库-->
<environments default="development">
        <!--一个environment对应一个SqlSessionFactory对象-->
        <environment id="development">
            <transactionManager type="JDBC"/>
                <dataSource type="POOLED">
                    <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                    <property name="url" value="jdbc:mysql://localhost:3306/powernode?serverTimezone=UTC"/>
                    <property name="username" value="root"/>
                    <property name="password" value="20160910yzy"/>
                </dataSource>
        </environment>
    </environments>
```
<h3 id="3">transactionalManager 标签：</h3>
  * 作用：配置事务管理器，指定MyBatis具体使用什么方式去管理事务
  * type属性有两个值：JDBC(使用原生的jdbc代码来管理事务)、MANAGED(将事务管理交给JEE容器来管理，如Spring事务管理)
    * 注意：不区分大小写
<br>
<br>

<h3 id="4">dataSource 标签：</h3>
  * dataSource被称为数据源
  * 作用：为程序提供Connection对象(给程序提供Connection对象的都叫做数据源)
  * 常见的数据源组件：druid、c3p0、dbcp
  * type属性有三个值：
    * UNPOOLED：不使用数据库连接池技术。每一次请求发过来之后，都是创建新的Connection对象
    * POOLED：使用MyBatis自己实现的数据库连接池
    * JNDI：集成第三方的数据库连接池
  * UNPOOLED和POOLED的区别
    ```java
    class Test{
        // 当dataSource=UNPOOLED ，也就是说不使用连接池
        // sqlSession1的连接对象Connection [com.mysql.cj.jdbc.ConnectionImpl@609e8838]
        // sqlSession2的连接对象 JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@821330f]
        // 两次获取的Connection不是同一个

        // 当dataSource=POOLED，使用连接池
        // sqlSession1的连接对象 JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@792b749c]
        // sqlSession2的连接对象 JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@792b749c]
        // 由此可见，当使用连接池时，两次会话获取的都是同一个连接对象
        @Test
        public void testDataSource() throws IOException {
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
            SqlSession sqlSession1 = sqlSessionFactory.openSession();
    
            Object car1 = sqlSession1.selectOne("selectById", 13);
            System.out.println(car1);
            sqlSession1.commit();
            sqlSession1.close();
            
            SqlSession sqlSession2 = sqlSessionFactory.openSession();
            Object car2 = sqlSession2.selectOne("selectById", 13);
            System.out.println(car2);
            sqlSession2.commit();
            sqlSession2.close();
        }
    }
    ```
  * 测试 POOLED的一些属性
    ```xml
    <!--在任意时间可存在的活动（正在使用）连接数量，默认值：10-->
    <property name="poolMaximumActiveConnections" value="3"/>
    <!--等等...-->
    ```
    ```java
    class Test{
      /**
       * 测试POOLED的属性
       * poolMaximumActiveConnections --- 最大连接数量 ,default=10
       * poolTimeToWait ---- 如果获取连接花费了相当长的时间，连接池会打印状态日志并重新尝试获取一个连接,设置打印日志时间，并且尝试获取连接
       * poolMaximumCheckoutTime ---- 超时时间，在被强制返回之前，池中连接被检出（checked out）时间，默认值：20000 毫秒（即 20 秒）,在20秒后还未取得连接就退出
       * poolMaximumIdleConnections ---- 任意时间可能存在的空闲连接数。
       * (
       * 假设最多的连接数量为 10 个， 最多空闲数量为 5 个；假设当前已经有5个连接是空闲的了，马上就要第6个空闲了。
       * 假设第6个空闲下来了，此时连接池为了保证最多空闲数量始终为 5个，会真正关闭多余的空闲的连接对象
       * )
       */
      @Test
      public void testPOOLEDConfiguration() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
    
        // 此时 poolMaximumActiveConnections = 3
        // 模拟四个连接，前三个连接但是不关闭，看看第四个是否会等待有一个关闭然后才连接
        Integer id = 7;
        for (int i = 0; i < 4; i++) {
          SqlSession sqlSession = sqlSessionFactory.openSession();
          Object car2 = sqlSession.selectOne("selectById", id++);
          System.out.println(car2);
          // 不关闭连接
        }
      }
    }
    ```
<br>

<h3 id="5">properties标签和mapper标签</h3>
  * properties标签可以引入配置文件配合${}符号来为dataSource的属性赋值
    ```html
    <!--resource属性从类路径下加载资源-->
    <properties resource="jdbc.properties"/>
    <!--url属性从绝对路径加载资源-->
    <properties url=""/>
    <dataSource type="POOLED">
        <property name="driver" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </dataSource>
    ```
  * mapper标签引入SQL映射文件
    ```xml
    <mappers>
        <mapper resource="CarMapper.xml"/>
    </mappers>
    ```

