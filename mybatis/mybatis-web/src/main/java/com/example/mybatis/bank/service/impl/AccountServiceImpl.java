package com.example.mybatis.bank.service.impl;

import com.example.mybatis.bank.dao.AccountDao;

import com.example.mybatis.bank.exception.MoneyNotEnoughException;
import com.example.mybatis.bank.exception.TransferAccountException;
import com.example.mybatis.bank.pojo.Account;
import com.example.mybatis.bank.service.AccountService;
import com.example.mybatis.bank.utils.GenerateDaoProxy;
import com.example.mybatis.bank.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * @Author: yzy
 * @Date: 2022/9/22-15:06
 * @Description:
 */
public class AccountServiceImpl implements AccountService {

    // private AccountDao accountDao = (AccountDao) GenerateDaoProxy.generate(SqlSessionUtil.openSession(), AccountDao.class);
    // 获取AccountDao实现类
    private AccountDao accountDao = SqlSessionUtil.openSession().getMapper(AccountDao.class);
    private final Integer UPDATE_COUNT = 2;

    @Override
    public void transferAccount(String fromActNo, String toActNo, Double money) throws MoneyNotEnoughException, TransferAccountException {
        // 添加事务控制代码
        SqlSession sqlSession = SqlSessionUtil.openSession();


        // 判断转出账户余额是否充足
        Account fromAccount = accountDao.selectByActNo(fromActNo);
        if(fromAccount.getBalance() < money) {
            throw new MoneyNotEnoughException("余额不足");
        }
        Account toAccount = accountDao.selectByActNo(toActNo);

        // 更新账户余额
        fromAccount.setBalance(fromAccount.getBalance() - money);
        toAccount.setBalance(toAccount.getBalance() + money);

        // 更新数据库数据
        Integer updateCnt = accountDao.updateByActNo(fromAccount);

        updateCnt += accountDao.updateByActNo(toAccount);
        if(!updateCnt.equals(UPDATE_COUNT)){
            throw new TransferAccountException("转账出现错误");
        }

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);

    }
}
