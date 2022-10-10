package com.example.spring.test;

import com.example.spring.aop.annotation.Calculator;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: yzy
 * @Date: 2022/9/9-17:28
 * @Description:
 */
public class AOPByAnnotationTest {
    /**
     * 基于注解的AOP
     */
    @Test
    public void testAOPByAnnotation() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("aop-annotation.xml");
        /*Calculator calculator = ioc.getBean(CalculatorImpl.class); // 获取失败 NoSuchBeanDefinitionException，使用AOP之后不能获取目标对象，只能通过代理对象来访问目标对象*/
        Calculator calculator = ioc.getBean(Calculator.class);
        calculator.div(6, 0);
        /*calculator.sub(1, 1);
        calculator.mul(1, 4);
        calculator.div(3, 0);*/
    }


}
