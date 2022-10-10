package com.example.spring.service.impl;


import com.example.spring.dao.BookDao;
import com.example.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: yzy
 * @Date: 2022/9/16-17:12
 * @Description:
 */
@Service
public class BookServiceImpl implements BookService {

    private BookDao bookDao;

    public BookDao getBookDao() {
        return bookDao;
    }

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    /**
     * * @Transactional -- 标识此注解能够对该方法进行事务管理
     * Transactional 注解的属性:
     * readOnly(default=false) ---- 对于只涉及查询操作的一个方法，我们可以将此属性设置为true，明确告诉数据库不涉及写操作，能够针对查询操作进行优化
     * timeout ---- 设置超时时间，超过此时间没有执行完马上进行回滚释放资源，单位秒
     * rollbackFor/rollbackForClassName ---- 指定某种异常，如果发生此种异常那么则回滚
     * noRollbackFor ---- 不因为某种异常而进行回滚
     * isolation ---- 设置隔离级别
     *
     */
    @Override
    @Transactional(
            rollbackFor = Exception.class,
            readOnly = false,
            timeout = 3,
            isolation = Isolation.DEFAULT,
            propagation = Propagation.REQUIRES_NEW
    )
    public void buyBook(Integer userId, Integer bookId) {
        // 查询图书的价格
        int bookPrice = bookDao.getPriceByBookId(bookId);

        // 更新图书的库存
        bookDao.updateBookStock(bookId);

        // 更新用户的余额
        bookDao.updateUserBalance(userId, bookPrice);
    }
}






















