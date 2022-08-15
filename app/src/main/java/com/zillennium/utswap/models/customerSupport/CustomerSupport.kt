package com.zillennium.utswap.models.customerSupport

object CustomerSupport {
    class CustomerSupportRes{
        var status: Int? = null
        var message: String? = null
        var data: CustomerSupportData? = null
    }
    class CustomerSupportData{
        var contact_cellphone: String? = null
        var contact_twitter: String? = null
        var contact_telegram: String? = null
        var contact_telegram_img: String? = null
        var contact_app_img: String? = null
    }
}