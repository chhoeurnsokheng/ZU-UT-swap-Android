package com.zillennium.utswap.models.financeTransfer

object Transfer {

    /** Get Transfer */
    class GetTransfer{
        var status: Int? = null
        var message: String? = null
        var data: GetTransferData? = null
    }
    class GetTransferData{
        var status: String? = null
        var amount: String? = null
        var trx_transfer: String? = null
        var trx_date: String? = null
        var sender: String? = null
        var receiver:String? = null
    }
    class GetTransferObject(
        var sign_type: String? = null,
        var sign: String? = null,
        var amount: String? = null,
        var currency: String? = null,
        var receiver: String? = null,
        var paypassword: String? = null
    )

    /** Validate Transfer */
    class GetValidateTransfer{
        var status: Int? = null
        var message: String? = null
        var data: GetValidateTransferData? = null
    }
    class GetValidateTransferData{
        var to_cellphone: String? = null
        var to_account: String? = null
        var balance_transfer: String? = null
        var fee: String? = null
        var total: String? = null
    }
    class GetValidateTransferObject(
        var amount: String? = null,
        var currency: String? = null,
        var receiver: String? = null
    )
}