package com.zillennium.utswap.datas.lockUpData

import com.zillennium.utswap.models.LockUpHistory.LockUpHistory


object DataLockUp {
    fun LIST_OF_LOCK_UP_HISTORY(): ArrayList<LockUpHistory> {
        val listLockUp: ArrayList<LockUpHistory> = ArrayList<LockUpHistory>()
        listLockUp.add(
            LockUpHistory(
                "$ 123 312.50",
                "20-Jan-2022",
                "UT Veng Sreng 2719",
                "181 Days",
                "20-Jul-2022"
            )
        )
        return listLockUp
    }
}