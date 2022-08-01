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
}