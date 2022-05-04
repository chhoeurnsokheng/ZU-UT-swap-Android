package com.zillennium.utswap.datas.subscriptionData

import com.zillennium.utswap.models.SubscriptionHistory.SubscriptionHistory


object DataSubscription {
    fun LIST_OF_SUBSCRIPTION_HISTORY(): ArrayList<SubscriptionHistory> {
        val listSub: ArrayList<SubscriptionHistory> = ArrayList<SubscriptionHistory>()
        listSub.add(
            SubscriptionHistory(
                "UT Bew Airport 38Ha - Flipping",
                "$ 11 994.21",
                "2021-06-11 17:15:43",
                "$ 8.37",
                "1433",
                "TR202106-1623406543109034"
            )
        )
        return listSub
    }
}
