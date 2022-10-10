package com.example.spring.service.impl;

import com.example.spring.service.BookService;
import com.example.spring.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: yzy
 * @Date: 2022/9/19-14:26
 * @Description: 结账服务实现类
 */
@Service
public class CheckoutServiceImpl implements CheckoutService {

    private BookService bookService;

    public BookService getBookService() {
        return bookService;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkout(Integer userId, Integer[] bookIds) {
        // 循环购买每一本书
        for (Integer bookId : bookIds) {
            bookService.buyBook(userId, bookId);
        }
    }
}
