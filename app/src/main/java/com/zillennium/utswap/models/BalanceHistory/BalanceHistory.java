package com.zillennium.utswap.models.BalanceHistory;

import java.io.Serializable;

public class BalanceHistory implements Serializable {
    String date, transaction, transactionDetail, moneyIn, moneyOut, Balance;
    int imagePath;

    public BalanceHistory(int imagePath, String date, String transaction, String transactionDetail, String moneyIn, String moneyOut, String balance) {
        this.imagePath = imagePath;
        this.date = date;
        this.transaction = transaction;
        this.transactionDetail = transactionDetail;
        this.moneyIn = moneyIn;
        this.moneyOut = moneyOut;
        Balance = balance;
    }

    public int getImagePath() {
        return imagePath;
    }

    public String getDate() {
        return date;
    }

    public String getTransaction() {
        return transaction;
    }

    public String getTransactionDetail() {
        return transactionDetail;
    }

    public String getMoneyIn() {
        return moneyIn;
    }

    public String getMoneyOut() {
        return moneyOut;
    }

    public String getBalance() {
        return Balance;
    }
}
