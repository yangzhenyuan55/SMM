package com.example.spring.test;

import com.example.spring.pojo.Clazz;
import com.example.spring.pojo.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: 杨镇远
 * @Date: 2022/9/5-16:30
 */
public class IOCByXMLTest {

    /**
     * 获取bean的三种方式：
     * 1、根据bean的id获取
     * 2、根据bean的类型获取
     * 3、根据id和类型同时获取
     *
     * 补充：根据类型获取bean时，在满足bean唯一性的前提下，只要【对象 instanceof 指定的类型】 返回结果为true，就可以获取到对象
     */
    @Test
    public void testIOC() {


        // 获取IOC容器
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 根据id获取bean
        // Student studentOne = (Student) ioc.getBean("studentOne");

        // 根据类型来获取
        // 但是当容器中有多个相同类型的bean的时候，此方法会报错，会获取bean失败
        // Student studentOne = ioc.getBean(Student.class);
        //

        // 根据类型和id一起获取
        Student studentOne = ioc.getBean("studentOne", Student.class);
        System.out.println(studentOne);

    }

    @Test
    public void testDI() {
        // 获取IOC容器
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 获取通过set注入的对象
        Student studentTwo = (Student) ioc.getBean("studentTwo");
        System.out.println(studentTwo);

        // 获取通过构造注入的对象
        Student studentThree = (Student) ioc.getBean("studentThree");
        System.out.println(studentThree);

        // bean对象的成员变量为对象，为其赋值（通过ref属性，引用容器中的某个bean对象为其赋值）
        Student studentFour = (Student) ioc.getBean("studentFour");
        System.out.println(studentFour); // Student{sid=1003, sname='王五', sage=25, gender='男', clazz=Clazz{cid=1111, cname='基础班'}}

        // 获取通过内部bean为bean的对象类型的成员变量赋值的bean
        Student studentSix = (Student) ioc.getBean("studentSix");
        System.out.println(studentSix); // Student{sid=1004, sname='赵四', sage=25, gender='男', clazz=Clazz{cid=1111, cname='基础班'}}
        // 注意：内部的bean不能使用容器对象获取


        // 当成员变量是List集合时
        Clazz clazzOne = (Clazz) ioc.getBean("clazzOne");
        System.out.println(clazzOne);
        // Clazz{
        // cid=1111, cname='基础班',
        // students=[
        // Student{sid=null, sname='null', sage=null, gender='null', clazz=null},
        // Student{sid=1001, sname='张三', sage=25, gender='null', clazz=null},
        // Student{sid=1002, sname='李四', sage=45, gender='女', clazz=null}
        //  ]
        // }

        Clazz clazzTwo = (Clazz) ioc.getBean("clazzTwo");
        System.out.println(clazzTwo);
        // Clazz{cid=2222, cname='基础班',
        // students=[
        // Student{sid=null, sname='null', sage=null, gender='null', clazz=null},
        // Student{sid=1001, sname='张三', sage=25, gender='null', clazz=null},
        // Student{sid=1002, sname='李四', sage=45, gender='女', clazz=null}]}
    }

}
