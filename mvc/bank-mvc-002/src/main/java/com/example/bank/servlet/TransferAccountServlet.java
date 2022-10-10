package com.example.bank.servlet;


import com.example.bank.exception.MoneyNotEnoughException;
import com.example.bank.service.AccountService;
import com.example.bank.service.impl.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: yzy
 * @Date: 2022/9/21-21:05
 * @Description:
 */
@WebServlet("/transfer")
public class TransferAccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String fromActNo = request.getParameter("fromActNo");
        String toActNo = request.getParameter("toActNo");
        String money = request.getParameter("money");

        try {
            AccountService accountService = new AccountServiceImpl();
            accountService.transferAccounts(fromActNo, toActNo, Double.parseDouble(money));
            out.print("转账成功");
            response.sendRedirect("success.jsp");

        } catch (MoneyNotEnoughException e) {
            response.sendRedirect("moneynoenough.jsp");
        } catch (Exception e) {
            // 将异常返回前端
            out.println(e.toString());
            out.print("转账失败");
        }

    }
}
