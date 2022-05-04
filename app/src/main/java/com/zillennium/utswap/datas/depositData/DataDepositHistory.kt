package com.zillennium.utswap.datas.depositData

import com.zillennium.utswap.R
import com.zillennium.utswap.models.DepositHistory.DepositHistory


object DataDepositHistory {
    fun LIST_OF_DEPOSIT_HISTORY(): ArrayList<DepositHistory> {
        val listDeposit: ArrayList<DepositHistory> = ArrayList<DepositHistory>()
        listDeposit.add(
            DepositHistory(
                R.drawable.ic_circle_check,
                "TR202201-1641891406024730",
                "Online bank transfer",
                "$ 185 700.00",
                "$ 0.00",
                "$ 185 700.00",
                "2022-01-11 15:56:46",
                "Successfully"
            )
        )
        listDeposit.add(
            DepositHistory(
                R.drawable.ic_circle_check,
                "TR202201-1641870452651405",
                "Online bank transfer",
                "$ 500.00",
                "$ 0.00",
                "$ 500.00",
                "2022-01-11 10:07:32",
                "Successfully"
            )
        )
        return listDeposit
    }
}