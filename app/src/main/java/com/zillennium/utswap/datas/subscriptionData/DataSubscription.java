package com.zillennium.utswap.datas.subscriptionData;

import com.zillennium.utswap.models.SubscriptionHistory.SubscriptionHistory;

import java.util.ArrayList;

public class DataSubscription {
    public static ArrayList<SubscriptionHistory> LIST_OF_SUBSCRIPTION_HISTORY() {
        ArrayList<SubscriptionHistory> listSub = new ArrayList<>();

        listSub.add(new SubscriptionHistory("UT Bew Airport 38Ha - Flipping","$ 11 994.21","2021-06-11 17:15:43","$ 8.37","1433","TR202106-1623406543109034"));
        return listSub;
    }
}
