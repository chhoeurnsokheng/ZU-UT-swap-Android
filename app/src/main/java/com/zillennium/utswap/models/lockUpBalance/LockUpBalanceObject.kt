package com.zillennium.utswap.models.lockUpBalance

object LockUpBalanceObject {

     class LockUpBalanceRes {
        var transaction: ArrayList<LockUpBalanceList> = arrayListOf()
        var total_lock_balance: Double = 0.0
        var type: String = ""
    }

     class LockUpBalanceList {
        var truename : String = ""
        var amount : Double = 0.0
        var addtimeReable : String? = ""
        var unlocktime : Int? = 0
        var finance_id : String? = ""
        var maturity_date : Int? = 0
        var remark : String? = ""
        var unlock_by : String? = ""
        var type : Int? = 0
        var lock_period : Int? = 0
        var operation_name : String? = ""
        var operation_log_id : Int? = 0
        var addtime : Int? = 0
        var original_amount : String? = ""
        var name : String? = ""
        var nameid : String? = ""
        var id : Int? = 0
        var status : Int? = 0
    }
}