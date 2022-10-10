# jdk动态代理

1) ### 反射，Method类表示方法。类型红的方法，通过Method类可以执行某个方法
2) ### jdk动态代理实现
   * 反射包 java.lang.reflect里面有三个类：InvocationHandler，Method，Proxy。
     1) InvocationHandler(调用处理器) 接口中就一个方法invoke()，这个方法表示代理对象要执行的功能代码，也就是说代理类要完成的功能要写在invoke方法中。
     ```java
     class Test{
        public static void main(String[] args){
            //  public Object invoke(Object proxy, Method method, Object[] args)
            // 参数
            /*
            Object proxy ---- JVM创建的代理对象
            Method method ---- 目标类中的方法
            Object[] args ---- 目标类中的方法的参数
            */
            
        }
     }
     ```
     2) Method类：表示目标类中的方法 
     3) Proxy类：创建代理对象，通过静态方法public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)来创建代理对象
     ```text
     ClassLoader loader ---- 类加载器，目标对象的类加载器，负责向内存中加载对象，使用反射来获取对象的ClassLoader,如：aClass.getClass().getClassLoader();
     Class<?> interfaces ---- 接口，目标对象实现的接口
     InvocationHandler h ---- 调用处理器
     ```
     