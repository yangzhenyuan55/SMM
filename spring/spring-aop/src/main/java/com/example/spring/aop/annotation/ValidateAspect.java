package com.example.spring.aop.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: yzy
 * @Date: 2022/9/16-14:10
 * @Description: 验证功能的切面，对计算器中输入的两个数字的验证
 */
@Component // 标识为bean组件
@Aspect // 标识为切面
@Order(1) // 设置切面的执行优先级，如不设置，默认为Integer.MAX_VALUE，value越小优先级越高
public class ValidateAspect {

    /**
     * 定义切入点表达式
     */
    @Pointcut("execution(* com.example.spring.aop.annotation.Calculator.*(..))")
    private void pointCut() {}



    @Before("pointCut()")
    public void beforeMethod() {
        System.out.println("ValidateAspect--->beforeMethod--->前置通知");
    }
}
