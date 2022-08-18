package com.zillennium.utswap.models.financeHistorical

object Historical {

    /** Get Market Name */
    class GetMarketName{
        var status: Int? = null
        var message: String? = null
        var data: List<GetMarketNameData> = arrayListOf()
    }
    class GetMarketNameData{
        var id: String? = null
        var name: String? = null
        var coinname: String? = null
        var buycoin: String? = null
        var market_name: String? = null
    }

    /** User Transaction */
    class UserTransaction{
        var status: Int? = null
        var message: String? = null
        var data: UserTransactionData? = null
    }
    class UserTransactionData{
        var TYPE: String? = null
        var TOTAL_PAGE: Int? = null
        var TRANSACTION: List<DataTransaction> = arrayListOf()
    }
    class DataTransaction{
        var id: String? = null
        var name: String? = null
        var coinname: String? = null
        var price: Double? = null
        var type: String? = null
        var color: String? = null
        var mum_a: Double? = null
        var remark: String? = null
        var addtimeReable: String? = null
    }
    class UserTransactionObject(
        var market: String?,
        var start_date: String?,
        var end_date: String?,
        var type: String?,
        var page: String?
    )

    /** Trade Transaction */
    class TradeTransaction{
        var status: Int? = null
        var message: String? = null
        var data: TradeTransactionData? = null
    }
    class TradeTransactionData{
        var TOTAL_PAGE: Int? = null
        var TRADE_TRANSACTION: List<DataTradeTransaction> = arrayListOf()
    }
    class DataTradeTransaction{
        var id: String? = null
        var market: String? = null
        var opened_price: String? = null
        var closed_price: String? = null
        var min_value: String? = null
        var high_value: String? = null
        var volume: String? = null
        var addtimeReadble: String? = null
    }
    class TradeTransactionObject(
        var market: String?,
        var start_date: String?,
        var end_date: String?,
        var page: String?
    )

    /** All Transaction */
    class AllTransaction{
        var status: Int? = null
        var message: String? = null
        var data: AllTransactionData? = null
    }
    class AllTransactionData{
        var TOTAL_PAGE:Int? = null
        var TRADE_TRANSACTION:List<DataAllTransaction> = arrayListOf()
    }
    class DataAllTransaction{
        var addtime: String? = null
        var transaction_id: String? = null
        var type: String? = null
        var price: String? = null
        var num: String? = null
        var gross: String? = null
    }
    class AllTransactionObject(
        var market: String?,
        var start_date: String?,
        var end_date: String?,
        var page: String?
    )

    /** Export Historical Data */
    class exportHistorical{
        var status: Int? = null
        var message: String? = null
        var data: DataExportHistorical? = null
    }
    class DataExportHistorical{
        var FILE_PATH: String? = null
    }
    class exportHistoricalObject(
        var market:String? = null,
        var start_date:String? = null,
        var end_date:String? = null,
    )

    /** Model for Store Trade */
    class TradeTransactionDate(
        var TRADE_DATE: String? = null,
        var TRADE_DATE_ITEMS: ArrayList<DataTradeDateTransaction> = arrayListOf()
    )
    class DataTradeDateTransaction(
        var market: String? = null,
        var opened_price: String? = null,
        var closed_price: String? = null,
        var min_value: String? = null,
        var high_value: String? = null,
        var volume: String? = null,
        var addtimeReadble: String? = null,
    )

}