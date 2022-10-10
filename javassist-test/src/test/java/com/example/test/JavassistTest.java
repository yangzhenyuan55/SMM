package com.example.test;


import com.example.bank.dao.AccountDao;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.junit.Test;


import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @Author: yzy
 * @Date: 2022/9/22-16:50
 * @Description:
 */
public class JavassistTest {



    @Test
    public void testGenerateAccountDaoImpl() throws Exception{
        ClassPool pool = ClassPool.getDefault();

        // 制造类
        CtClass ctClass = pool.makeClass("com.example.bank.dao.impl.AccountDaoImpl");
        // 制造接口
        CtClass ctInterface = pool.makeInterface("com.example.bank.dao.AccountDao");
        // 实现接口
        ctClass.addInterface(ctInterface);

        // 实现接口中的所有方法
        Method[] methods = AccountDao.class.getDeclaredMethods();
        Stream.of(methods).forEach(method -> {

            StringBuilder methodCode = new StringBuilder();

            methodCode.append("public "); // 拼接方法的修饰符列表，如 public
            methodCode.append(method.getReturnType().getName() + " "); // 追加返回值类型
            methodCode.append(method.getName()); // 追加方法名
            methodCode.append("(");
            // 拼接参数列表
            Parameter[] parameters = method.getParameters();
            methodCode.append(Arrays.toString(parameters).replaceAll("[\\[\\]]", ""));
            methodCode.append("){System.out.println(111); ");
            // 拼接返回值
            String returnType = method.getReturnType().getSimpleName();
            if ("void".equals(returnType)) {

            } else if("int".equals(returnType)) {
                methodCode.append("return 1;");
            } else if("String".equals(returnType)) {
                methodCode.append("return \"hello\";");
            }
            methodCode.append("}");
            System.out.println(methodCode);
            try {
                CtMethod ctMethod = CtMethod.make(methodCode.toString(), ctClass);
                ctClass.addMethod(ctMethod);
            } catch (CannotCompileException e) {
                throw new RuntimeException(e);
            }

        });


        // 在内存中生成类，并加载到JVM中
        Class<?> clazz = ctClass.toClass();
        // 创建对象
        AccountDao accountDao = (AccountDao) clazz.newInstance();

        // 调用方法
        accountDao.delete();
        accountDao.selectByActNo("a");
        accountDao.insert("a");
        accountDao.update("a", 1.0);
    }

    @Test
    public void testGenerateFirstClass() throws Exception {
        ClassPool pool = ClassPool.getDefault();

        // 生成类
        CtClass ctClass = pool.makeClass("com.example.bank.dao.impl.AccountDaoImpl");

        // 生成方法
        CtMethod ctMethod = CtMethod.make("public static java.lang.Integer insert(int a,int b,String c){System.out.println(\"insert method\");return new Integer(1);}", ctClass);
        // 将方法添加到类中
        ctClass.addMethod(ctMethod);
        // 生成class
        Class<?> aClass = ctClass.toClass();
        // 创建对象
        Object obj = aClass.newInstance();
        // 获取类的方法
        Method[] insert = aClass.getDeclaredMethods();


        Parameter[] parameters = insert[0].getParameters();
        System.out.println(Arrays.toString(parameters).replaceAll("[\\[\\]]", ""));
        System.out.println(insert[0].getReturnType().getSimpleName());


    }


}
