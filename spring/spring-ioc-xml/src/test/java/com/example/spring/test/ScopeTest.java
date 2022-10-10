package com.example.spring.test;

import com.example.spring.pojo.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: 杨镇远
 * @Date: 2022/9/6-15:33
 */
public class ScopeTest {

    @Test
    public void testScope() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-scope.xml");

        Student student1 = context.getBean(Student.class);
        Student student2 = context.getBean(Student.class);
        System.out.println(student1 == student2); // true，说明默认情况下从容器中获取的bean是单例模式，两次获取到的是同一个bean
        // bean的属性scope默认是单例模式，可以将scope设置为"prototype"，这样就变成了多例模式


    }
}
