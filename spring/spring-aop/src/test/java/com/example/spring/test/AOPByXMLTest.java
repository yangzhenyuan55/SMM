package com.example.spring.test;

import com.example.spring.aop.xml.Calculator;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: yzy
 * @Date: 2022/9/16-14:53
 * @Description:
 */
public class AOPByXMLTest {


    @Test
    public void testAOPByXML() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("aop-xml.xml");

        Calculator calculator = ioc.getBean(Calculator.class);
        calculator.div(9, 1);
    }
}
