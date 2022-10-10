package com.example.bank.pojo;

import java.util.Objects;

/**
 * @Author: yzy
 * @Date: 2022/9/21-18:55
 * @Description: 账户实体类
 */
public class Account {
    private Long id;

    private String actNo;

    private Double balance;


    public Account() {
    }

    public Account(Long id, String actNo, Double balance) {
        this.id = id;
        this.actNo = actNo;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActNo() {
        return actNo;
    }

    public void setActNo(String actNo) {
        this.actNo = actNo;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", actNo='" + actNo + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(actNo, account.actNo) && Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, actNo, balance);
    }
}
