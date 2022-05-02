package com.zillennium.utswap.models.TransferHistory;

import java.io.Serializable;

public class TransferHistory implements Serializable {
    String transactionId, time, sender, receiver, amount, total;

    public TransferHistory(String transactionId, String time, String sender, String receiver, String amount, String total) {
        this.transactionId = transactionId;
        this.time = time;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.total = total;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getTime() {
        return time;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getAmount() {
        return amount;
    }

    public String getTotal() {
        return total;
    }
}
