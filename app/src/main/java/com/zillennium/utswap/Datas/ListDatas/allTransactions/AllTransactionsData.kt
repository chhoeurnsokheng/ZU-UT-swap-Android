package com.zillennium.utswap.Datas.ListDatas.allTransactions

import com.zillennium.utswap.models.allTransactions.AllTransactions

object AllTransactionsData {
    fun LIST_OF_ALL_TRANSACTIONS(): ArrayList<AllTransactions> {
        val allTransactions: ArrayList<AllTransactions> = ArrayList<AllTransactions>()

        allTransactions.add(AllTransactions("24-03-2022","01:02:22 PM","400","1.31","2.62"))
        allTransactions.add(AllTransactions("24-03-2022","01:02:22 PM","200","1.31","1.31"))
        allTransactions.add(AllTransactions("24-03-2022","01:02:22 PM","200","1.31","1.31"))
        allTransactions.add(AllTransactions("24-03-2022","01:02:22 PM","200","1.31","1.31"))
        allTransactions.add(AllTransactions("24-03-2022","01:02:22 PM","200","1.31","1.31"))
        allTransactions.add(AllTransactions("24-03-2022","01:02:22 PM","200","1.31","1.31"))

        return allTransactions
    }
}