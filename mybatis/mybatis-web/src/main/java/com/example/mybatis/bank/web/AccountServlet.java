package com.example.mybatis.bank.web;

import com.example.mybatis.bank.exception.MoneyNotEnoughException;
import com.example.mybatis.bank.exception.TransferAccountException;
import com.example.mybatis.bank.service.AccountService;
import com.example.mybatis.bank.service.impl.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: yzy
 * @Date: 2022/9/22-15:00
 * @Description:
 */
@WebServlet("/transfer")
public class AccountServlet extends HttpServlet {
    private AccountService accountService = new AccountServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");


        String fromActNo = request.getParameter("fromActNo");
        String toActNo = request.getParameter("toActNo");
        Double money = Double.parseDouble(request.getParameter("money"));

        // 调用service的转账方法完成转账
        try {
            accountService.transferAccount(fromActNo, toActNo, money);
            // 运行到此处，表示转账成功
            response.sendRedirect("success.html");
        } catch (MoneyNotEnoughException e) {
            response.sendRedirect("error1.html");

        } catch (TransferAccountException e) {
            response.sendRedirect("error2.html");

        } catch (Exception e) {
            response.sendRedirect("error2.html");
        }

        // 调用view完成展示结果


    }
}
