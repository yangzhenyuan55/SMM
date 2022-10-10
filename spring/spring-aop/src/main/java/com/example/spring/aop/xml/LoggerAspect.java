package com.example.spring.aop.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author: yzy
 * @Date: 2022/9/9-17:10
 * @Description: 日志切面类
 * 除了使用@Component表示为普通组件进行扫描之外，还需要将这个类标识为切面类，使用@Aspect注解标识
 */
@Component
public class LoggerAspect {



    /**
     * 前置通知
     * @param joinPoint 连接点对象。简单理解为切入点表达式定位的是哪一个方法，那么joinPoint连接点对应的就是这个切入点定位的方法
     */

    public void beforeAdviceMethod(JoinPoint joinPoint) {
        // 如何获取执行方法的参数(也就是Calculator中的方法的信息)

        // 获取连接点所对应的方法的方法名
        // joinPoint.getSignature() 获取方法的签名信息，也就是方法的声明信息
        String methodName = joinPoint.getSignature().getName();

        // 获取方法的参数
        Object[] args = joinPoint.getArgs();

        System.out.println("LogInfo--->LoggerAspect.beforeAdviceMethod, method=" + methodName + ", 参数=" + Arrays.toString(args));
    }

    /**
     * 最终后置通知，但是并不确定是在目标方法的后面还是在finally中，
     * 使用div(1, 0) 来进行测试，如果抛出异常之后这个后置通知还能执行说明在finally，反之说明在目标方法的后面
     * 通过测试可知，由注解@After标注的这个后置通知，其执行位置为finally中
     * @param joinPoint 连接点
     */
    public void afterAdviceMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();


        System.out.println("LogInfo--->LoggerAspect.afterAdviceMethod, method=" + methodName + ", 执行完毕");
    }

    /**
     * 返回通知(其位置于目标方法执行完毕之后，也就是目标方法返回之后)
     * @param joinPoint 连接点
     * @param result 接收目标方法执行的返回值，要在注解中标识接收返回值的参数名，下面指定接收返回值的参数名为result
     */
    public void afterReturningAdviceMethod(JoinPoint joinPoint, Object result) {

        System.out.println("LogInfo--->LoggerAspect.afterReturningAdviceMethod, method=" + joinPoint.getSignature().getName()  +  ", 目标方法执行完毕, " + "执行结果=" + result);
    }

    /**
     * 异常通知
     * @param joinPoint 连接点
     */
    public void afterThrowingAdviceMethod(JoinPoint joinPoint, Exception exception) {
        System.out.println("LogInfo--->LoggerAspect.afterThrowingAdviceMethod, method=" + joinPoint.getSignature().getName() + ", 发生异常: " + exception.toString());
    }


}
