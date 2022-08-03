package com.zillennium.utswap.models.userService

object User {

    /**Login Response*/
    class LoginRes{
        var status: Int? = null
        var message: String? = null
        var data: LoginData? = null
    }

    class LoginData{
        var ID: String? = null
        var TOKEN: String? = null
        var x_api_key: String? = null
        var fundpass: Int? = null
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
        var truename:String? =null
        var gender = ""
        var occupation =""
        var companyname =""
        var email = ""
        var citycode = ""
        var districtcode  = ""
        var communecode  = ""
        var streetnumber =""
        var idcardinfo = ""
        var idcardfront =""
        var idcardrear =""
        var userImage =""
        var idcard =""
        var termandcondition = ""
        var paypassword =""
        var repaypassword =""
    }




    /** Register */
    class RegisterRes{
        var status: Int? = null
        var message: String? = null
        var data: RegisterData? = null
    }

    class RegisterData{
        var secure_key: String? = null
    }

    class RegisterObject(
        var username: String?,
        var password: String?,
        var verify: String?,
        var invit: String?
    )

}