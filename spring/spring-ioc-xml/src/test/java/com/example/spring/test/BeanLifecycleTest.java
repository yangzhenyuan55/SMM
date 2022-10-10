package com.example.spring.test;

import com.example.spring.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: 杨镇远
 * @Date: 2022/9/6-15:59
 */
public class BeanLifecycleTest {

    /**
     * 由下面的例子可得出bean的生命周期大致过程
     * 1、实例化
     * 2、依赖注入
     * 3、初始化，需要通过bean的init-method属性来指定初始化方法
     * 4、IOC容器关闭时销毁，需要通过bean的destroy-method方法来指定销毁方法
     *
     * 加入后置处理器后，bean的生命周期为：
     * 1、实例化
     * 2、依赖注入
     * 3、后期处理器的postProcessBeforeInitialization方法
     * 4、初始化，需要通过bean的init-method属性来指定初始化方法
     * 5、后置处理器的postProcessAfterInitialization方法
     * 6、IOC容器关闭时销毁，需要通过bean的destroy-method方法来指定销毁方法
     */
    @Test
    public void testLifecycle() {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring-lifecycle.xml");

        User user = context.getBean(User.class);
        System.out.println(user);
        /*
            生命周期：1、实例化对象
            生命周期：2、依赖注入
            生命周期：3、初始化

            没有销毁是因为容器没有提供关闭的方法，所有没有销毁过程

            ConfigurableApplicationContext中提供了刷新和销毁方法
            所以我们将ApplicationContext改成ConfigurableApplicationContext
         */
        context.close();
        /*
        容器进行关闭之后出现销毁过程

            生命周期：1、实例化对象
            生命周期：2、依赖注入
            生命周期：3、初始化
            User{id=1, username='admin', password='123456', age=23}
            生命周期：4、销毁
         */

    }

    /**
     * 测试作用域对生命周期的影响
     */
    @Test
    public void testScopeInfluenceForLifecycle() {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring-lifecycle.xml");

        User user = context.getBean(User.class);
        System.out.println(user);
        context.close();
        /*
            一、当在实例化容器的时候不获取bean对象并且scope为默认（singleton）：此时容器中的bean进行了实例化、依赖注入和初始化
                生命周期：1、实例化对象
                生命周期：2、依赖注入
                生命周期：3、初始化
            原因：因为scope设置为了singleton，此时Bean是单例的，所以在实例化容器的时候就可以把bean对象先初始化。
                 因为是单例模式，没有必要在需要使用bean的时候才创建，可以在创建ioc容器的时候就把bean创建好
            二、把scope设置为prototype
            此时控制台没有输出
            原因：此时为多例模式，没有必要在创建容器的时候创建bean对象，因为在多例模式的情况下每一次获取的bean对象是不一样的
            所以在获取bean的时候才会进行bean对象的创建


            注意：在多例模式的情况下，Bean的销毁方法不由IOC容器来进行管理，所以才进行容器关闭的时候没有执行bean的销毁方法
            生命周期：1、实例化对象
            生命周期：2、依赖注入
            生命周期：3、初始化
            User{id=1, username='admin', password='123456', age=23}
         */
    }

    /**
     * 测试Bean的后置处理器
     */
    @Test
    public void testBeanPostProcessor() {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring-lifecycle.xml");

        User user = context.getBean(User.class);
        System.out.println(user);
        context.close();

        /*
        运行结果：
            生命周期：1、实例化对象
            生命周期：2、依赖注入
            MyBeanPostProcessor--->后置处理器: postProcessBeforeInitialization
            生命周期：3、初始化
            MyBeanPostProcessor--->后置处理器: postProcessAfterInitialization
            User{id=1, username='admin', password='123456', age=23}
            生命周期：4、销毁
         */


    }

}
