package com.example.spring.process;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @Author: 杨镇远
 * @Date: 2022/9/6-16:25
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 此方法在Bean的生命周期初始化方法之前执行，在Bean对象进行初始化前进行一些额外的操作
        System.out.println("MyBeanPostProcessor--->后置处理器: postProcessBeforeInitialization");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 此方法在Bean的生命周期初始化方法之后执行
        System.out.println("MyBeanPostProcessor--->后置处理器: postProcessAfterInitialization");
        return bean;
    }
}
