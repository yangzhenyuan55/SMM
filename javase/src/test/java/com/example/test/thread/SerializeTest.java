package com.example.test.thread;

import com.example.pojo.User;
import org.junit.Test;

import java.io.*;

/**
 * @Author: yzy
 * @Date: 2022/10/3-19:06
 * @Description:
 */
public class SerializeTest {

    /**
     * 序列化测试
     * 序列化：将Java对象存储到文件中，将Java对象保存下来的过程
     * 反序列化：将硬盘上的数据重新恢复到内存当中，恢复成Java对象
     */
    @Test
    public void testSerializable() throws Exception {

        User user = new User("张三", "123");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/resources/userinfo"));
        // 序列化
        out.writeObject(user);
        out.flush();
        out.close();


        // 反序列化
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("src/main/resources/userinfo"));
        Object obj = input.readObject();
        System.out.println(obj);
        input.close();
    }
}
