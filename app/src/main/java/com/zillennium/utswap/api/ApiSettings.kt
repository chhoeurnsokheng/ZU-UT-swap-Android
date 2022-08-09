package com.zillennium.utswap.api

/**
 * @author chhoeurnsokheng
 * Created 5/7/22 at 4:42 PM
 * By Mac
 */

object ApiSettings {
    const val VERSION = ""

    const val PATH_VERIFY_CODE = "login/submit"

    const val PATH_USER_LOGIN = "login/submit"

    /** OTP */
    const val PATH_OTP = "login/verify_code"

    /** News */
    const val PATH_NEWS = "Art/ArtList"
    const val PATH_NEWS_DETAIL = "Art/ArtShow"


    /** Finance */
    const val PATH_SUBSCRIPTION = "Issue/log"
    const val PATH_LOCK_UP = "finance/lockbalance"
    /** Register */
    const val PATH_REGISTER = "login/register"

    /** Forgot Password*/
    const val PATH_FORGOT_PASSWORD = "login/forgetPassword"
    const val PATH_FORGOT_PASSWORD_VERIFY = "login/verifyForgetPassword"
    const val PATH_ENTER_NEW_PASSWORD = "login/changeNewPassword"
    /*User add KYC */
    const val PATH_KYC = "user/submitkyc"

    const val PATH_GET_PROVINCES = "user/getCityProvince"

    /** Add Phone Number*/
    const val PATH_ADD_PHONE_NUMBER = "add_phone_number"
    const val PATH_VERIFY_CODE_PHONE = "add_phone_number/verify_code"

    /** App Side Bar*/
    const val PATH_APP_SIDE_BAR = "user/profile"

    /** Check User Login Status*/
    const val PATH_CHECK_USER_LOGIN_STATUS = "user/checkUserLogin"

     /*Get Banner HomeScreen*/
    const val PATH_GET_BANNNER= "portfolio/app_banner"
}
