package com.example.spring.test;

import com.example.spring.pojo.HelloWorld;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: 杨镇远
 * @Date: 2022/9/5-16:19
 */
public class HelloWorldTest {

    @Test
    public void helloWorldTest() {
        // 获取IOC容器
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 获取容器中的Bean对象

        // 通过Name(Id)来获取对象
        HelloWorld helloWorld = (HelloWorld) ioc.getBean("helloWorld");
        helloWorld.sayHello();
    }
}
