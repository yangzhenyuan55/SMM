第一个MyBatis程序
=====================================

* ### resources目录：放到这个目录下的一般是资源文件，配置文件。<br>直接放resources目录下的资源，等同于是放到了类路径下。
* ### 开发步骤：
    * 第一步：设置打包方式为jar
    * 第二步：引入依赖（MyBatis依赖，MySQL驱动）
    * 第三步：编写MyBatis核心配置文件mybatis-config.xml
    * 第四步：编写XXXMapper.xml配置文件，在这个配置文件当中编写sql语句，文件名不是固定的
    * 第五步：在mybatis-config.xml文件中指定XXXMapper.xml文件(resource会自动从更路径下查找资源)
    * 第六步：编写MyBatis程序

* ### 在MyBatis中负责执行SQL语句的对象是什么呢？
  * SqlSession是专门用来执行SQL语句的，是一个Java程序和数据库之间的一次会话
  * 通过SqlSessionFactory工厂来获取SqlSession对象
  * 获取SqlSessionFactory对象要通过SqlSessionFactoryBuilder对象的build方法，来获取一个SqlSessionFactory对象

* ### 关于MyBatis的事务管理机制
  * 在mybatis-config.xml文件中，可以通过以下的配置进行mybatis的事务管理
    ```xml
    <transactionManager type="JDBC"/>
    ```
    * type的属性有两个：JDBC、MANAGED
  * JDBC事务管理器：mybatis框架自己管理事务，自己采用原生的JDBC代码取管理事务
    ```text
       // 如
    conn.setAutoCommit(false);
    ....业务处理....
    conn.commit();
    ```
    * 使用JDBC事务管理器底层会创建事务管理器对象：JdbcTransaction对象
  * MANAGED事务管理器：mybatis不再负责事务管理，事务交给其他容器来管理，例如spring事务管理

