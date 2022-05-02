package com.zillennium.utswap.models.DepositHistory;

import java.io.Serializable;

public class DepositHistory implements Serializable {
    int imagePath;
    String transactionId, depositMethod, amount, fee, netValue, rechargeTime, status;

    public DepositHistory(int imagePath, String transactionId, String depositMethod, String amount, String fee, String netValue, String rechargeTime, String status) {
        this.imagePath = imagePath;
        this.transactionId = transactionId;
        this.depositMethod = depositMethod;
        this.amount = amount;
        this.fee = fee;
        this.netValue = netValue;
        this.rechargeTime = rechargeTime;
        this.status = status;
    }

    public int getImagePath() {
        return imagePath;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getDepositMethod() {
        return depositMethod;
    }

    public String getAmount() {
        return amount;
    }

    public String getFee() {
        return fee;
    }

    public String getNetValue() {
        return netValue;
    }

    public String getRechargeTime() {
        return rechargeTime;
    }

    public String getStatus() {
        return status;
    }
}
