package com.example.mybatis.bank.utils;



import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.SqlSession;


import java.lang.reflect.Method;

import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * @Author: yzy
 * @Date: 2022/9/22-17:58
 * @Description: 动态生成DAO的实现类
 */
public class GenerateDaoProxy {

    /**
     * 生成DAO接口实现类，并且将实现类的对象创建出来
     *
     * @param daoInterface DAO接口
     * @return 返回DAO实现类实例化对象
     */
    public static Object generate(SqlSession sqlSession, Class<?> daoInterface) {
        // 类池
        ClassPool pool = ClassPool.getDefault();

        // 制造类
        CtClass ctClass = pool.makeClass(daoInterface.getName() + "Proxy"); // 实际本质上就是在内存中动态生成一个代理类。
        // 制造接口
        CtClass ctInterface = pool.makeInterface(daoInterface.getName());
        // 实现接口
        ctClass.addInterface(ctInterface);
        // 实现接口中所有的方法
        Method[] methods = daoInterface.getDeclaredMethods();
        Arrays.stream(methods).forEach(method -> {
            // method是接口中的抽象方法
            // 将method这个抽象方法进行实现
            try {

                StringBuilder methodCode = new StringBuilder();
                methodCode.append("public ");
                methodCode.append(method.getReturnType().getName());
                methodCode.append(" ");
                methodCode.append(method.getName());
                methodCode.append("(");
                // 需要方法的形式参数列表
                Parameter[] parameters = method.getParameters();
                methodCode.append(Arrays.toString(parameters).replaceAll("[\\[\\]]", ""));
                methodCode.append(")");
                methodCode.append("{");
                // 需要方法体当中的代码
                methodCode.append("org.apache.ibatis.session.SqlSession sqlSession = com.example.mybatis.bank.utils.SqlSessionUtil.openSession();");
                // 需要知道是什么类型的sql语句
                // mybatis框架规定：凡是使用GenerateDaoProxy机制的。
                // sqlId都不能随便写。namespace必须是dao接口的全限定名称。id必须是dao接口中方法名。
                String sqlId = daoInterface.getName() + "." + method.getName();
                SqlCommandType sqlCommandType = sqlSession.getConfiguration().getMappedStatement(sqlId).getSqlCommandType();
                if (sqlCommandType == SqlCommandType.INSERT) {

                }
                if (sqlCommandType == SqlCommandType.DELETE) {

                }
                if (sqlCommandType == SqlCommandType.UPDATE) {
                    methodCode.append("return sqlSession.update(\"" + sqlId + "\", arg0);");
                }
                if (sqlCommandType == SqlCommandType.SELECT) {
                    String returnType = method.getReturnType().getName();
                    methodCode.append("return (" + returnType + ")sqlSession.selectOne(\"" + sqlId + "\", arg0);");
                }

                methodCode.append("}");
                CtMethod ctMethod = CtMethod.make(methodCode.toString(), ctClass);
                ctClass.addMethod(ctMethod);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        // 创建对象
        Object obj = null;
        try {
            Class<?> clazz = ctClass.toClass();
            obj = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return obj;
    }
}
