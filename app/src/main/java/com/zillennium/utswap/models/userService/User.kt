package com.zillennium.utswap.models.userService

object User {

    /**Login Response*/
    class LoginRes{
        var status: Int? = null
        var message: String? = null
        var data: LoginData? = null
    }

    class LoginData{
        var secure_key: String? = null
    }

    class LoginObject(
        var username: String?,
        var password: String?
    )

    /**OTP Code*/
    class OtpRes{
        var status: Int? = null
        var message: String? = null
        var data: OtpData? = null
    }

    class OtpData{
        var ID: String? = null
        var TOKEN: String? = null
        var x_api_key: String? = null
        var fundpass: Int? = null
    }

    class OtpObject(
        var otp_code: String?,
        var secure_key: String?
    )

    class Kyc {
        var truename:String? = ""
        var gender:String? =""
        var occupation:String? =""
        var companyname:String? =""
        var email:String? =""
        var citycode:String? =""
        var districtcode:String? =""
        var communecode:String? =""
        var streetnumber:String? =""
        var idcardinfo:String? = ""
        var idcardfront:String? =""
        var idcardrear:String? =""
        var userImage:String? =""
        var idcard:String? =""
        var termandcondition:String? =""
        var paypassword:String? =""
        var repaypassword:String? =""

    }
}