package com.example.spring.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: yzy
 * @Date: 2022/10/29-17:21
 * @Description:
 */
public class FirstSpringTest {

    @Test
    public void testFirstSpring() {
        // 获取Spring容器对象
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        // 根据id获取bean
        Object user = context.getBean("userBean");
        System.out.println(user);


        Object nowTime = context.getBean("dateBean");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        String nowTimeStr = sdf.format((Date) nowTime);
        System.out.println(nowTimeStr);

    }

}
