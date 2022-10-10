package com.example.threadlocal.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yzy
 * @Date: 2022/9/21-22:42
 * @Description:
 */
public class MyThreadLocal<T> {
    private Map<Thread, T>  map = new HashMap<>();


    public void set(T o) {
        map.put(Thread.currentThread(), o);
    }

    public T get() {
        return map.get(Thread.currentThread());
    }

    public void remove() {
        map.remove(Thread.currentThread());
    }
}
