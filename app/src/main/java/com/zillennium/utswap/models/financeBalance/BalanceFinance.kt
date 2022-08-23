package com.zillennium.utswap.models.financeBalance

object BalanceFinance {

    /** Get User Balance */
    class GetUserBalanceInfo{
        var status: Int? = null
        var message: String? = null
        var data: GetUserBalanceInfoData? = null
    }

    class GetUserBalanceInfoData{
        var withdrawal_balance: String? = null
        var available_balance: String? = null
        var pending: String? = null
        var lock_up: Int? = null
        var total_balance: Double? = null
    }

    /** Finance Balance Search Date */
    class GetBalanceSearchDate{
        var status: Int? = null
        var message: String? = null
        var data: GetBalanceSearchDateData? = null
    }

    class GetBalanceSearchDateData{
        var total_page: Int? = null
        var transaction: List<GetBalanceSearchDateTransactionData> = arrayListOf()
    }

    class GetBalanceSearchDateTransactionData{
        var id: String? = null
        var type: String? = null
        var total: Double? = null
        var addtimeReadable: String?  = null
        var balance:String? = null
        var remark: String? = null
        var issue_name: String? = null
        var name: String? = null
        var transaction_type: String? = null
        var fee_admin: String? = null
    }

    class GetBalanceSearchDateObject(
        var start:String? = null,
        var end:String? = null,
        var filter_type:String? = null,
        var page: Int? = null
    )

    /** Export Finance Balance */
    class ExportFinanceBalance{
        var status: Int? = null
        var message: String? = null
        var data: ExportFinanceBalanceData? = null
    }

    class ExportFinanceBalanceData{
        var FILE_PATH: String? = null
    }

    class ExportFinanceBalanceObject(
        var start:String? = null,
        var end:String? = null,
        var filter_type:String? = null
    )
}