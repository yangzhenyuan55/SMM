package com.example.spring.test;

import com.example.spring.bean.User;
import com.example.spring.service.OrderService;
import com.example.spring.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: yzy
 * @Date: 2022/10/29-20:24
 * @Description: Spring依赖注入
 */
public class SpringDITest {


    /**
     * 简单类型set注入
     */
    @Test
    public void testSimpleTypeSetDI() {
        ApplicationContext context = new ClassPathXmlApplicationContext("set-di.xml");

        User user = context.getBean("userBean", User.class);
        System.out.println(user);
    }

    /**
     * set内部注入和外部注入
     */
    @Test
    public void testSetDI2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("set-di.xml");

        // 外部bean注入
        OrderService orderService = context.getBean("orderServiceBean", OrderService.class);
        orderService.generateOrder();

        // 内部bean注入
        OrderService orderService2 = context.getBean("orderServiceBean2", OrderService.class);
        orderService2.generateOrder();

    }

    /**
     * 构造注入
     */
    @Test
    public void testConstructorDI() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = context.getBean("userServiceBean2", UserService.class);

        userService.saveUser();
    }


    /**
     * set注入
     */
    @Test
    public void testSetDI() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserService userService = context.getBean("userServiceBean1", UserService.class);

        userService.saveUser();

    }
}
