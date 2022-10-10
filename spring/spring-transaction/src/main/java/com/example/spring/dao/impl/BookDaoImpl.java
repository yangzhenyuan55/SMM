package com.example.spring.dao.impl;

import com.example.spring.dao.BookDao;
import com.example.spring.sql.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Author: yzy
 * @Date: 2022/9/16-17:13
 * @Description:
 */
@Repository
public class BookDaoImpl implements BookDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer getPriceByBookId(Integer bookId) {

        return jdbcTemplate.queryForObject(Sql.GET_PRICE_BY_BOOK_ID, Integer.class, bookId);
    }

    @Override
    public void updateBookStock(Integer bookId) {

        jdbcTemplate.update(Sql.UPDATE_BOOK_STOCK, bookId);
    }

    @Override
    public void updateUserBalance(Integer userId, int bookPrice) {
        jdbcTemplate.update(Sql.UPDATE_USER_BALANCE, bookPrice, userId);
    }
}
