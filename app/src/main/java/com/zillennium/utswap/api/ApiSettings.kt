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


    /** historical */
    const val PATH_GET_MARKET_NAME = "finance/market_name"
    const val PATH_USER_TRANSACTION = "finance/historical_transaction"
    const val PATH_TRADE_HISTORY = "finance/tradeHistory"
    const val PATH_ALL_TRANSACTION = "finance/alltransactions"
    const val PATH_EXPORT_HISTORICAL_DATA = "finance/exportHistoryData"


    /** Register */
    const val PATH_REGISTER = "login/register"

    /** Forgot Password*/
    const val PATH_FORGOT_PASSWORD = "login/forgetPassword"
    const val PATH_FORGOT_PASSWORD_VERIFY = "login/verifyForgetPassword"
    const val PATH_ENTER_NEW_PASSWORD = "login/changeNewPassword"


}
