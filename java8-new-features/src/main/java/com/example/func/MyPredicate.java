package com.example.func;

/**
 * @Author: yzy
 * @Date: 2022/9/21-14:29
 * @Description:
 */
public interface MyPredicate<T> {
    /**
     * 对传递过来的T类型的数据进行过滤
     * @param t T类型的数据
     * @return 符合规则则返回true，不符合返回false
     */
    boolean filter(T t);
}
