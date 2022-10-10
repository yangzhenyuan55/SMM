package com.example.spring.test;

import com.example.spring.controller.UserController;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: 杨镇远
 * @Date: 2022/9/6-18:00
 */
public class AutowireByXml {

    /**
     * 基于XML的自动装配模拟
     *
     * 自动装配：
     * 根据指定的策略，在IOC容器中匹配某个bean，自动为bean中的类类型的属性或者接口类型的属性赋值
     */
    @Test
    public void testAutowire() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring-autowire-xml.xml");

        UserController userController = ioc.getBean(UserController.class);
        userController.saveUser();


    }
}
