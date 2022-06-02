package com.zillennium.utswap.Datas.ListDatas.orderBookListBidData

import com.zillennium.utswap.models.orderBookList.OrderBookList

object OrderBookListBidData {
    fun LIST_OF_ORDER_BOOK_BID(): ArrayList<OrderBookList> {
        val orderBookList: ArrayList<OrderBookList> = ArrayList<OrderBookList>()

        orderBookList.add(OrderBookList("18.8","130"))
        orderBookList.add(OrderBookList("18.88","30"))
        orderBookList.add(OrderBookList("18.87","50"))
        orderBookList.add(OrderBookList("18.86","310"))
        orderBookList.add(OrderBookList("18.85","100"))
        orderBookList.add(OrderBookList("18.80","1300"))
        orderBookList.add(OrderBookList("18.75","100"))
        orderBookList.add(OrderBookList("18.59","10"))
        orderBookList.add(OrderBookList("18.80","300"))
        orderBookList.add(OrderBookList("18.81","30"))

        return orderBookList
    }
}