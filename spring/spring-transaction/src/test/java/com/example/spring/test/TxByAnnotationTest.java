package com.example.spring.test;

import com.example.spring.controller.BookController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: yzy
 * @Date: 2022/9/16-17:40
 * @Description: 通过注解来实现声明式事务
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:tx-annotation.xml")
public class TxByAnnotationTest {

    @Autowired
    private BookController bookController;

    @Test
    public void testBuyBook() {
        // 此时用户余额为50，而书的价格为80，默认每一个语句为一个事务并且自动提交，这里用户的余额不可能为负数，但是库存却减少了，这就不是一个完整的过程
        // 要把减少库存和余额减少放在同一个事务中，要么两个操作都成功，要么都失败然后进行回滚
        bookController.buyBook(1, 1);
    }

    /**
     * 测试购买多本书然后结账
     */
    @Test
    public void testCheckout() {
        // id为1的用户购买id为1，2的书
        // 注意：在BookService的buyBook()方法和CheckoutService的checkout() 方法都分别设置了事务，那么会默认使用checkout()方法的事务
        // 也就是说，只要有一本书买不了，那么全部都买不了，都会进行回滚
        // 如何要设置使用buyBook()方法的事务
        // 在buyBook方法的注解@Transactional注解中设置属性propagation =
        // 这个属性默认为Propagation.REQUIRED，也就是调用者的事务，在这个例子中，checkout()方法调用了buyBook()方法，所有默认使用checkout()的事务
        // 将此属性设置为Propagation.REQUIRES_NEW，在执行buyBook()的时候会重新开启一个新的事务，也就是说来使用自己的事务
        // 用户1 余额为 100
        // 书1价格：80， 书2价格：50
        // 这个时候能够成功购买一本书，购买第二本书的时候余额不足报错然后回滚，
        bookController.checkout(1, new Integer[]{1, 2});

        /*
        结果如下:成功购买一本，购买第二本失败
        +---------+----------+----------+---------+
        | user_id | username | password | balance |
        +---------+----------+----------+---------+
        |       1 | 张三     | 123456   |      20  |
        |       2 | 李四     | 123456   |     200  |
        +---------+----------+----------+---------+

        +---------+------------------+-------+-------+
        | book_id | book_name        | price | stock |
        +---------+------------------+-------+-------+
        |       1 | C语言程序设计    |    80 |    99   |
        |       2 | Java语言程序设计 |    50 |   100   |
        +---------+------------------+-------+-------+
         */
    }

}
