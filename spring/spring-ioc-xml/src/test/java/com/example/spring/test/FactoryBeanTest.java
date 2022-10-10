package com.example.spring.test;

import com.example.spring.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: 杨镇远
 * @Date: 2022/9/6-16:43
 */
public class FactoryBeanTest {
    @Test
    public void testFactoryBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-factory.xml");

        User user = context.getBean(User.class);


    }
}
