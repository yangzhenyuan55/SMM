package com.example.bank.service.impl;

import com.example.bank.dao.Dao;
import com.example.bank.dao.impl.AccountDaoImpl;
import com.example.bank.exception.MoneyNotEnoughException;
import com.example.bank.pojo.Account;
import com.example.bank.service.AccountService;


/**
 * @Author: yzy
 * @Date: 2022/9/21-19:46
 * @Description:
 */
public class AccountServiceImpl implements AccountService {

    private final Dao<Account> dao = new AccountDaoImpl();

    private final int CHANGE_COUNT = 2;

    public AccountServiceImpl(){}


    @Override
    public void transferAccounts(String fromActNo, String toActNo, Double money) throws Exception {
        dao.setAutoCommit(false);
        Account fromAccount = dao.selectByActNo(fromActNo);
        // 查询余额是否充足
        if(!balanceIsEnough(fromAccount.getBalance(), money)) {

            throw new MoneyNotEnoughException("余额不足");
        }
        // 余额充足，开始转账
        Account toAccount = dao.selectByActNo(toActNo);
        // 减少转入账户余额
        fromAccount.setBalance(fromAccount.getBalance() - money);
        int changeCount = dao.update(fromAccount);

        // 增加转入账户余额
        toAccount.setBalance(toAccount.getBalance() + money);
        changeCount += dao.update(toAccount);
        if(changeCount != CHANGE_COUNT) {
            dao.rollBack();
            throw new Exception("账户转账异常");
        }
        dao.commit();
        dao.closeConnection();
    }

    /**
     * 余额是否充足
     * @param balance 余额
     * @param money 转账金额
     * @return 充足返回true，不足返回false
     */
    private boolean balanceIsEnough(Double balance, Double money) {
        return balance >= money;
    }
}
