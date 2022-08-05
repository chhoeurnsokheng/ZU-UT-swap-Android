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
        var status_kyc: Boolean? = null
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

    /** Add Phone Number*/
    class AddPhoneNumberRes{
        var status: Int? = null
        var message: String? = null
        var data: AddPhoneNumberData? = null
    }

    class AddPhoneNumberData{
        var secure_key: String? = null
    }

    class AddPhoneNumberObject(
        var cellphone: String?
    )

    /** Verify Add phone number*/
    class VerifyAddPhoneNumberRes{
        var status: Int? = null
        var message: String? = null
        var data: VerifyAddPhoneNumberData? = null
    }

    class VerifyAddPhoneNumberData{
        var phone: String? = null
    }

    class VerifyAddPhoneNumberObject(
        var secure_key: String?,
        var otp_code: String?
    )

    /** App Side Bar*/
    class AppSideBarRes{
        var status: Int? = null
        var message: String? = null
        var data: AppSideBarData? = null
    }

    class AppSideBarData{
        var username: String? = null
        var truename: String? = null
        var kyc: String? = null
        var email: String? = null
        var phonenumber: String? = null
        var ocupation: String? = null
        var company_name: String? = null
        var address: String? = null
        var image_profile: String? = null
        var mobile: String? = null
        var image_lavel: String? = null
        var name_user_lavel: String? = null
    }

    /** Check User Login Status*/
    class CheckUserLoginStatusRes{
        var status: Int? = null
        var message: String? = null
        var data: Any? = null
    }

    class CheckUserLoginStatusData{
        var user_id: Int? = null
    }
}