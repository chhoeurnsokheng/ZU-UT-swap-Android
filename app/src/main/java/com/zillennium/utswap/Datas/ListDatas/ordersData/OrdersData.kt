package com.zillennium.utswap.Datas.ListDatas.ordersData

import com.zillennium.utswap.models.orders.Orders

object OrdersData {
    fun LIST_OF_ORDERS(): ArrayList<Orders> {
        val orderList: ArrayList<Orders> = ArrayList<Orders>()

        orderList.add(Orders("Limit / Buy",10,"25-03-2022",19.99))
        orderList.add(Orders("Limit / Sell",10,"24-03-2022",17.51))
        orderList.add(Orders("Limit / Buy",10,"28-03-2022",19.98))

        return orderList
    }
}

