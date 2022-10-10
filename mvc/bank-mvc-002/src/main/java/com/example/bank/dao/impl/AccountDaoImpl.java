package com.example.bank.dao.impl;


import com.example.bank.dao.Dao;
import com.example.bank.pojo.Account;
import com.example.bank.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yzy
 * @Date: 2022/9/21-18:58
 * @Description:
 */
public class AccountDaoImpl implements Dao<Account> {
    private static final Connection conn;

    static {
        try {
            conn = DBUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setAutoCommit(boolean autoCommit){
        try {
            conn.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void rollBack() {
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void commit(){
        try {
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer insert(Account account) {

        PreparedStatement ps = null;

        int cnt = 0;
        try {
            String sql = "insert into t_act(act_no,balance) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, account.getActNo());
            ps.setDouble(2, account.getBalance());
            cnt = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null, ps, null);
        }
        return cnt;
    }

    @Override
    public Integer deleteByActNo(String actNo) {

        PreparedStatement ps = null;

        int cnt = 0;
        try {

            String sql = "delete from t_act where act_no=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, actNo);

            cnt = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null, ps, null);
        }
        return cnt;
    }

    @Override
    public Integer deleteById(Long id) {

        PreparedStatement ps = null;

        int cnt = 0;
        try {

            String sql = "delete from t_act where id=?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);

            cnt = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null, ps, null);
        }
        return cnt;
    }

    @Override
    public Integer update(Account account) {
        PreparedStatement ps = null;

        int cnt = 0;
        try {
            String sql = "update t_act set act_no=?,balance=? where id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, account.getActNo());
            ps.setDouble(2, account.getBalance());
            ps.setLong(3, account.getId());

            cnt = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null, ps, null);
        }
        return cnt;
    }

    @Override
    public Account selectByActNo(String actNo) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Account account = new Account();
        int cnt = 0;
        try {
            String sql = "select * from t_act where act_no=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, actNo);
            rs = ps.executeQuery();

            if (rs.next()) {
                account.setId(rs.getLong("id"));
                account.setActNo(rs.getString("act_no"));
                account.setBalance(rs.getDouble("balance"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null, ps, rs);
        }
        return account;
    }

    @Override
    public List<Account> selectAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Account> accountList = new ArrayList<>();
        int cnt = 0;
        try {

            String sql = "select * from t_act";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                accountList.add(
                        new Account(
                                rs.getLong("id"),
                                rs.getString("act_no"),
                                rs.getDouble("balance")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(null, ps, rs);
        }
        return accountList;
    }

}
