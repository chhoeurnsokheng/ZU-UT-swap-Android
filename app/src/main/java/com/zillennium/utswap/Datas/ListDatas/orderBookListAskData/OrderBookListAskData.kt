package com.zillennium.utswap.Datas.ListDatas.orderBookListAskData

import com.zillennium.utswap.models.orderBookList.OrderBookList

object OrderBookListAskData {
    fun LIST_OF_ORDER_BOOK_ASK(): ArrayList<OrderBookList> {
        val orderBookList: ArrayList<OrderBookList> = ArrayList<OrderBookList>()

        orderBookList.add(OrderBookList("18.90","200"))
        orderBookList.add(OrderBookList("18.91","1800"))
        orderBookList.add(OrderBookList("18.92","900"))
        orderBookList.add(OrderBookList("18.93","588"))
        orderBookList.add(OrderBookList("18.94","20000"))
        orderBookList.add(OrderBookList("18.95","888"))
        orderBookList.add(OrderBookList("18.96","300"))
        orderBookList.add(OrderBookList("18.97","200"))
        orderBookList.add(OrderBookList("18.87","97"))
        orderBookList.add(OrderBookList("18.88","927"))

        return orderBookList
    }
}