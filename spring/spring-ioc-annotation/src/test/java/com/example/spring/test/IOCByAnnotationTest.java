package com.example.spring.test;

import com.example.spring.controller.UserController;
import com.example.spring.dao.UserDao;
import com.example.spring.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: 杨镇远
 * @Date: 2022/9/6-18:46
 */
public class IOCByAnnotationTest {
    /**
     * 通过注解来进行注入
     */
    @Test
    public void testIOCByAnnotation() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring-ioc-annotation.xml");

        /*
            在使用注解注入bean的时候，每个Bean的id默认是其类名的小驼峰写法
         */
        UserController userController = ioc.getBean("userController", UserController.class);
        System.out.println("控制层--->" + userController);

        UserService userService = ioc.getBean("userService", UserService.class);
        System.out.println("业务逻辑层--->" + userService);

        UserDao userDao = ioc.getBean("userDao", UserDao.class);
        System.out.println("持久层--->" + userDao);

        System.out.println("=========================");
        /*
            使用注解实现自动装配：
            1、@Autowired注解能够识别的位置
            a)、标识在成员变量上，此时不需要设置变量的set方法
            b)、标识在set方法上
            c)、标识在为当前成员变量赋值的有参构造方法上

            2、@Autowired注解的原理
            a)、默认通过byType的方式，在IOC容器中通过类型匹配某个bean为属性赋值
            b)、若有多个类型匹配的bean,此时会自动转换为byName方式实现自动装配的效果，其属性名跟容器中bean的id相等就能匹配成功
            c)、@Qualifier注解可以通过value属性值来指定某个bean的id，将这个bean为成员变量赋值
         */
        userController.saveUser();
    }
}
