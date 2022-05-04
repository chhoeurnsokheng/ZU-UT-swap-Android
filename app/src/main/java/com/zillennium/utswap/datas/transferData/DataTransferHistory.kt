package com.zillennium.utswap.datas.transferData

import com.zillennium.utswap.models.TransferHistory.TransferHistory


object DataTransferHistory {
    fun LIST_OF_TRANSFER_HISTORY(): ArrayList<TransferHistory> {
        val listTransfer: ArrayList<TransferHistory> = ArrayList<TransferHistory>()
        listTransfer.add(
            TransferHistory(
                "TR202201-1642181507945590",
                "2022-01-15 00:31:47",
                "011596163",
                "015833833",
                "$ 152.00",
                "$ 152.00"
            )
        )
        return listTransfer
    }
}