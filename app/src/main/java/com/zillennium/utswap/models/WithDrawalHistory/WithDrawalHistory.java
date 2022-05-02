package com.zillennium.utswap.models.WithDrawalHistory;

import java.io.Serializable;

public class WithDrawalHistory implements Serializable {
    String transactionId, time, amount, amountArrival, receivingAddress;

    public WithDrawalHistory(String transactionId, String time, String amount, String amountArrival, String receivingAddress) {
        this.transactionId = transactionId;
        this.time = time;
        this.amount = amount;
        this.amountArrival = amountArrival;
        this.receivingAddress = receivingAddress;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getTime() {
        return time;
    }

    public String getAmount() {
        return amount;
    }

    public String getAmountArrival() {
        return amountArrival;
    }

    public String getReceivingAddress() {
        return receivingAddress;
    }
}
