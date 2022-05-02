package com.zillennium.utswap.screens.setting.MyWallet;

public class MyWalletItem {

    String date, transaction, transactionDetail, moneyIn, moneyOut, Balance;
    int imagePath;

    public MyWalletItem(int imagePath, String date, String transaction, String transactionDetail, String moneyIn, String moneyOut, String balance) {
        this.date = date;
        this.transaction = transaction;
        this.transactionDetail = transactionDetail;
        this.moneyIn = moneyIn;
        this.moneyOut = moneyOut;
        this.Balance = balance;
        this.imagePath = imagePath;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getTransactionDetail() {
        return transactionDetail;
    }

    public void setTransactionDetail(String transactionDetail) {
        this.transactionDetail = transactionDetail;
    }

    public String getMoneyIn() {
        return moneyIn;
    }

    public void setMoneyIn(String moneyIn) {
        this.moneyIn = moneyIn;
    }

    public String getMoneyOut() {
        return moneyOut;
    }

    public void setMoneyOut(String moneyOut) {
        this.moneyOut = moneyOut;
    }

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String balance) {
        Balance = balance;
    }

    public int getImagePath() {
        return imagePath;
    }

    public void setImagePath(int imagePath) {
        this.imagePath = imagePath;
    }
}
