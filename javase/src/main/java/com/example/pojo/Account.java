package com.example.pojo;

/**
 * @Author: yzy
 * @Date: 2022/10/4-18:58
 * @Description:
 */
public class Account {

    private String actNo;


    private double balance;

    public Account() {
    }

    public Account(String actNo, double balance) {
        this.actNo = actNo;
        this.balance = balance;
    }

    public String getActNo() {
        return actNo;
    }

    public void setActNo(String actNo) {
        this.actNo = actNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void withdraw(double money) {

        synchronized (this) {
            // 取款之前的余额
            double before = this.getBalance();

            double after = before - money;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            this.setBalance(after);
        }

    }


    @Override
    public String toString() {
        return "Account{" +
                "actNo='" + actNo + '\'' +
                ", balance=" + balance +
                '}';
    }
}
