package com.zillennium.utswap.api

import io.reactivex.internal.operators.parallel.ParallelDoOnNextTry

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


    const val PATH_NEWS_HOME = "Art/ArtList/p/1"


    /**     Account Logs       **/
    const val PATH_ACCOUNT_LOGS = "user/accountLogs"

    /** Register **/
    const val PATH_REGISTER = "login/register"

    /**     Customer Support     **/
    const val PATH_CUSTOMER_SUPPORT = "customer_support"

    /**      Project List        **/
    const val PATH_PROJECT_LIST = "issue"

    /**      Project Detail/Project Info          **/
    const val PATH_PROJECT_DETAIL = "issue/project_detail"

    /**        Subscription Project       **/


    /** Finance */
    const val PATH_SUBSCRIPTION = "Issue/log"
    const val PATH_LOCK_UP = "finance/lockbalance"


    /** Forgot Password*/
    const val PATH_FORGOT_PASSWORD = "login/forgetPassword"
    const val PATH_FORGOT_PASSWORD_VERIFY = "login/verifyForgetPassword"
    const val PATH_ENTER_NEW_PASSWORD = "login/changeNewPassword"

    /*User add KYC */
    const val PATH_KYC = "user/submitkyc"
    const val PATH_KYC_STATUS = "user/checkStatusKYC"

    const val PATH_GET_PROVINCES = "user/getCityProvince"


    /** Add Phone Number*/
    const val PATH_ADD_PHONE_NUMBER = "add_phone_number"
    const val PATH_VERIFY_CODE_PHONE = "add_phone_number/verify_code"

    /** App Side Bar*/
    const val PATH_APP_SIDE_BAR = "user/profile"

    /** Check User Login Status*/
    const val PATH_CHECK_USER_LOGIN_STATUS = "user/checkUserLogin"

    /** Website Socket */
    const val PATH_LIST_TRADE = "wsocket/"
    const val SEND_LIST_TRADE = "market_update_stream"
    const val SEND_TRADE_MARKET_NAME = "trade_market_summary:"

    /** Upcomming project in trading list screen*/
    const val PATH_UPCOMING_PROJECT_TRADING_LIST = "trade/getUpcomingProject"

     /*Get Banner HomeScreen*/
    const val PATH_GET_BANNNER= "portfolio/app_banner"
    const val PATH_GET_HOME_WISHLIST_BALANCE ="portfolio/index"

    /** Account Upload Profile*/
    const val PATH_ACCOUNT_UPLOAD_PROFILE = "user/uploadImageProfile"

    /**         Notification List            **/
    const val PATH_NOTIFICATION_LIST = "notification/loadNotification"
}