* ### MyBatis集成日志组件
  * mybatis常见的日志组件
    * SLF4J(沙拉风)
    * LOG4J、LOG4J2
    * STDOUT_LOGGING(标准日志，MyBatis已经实现了这种标准日志，只需要开启即可，在mybatis-config.xml文件种的settings标签中开启)
      ```xml
      <settings>
        <setting name="logImpl" value="STDOUT_LOGGING"/>
      </settings>
      ```
  * 开启日志后的执行效果(往t_car种插入一条数据,使用的日志组件是STDOUT_LOGGING，是MyBatis自己实现的一个日志组件)
    ```text
       Logging initialized using 'class org.apache.ibatis.logging.stdout.StdOutImpl' adapter.
       PooledDataSource forcefully closed/removed all connections.
       PooledDataSource forcefully closed/removed all connections.
       PooledDataSource forcefully closed/removed all connections.
       PooledDataSource forcefully closed/removed all connections.
       Opening JDBC Connection
       Created connection 8805846.
       Setting autocommit to false on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@865dd6]
       ==>  Preparing: INSERT INTO t_car(id,car_num, brand, guide_price, produce_time, car_type) VALUES (null,'1005','奥迪A5',100.0,'2019-11-11','燃油车');
       ==> Parameters:
       <==    Updates: 1
       Committing JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@865dd6]
       Resetting autocommit to true on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@865dd6]
       Closing JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@865dd6]
       Returned connection 8805846 to pool.
    ```
  * 使用logback日志框架
    * logback日志框架实现了SLF4J标准
    * 第一步：引入依赖
      ```xml
      <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.2.11</version>
            <scope>test</scope>
      </dependency>
      ```
    * 第二步：引入logback所必须的xml配置文件：这个配置文件名字必须为logback.xml或者logback-test.xml，且必须放在类路径下
    * 运行结果：
      ```text
      22:45:48,901 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Could NOT find resource [logback-test.xml]
      22:45:48,901 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Found resource [logback.xml] at [file:/E:/Program%20Files%20(x86)/workSpace/SSM/mybatis-introduction/target/classes/logback.xml]
      22:45:48,966 |-INFO in ch.qos.logback.classic.joran.action.ConfigurationAction - debug attribute not set
      22:45:48,969 |-INFO in ch.qos.logback.core.joran.action.AppenderAction - About to instantiate appender of type [ch.qos.logback.core.ConsoleAppender]
      22:45:48,973 |-INFO in ch.qos.logback.core.joran.action.AppenderAction - Naming appender as [STDOUT]
      22:45:49,017 |-INFO in ch.qos.logback.classic.joran.action.LoggerAction - Setting level of logger [com.apache.ibatis] to TRACE
      22:45:49,017 |-INFO in ch.qos.logback.classic.joran.action.LoggerAction - Setting level of logger [java.sql.Connection] to DEBUG
      22:45:49,017 |-INFO in ch.qos.logback.classic.joran.action.LoggerAction - Setting level of logger [java.sql.Statement] to DEBUG
      22:45:49,017 |-INFO in ch.qos.logback.classic.joran.action.LoggerAction - Setting level of logger [java.sql.PreparedStatement] to DEBUG
      22:45:49,017 |-INFO in ch.qos.logback.classic.joran.action.RootLoggerAction - Setting level of ROOT logger to DEBUG
      22:45:49,017 |-INFO in ch.qos.logback.core.joran.action.AppenderRefAction - Attaching appender named [STDOUT] to Logger[ROOT]
      22:45:49,018 |-ERROR in ch.qos.logback.core.joran.action.AppenderRefAction - Could not find an appender named [FILE]. Did you define it below instead of above in the configuration file?
      22:45:49,018 |-ERROR in ch.qos.logback.core.joran.action.AppenderRefAction - See http://logback.qos.ch/codes.html#appender_order for more details.
      22:45:49,018 |-INFO in ch.qos.logback.classic.joran.action.ConfigurationAction - End of configuration.
      22:45:49,019 |-INFO in ch.qos.logback.classic.joran.JoranConfigurator@289d1c02 - Registering current configuration as safe fallback point
      
      2022-09-19 22:45:49.025 [main] DEBUG org.apache.ibatis.logging.LogFactory - Logging initialized using 'class org.apache.ibatis.logging.slf4j.Slf4jImpl' adapter.
      2022-09-19 22:45:49.028 [main] DEBUG org.apache.ibatis.logging.LogFactory - Logging initialized using 'class org.apache.ibatis.logging.slf4j.Slf4jImpl' adapter.
      2022-09-19 22:45:49.076 [main] DEBUG o.apache.ibatis.datasource.pooled.PooledDataSource - PooledDataSource forcefully closed/removed all connections.
      2022-09-19 22:45:49.077 [main] DEBUG o.apache.ibatis.datasource.pooled.PooledDataSource - PooledDataSource forcefully closed/removed all connections.
      2022-09-19 22:45:49.077 [main] DEBUG o.apache.ibatis.datasource.pooled.PooledDataSource - PooledDataSource forcefully closed/removed all connections.
      2022-09-19 22:45:49.077 [main] DEBUG o.apache.ibatis.datasource.pooled.PooledDataSource - PooledDataSource forcefully closed/removed all connections.
      2022-09-19 22:45:49.124 [main] DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Opening JDBC Connection
      2022-09-19 22:45:50.763 [main] DEBUG o.apache.ibatis.datasource.pooled.PooledDataSource - Created connection 1082309267.
      2022-09-19 22:45:50.763 [main] DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Setting autocommit to false on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@4082ba93]
      2022-09-19 22:45:50.768 [main] DEBUG abc.insertCar - ==>  Preparing: INSERT INTO t_car(id,car_num, brand, guide_price, produce_time, car_type) VALUES (null,'1006','奥迪A7',100.0,'2020-12-11','燃油车');
      2022-09-19 22:45:50.798 [main] DEBUG abc.insertCar - ==> Parameters:
      2022-09-19 22:45:50.828 [main] DEBUG abc.insertCar - <==    Updates: 1
      2022-09-19 22:45:50.828 [main] DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Committing JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@4082ba93]
      2022-09-19 22:45:50.888 [main] DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Resetting autocommit to true on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@4082ba93]
      2022-09-19 22:45:50.888 [main] DEBUG org.apache.ibatis.transaction.jdbc.JdbcTransaction - Closing JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@4082ba93]
      2022-09-19 22:45:50.888 [main] DEBUG o.apache.ibatis.datasource.pooled.PooledDataSource - Returned connection 1082309267 to pool.
      ```
    * 注意：当不使用STDOUT_LOGGING日志组件时，可以不在mybatis-config.xml文件种配置，因为mybatis会自动查找，但是要有相关的日志框架的依赖(logback依赖)和配置文件(logback.xml)

* ### 自己封装一个MyBatis工具类
  * SqlSessionFactory注意事项：一个SqlSessionFactory对象对应一个<environment>标签(mybatis-config.xml中的标签)，也就是一个数据库。一个mybatis-config.xml文件中可以配置多个<enviroment>标签，也就是说可以配置多个数据库，例如Mysql，Oracle等等。
  
```java
package com.example.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: yzy
 * @Date: 2022/9/19-23:00
 * @Description: MyBatis工具类
 */
public class SqlSessionUtil {
    /**
     * 构造方法私有化，防止实例化
     */
    private SqlSessionUtil() {
    }

    private static SqlSessionFactory sqlSessionFactory;

    static {
        // 类加载时创建SqlSessionFactory对象

        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取与数据库的会话对象
     * @return 会话对象
     */
    public static SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }
}
```