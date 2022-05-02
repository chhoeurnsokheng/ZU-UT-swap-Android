package com.zillennium.utswap.models.SubscriptionHistory;

import java.io.Serializable;

public class SubscriptionHistory implements Serializable {
    String nameSub, value, date,price, volume, transactionId;

    public SubscriptionHistory(String nameSub, String value, String date, String price, String volume, String transactionId) {
        this.nameSub = nameSub;
        this.value = value;
        this.date = date;
        this.price = price;
        this.volume = volume;
        this.transactionId = transactionId;
    }

    public String getNameSub() {
        return nameSub;
    }

    public String getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }

    public String getPrice() {
        return price;
    }

    public String getVolume() {
        return volume;
    }

    public String getTransactionId() {
        return transactionId;
    }
}
