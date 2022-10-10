package com.example.spring.factory;

import com.example.spring.pojo.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Author: 杨镇远
 * @Date: 2022/9/6-16:40
 */
public class UserFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new User();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
