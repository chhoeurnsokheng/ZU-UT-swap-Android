package com.zillennium.utswap.Datas.ListDatas.historicalData

import com.zillennium.utswap.R
import com.zillennium.utswap.models.Historical.Historical


object DataHistorical {
    fun LIST_OF_HISTORICAL_HISTORY(): ArrayList<Historical> {
        val listHistorical: ArrayList<Historical> = ArrayList<Historical>()
        listHistorical.add(
            Historical(
                R.drawable.ic_arrow_down_right,
                "Sold 10UT New Airport 38Ha @ 18.71",
                "28-Feb-2022",
                "$ 187.10",
                "10",
                "$ 18.71",
                "$ 187.10",
                "6 822"
            )
        )
        listHistorical.add(
            Historical(
                R.drawable.ic_arrow_down_right,
                "Sold 25UT New Airport 38Ha @ 18.75",
                "28-Feb-2022",
                "$ 468.75",
                "25",
                "$ 18.75",
                "$ 468.75",
                "6 842"
            )
        )
        return listHistorical
    }
}