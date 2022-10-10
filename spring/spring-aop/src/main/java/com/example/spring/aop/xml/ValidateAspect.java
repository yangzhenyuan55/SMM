package com.example.spring.aop.xml;

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
public class ValidateAspect {

    public void beforeMethod() {
        System.out.println("ValidateAspect--->beforeMethod--->前置通知");
    }
}
