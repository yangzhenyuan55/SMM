package com.example.bank.web.servlet;

import com.example.bank.exception.AppException;
import com.example.bank.exception.MoneyNotEnoughException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * @Author: yzy
 * @Date: 2022/9/21-16:53
 * @Description: 在不使用mvc架构模式的前提下完成转账
 */
@WebServlet("/transfer")
public class AccountTransferServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 响应流
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        // 获取转账相关的信息
        String fromActno = request.getParameter("fromActno");
        String toActno = request.getParameter("toActno");
        double money = Double.parseDouble(request.getParameter("money"));

        // 转账
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        ResultSet rs = null;
        try {
            // 注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 获取连接
            String url = "jdbc:mysql://localhost:3306/mvc";
            String username = "root";
            String password = "20160910yzy";
            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false);
            String sql1 = "select balance from t_act where act_no=?";
            ps1 = conn.prepareStatement(sql1);
            ps1.setString(1, fromActno);
            rs = ps1.executeQuery();
            if(rs.next()) {
                double balance = rs.getDouble("balance");
                if(balance < money) {
                    // 余额不足
                    throw new MoneyNotEnoughException("余额不足");
                }
            }
            // 余额足够
            String sql2 = "update t_act set balance=balance-? where act_no=?";
            ps2 = conn.prepareStatement(sql2);
            ps2.setDouble(1, money);
            ps2.setString(2, fromActno);
            int cnt = ps2.executeUpdate();

            String sql3 = "update t_act set balance=balance+? where act_no=?";
            ps3 = conn.prepareStatement(sql3);
            ps3.setDouble(1, money);
            ps3.setString(2, toActno);
            cnt += ps3.executeUpdate();

            if(cnt != 2) {
                throw new AppException("App异常");
            }

            System.out.println("影响" + cnt + "条");
            conn.commit();

        } catch (Exception e) {
            if(conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            out.print(e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            if(ps1 != null) {
                try {
                    ps1.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(ps2 != null) {
                try {
                    ps2.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(ps3 != null) {
                try {
                    ps3.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        out.print("转账成功");

    }
}
