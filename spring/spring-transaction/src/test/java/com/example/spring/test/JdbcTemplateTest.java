package com.example.spring.test;



import com.example.spring.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author: yzy
 * @Date: 2022/9/16-15:26
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class) // 指定当前测试类在spring的测试环境中来执行，此时可以通过注入的方式直接来获取IOC容器中的bean
@ContextConfiguration("classpath:spring-jdbc.xml") // 来设置Spring测试环境的配置文件
public class JdbcTemplateTest {


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Test
    public void testInsert() {
        String sql = "INSERT INTO t_user(username,password) VALUES(?,?)";
        jdbcTemplate.update(sql, "王五", "123456");
    }

    /**
     * 查询功能（根据id）
     */
    @Test
    public void testGetUserById() {
        String sql = "SELECT * FROM t_user WHERE id=?";
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), 6);
        System.out.println(user);
    }

    /**
     * 获取所有的用户
     */
    @Test
    public void testGetAllUser() {
        String sql = "SELECT * FROM t_user";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));

        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testGetCount() {
        String sql = "SELECT count(*) FROM t_user";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(count);
    }
}
