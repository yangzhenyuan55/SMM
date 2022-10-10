package com.example.thread;

import com.example.pojo.Account;

/**
 * @Author: yzy
 * @Date: 2022/10/4-19:45
 * @Description:
 */
public class AccountThread extends Thread {
    private Account act;

    public AccountThread(Account act) {
        this.act = act;
    }

    @Override
    public void run() {

        double money = 5000;
        act.withdraw(money);

        System.out.println(Thread.currentThread().getName() +","+ act + "成功取款:" + money);
    }
}