package com.example.spring.controller;

import com.example.spring.service.BookService;
import com.example.spring.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @Author: yzy
 * @Date: 2022/9/16-17:10
 * @Description: Book类控制层组件
 */
@Controller
public class BookController {

    private BookService bookService;
    private CheckoutService checkoutService;

    public BookService getBookService() {
        return bookService;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public CheckoutService getCheckoutService() {
        return checkoutService;
    }

    @Autowired
    public void setCheckoutService(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    /**
     * 购买一本书
     * @param userId 用户id
     * @param bookId 书id
     */
    public void buyBook(Integer userId, Integer bookId) {
        bookService.buyBook(userId, bookId);
    }

    /**
     * 结账购买多本书
     * @param userId 用户id
     * @param bookIds 书id
     */
    public void checkout(Integer userId, Integer[] bookIds) {
        checkoutService.checkout(userId, bookIds);
    }
}
