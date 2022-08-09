package com.zillennium.utswap.models.userService

object User {

    /**Login Response*/
    class LoginRes {
        var status: Int? = null
        var message: String? = null
        var data: LoginData? = null
    }

    class LoginData {
        var ID: String? = null
        var TOKEN: String? = null
        var x_api_key: String? = null
        var fundpass: Int? = null
        var status_kyc: Boolean? =null
        var status_submit_kyc:Boolean?=null
    }

    class LoginObject(
        var username: String?,
        var password: String?
    )

    /**OTP Code*/
    class OtpRes {
        var status: Int? = null
        var message: String? = null
        var data: OtpData? = null
    }

    class OtpData {
        var ID: String? = null
        var TOKEN: String? = null
        var x_api_key: String? = null
        var fundpass: Int? = null
    }

    class OtpObject(
        var otp_code: String?,
        var secure_key: String?
    )

    class KycList {
        var truename = ""
        var gender = ""
        var occupation = ""
        var companyname = ""
        var email = ""
        var citycode = ""
        var districtcode = ""
        var communecode = ""
        var streetnumber = ""
        var idcardinfo = ""
        var idcardfront = ""
        var idcardrear = ""
        var userImage = ""
        var idcard = ""
        var termandcondition = ""
        var paypassword = ""
        var repaypassword = ""
    }

    class KycRes {
        var status: String = " "
        var message:String =""
        var data: ItemRes? = null
    }

    class ItemRes {
        var status_kyc_submit:Boolean? = null
        var status_kyc_approved:Boolean? = null
    }

    class Kyc(
        var truename: String = "",
        var gender: String = "",
        var occupation: String = "",
        var companyname: String = "",
        var email: String = "",
        var phonenumber: String = "",
        var cellphones: String = "",
        var citycode: String = "",
        var districtcode: String = "",
        var communecode: String = "",
        var streetnumber: String = "",
        var idcardinfo: String = "",
        var idcardfront: String = "",
        var idcardrear: String = "",
        var userImage: String = "",
        var idcard: String = "",
        var termandcondition: String = "",
        var paypassword: String = "",
        var repaypassword: String = ""
    )

    /** Register */
    class RegisterRes {
        var status: Int? = null
        var message: String? = null
        var data: RegisterData? = null
    }

    class RegisterData {
        var secure_key: String? = null
    }

    class RegisterObject(
        var username: String?,
        var password: String?,
        var verify: String?,
        var invit: String?
    )

    /** Forgot Password*/
    class ForgotPasswordRes{
        var status: Int? = null
        var message: String? = null
        var data: ForgotPasswordData? = null
    }

    class ForgotPasswordData{
        var secure_key: String? = null
    }

    class ForgotPasswordObject(
        var username: String?
    )

    /** Forgot Password Verify*/
    class ForgotPasswordVerifyRes{
        var status: Int? = null
        var message: String? = null
        var data: ForgotPasswordVerifyData? = null
    }

    class ForgotPasswordVerifyData{
        var secure_key: String? = null
    }

    class ForgotPasswordVerifyObject(
        var otp_code: String?,
        var secure_key: String?
    )

    /** Enter New Password*/
    class EnterNewPasswordObject(
        var secure_key: String?,
        var new_password: String?,
        var verify_password: String?
    )

    class EnterNewPasswordRes{
        var status: Int? = null
        var message: String? = null
        var data: EnterNewPasswordData? = null
    }

    class EnterNewPasswordData{

    }
}