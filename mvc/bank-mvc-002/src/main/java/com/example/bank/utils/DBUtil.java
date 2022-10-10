package com.example.bank.utils;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * @Author: yzy
 * @Date: 2022/9/21-18:36
 * @Description: jdbc工具类
 */
public class DBUtil {

    private static ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
    private static final String driver = bundle.getString("driver");
    private static final String url = bundle.getString("url");
    private static final String username = bundle.getString("username");
    private static final String password = bundle.getString("password");

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private DBUtil(){}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public static void close(Connection conn, Statement ps, ResultSet rs){
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if(ps != null) {
            try {
                ps.close();
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


}
