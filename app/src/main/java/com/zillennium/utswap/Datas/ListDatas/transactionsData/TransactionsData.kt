package com.zillennium.utswap.Datas.ListDatas.transactionsData

import com.zillennium.utswap.models.orders.Orders

object TransactionsData {
    fun LIST_OF_TRANSACTIONS(): ArrayList<Orders> {
        val orderList: ArrayList<Orders> = ArrayList<Orders>()

        orderList.add(Orders("BUY","10 UT","24-03-2022",19.99))
        orderList.add(Orders("SELL","10 UT","21-03-2022",20.99))
        orderList.add(Orders("BUY","10 UT","24-03-2022",17.99))

        return orderList
    }
}