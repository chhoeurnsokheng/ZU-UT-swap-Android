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


    const val PATH_NEWS_HOME = "Art/ArtList"
    const val PATH_NEWS_WITHOUT_TOKEN = "Art/ArtList"



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
    const val PATH_SUBSCRIPTION_PROJECT = "issue/available_subscription"
    const val PATH_SUBSCRIPTION_PROJECT_TERM_CONDITION_SUBMIT ="issue/checkStatusTerms"
    const val PATH_CHECK_PROJECT_STATUS ="issue/checkCondition"


    /**    Check Subscription Project Order     **/
    const val PATH_SUBSCRIPTION_PROJECT_ORDER = "issue/check_subscription_order"

    /**     Subscription Order       **/
    const val PATH_SUBSCRIPTION_ORDER = "issue/subscription_order"

    /** Finance */
    const val PATH_SUBSCRIPTION = "Issue/log"
    const val PATH_LOCK_UP = "finance/lockbalance"


    /** Forgot Password*/
    const val PATH_FORGOT_PASSWORD = "login/forgetPassword"
    const val PATH_FORGOT_PASSWORD_VERIFY = "login/verifyForgetPassword"
    const val PATH_ENTER_NEW_PASSWORD = "login/changeNewPassword"

    /** Change Login Password*/
    const val PATH_CHANGE_LOGIN_PASSWORD = "user/changeNewPassword"

    /** Change Fund Password*/
    const val PATH_CHECK_OLD_FUND_PASSWORD = "user/checkOldFund"
    const val PATH_CHANGE_FUND_PASSWORD = "user/changeFundPassword"

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
    const val PATH_LIST_TRADE = "wsocket/?path=listtrade"
    const val SEND_LIST_TRADE = "market_update_stream"
    const val  SEND_TRADE_MARKET_NAME = "trade_market_summary:"
    const val SEND_TRADE_MARKET_ORDER_BOOK_TABLE = "market:"

    const val PATH_TRADE_EXCHANGE = "wsocket/?path=listtradeexchange"
    const val PATH_TRADE_ORDER_BOOK = "wsocket/?path=listtradeorderbook"

    /** Upcomming project in trading list screen*/
    const val PATH_UPCOMING_PROJECT_TRADING_LIST = "trade/getUpcomingProject"

    /*Get Banner HomeScreen*/
    const val PATH_GET_BANNNER = "portfolio/app_banner"
    const val PATH_GET_HOME_WISHLIST_BALANCE = "portfolio/index"


    const val PATH_FORCE_UPDATE= "CheckAppVersion/index"


    /** Account Upload Profile*/
    const val PATH_ACCOUNT_UPLOAD_PROFILE = "user/uploadImageProfile"

    /**         Notification List            **/
    const val PATH_NOTIFICATION_LIST = "notification/loadNotification"
    const val PATH_NOTIFICATION_READ = "notification/mask_as_read_msg"
    const val PATH_NOTIFICATION_READ_ALL = "notification/showAllNoti"
    const val PATH_SAVE_FIREBASE_TOKEN = "notification/saveFirebaseToken"

    /** User Balance */
    const val PATH_FINANCE_USER_BALANCE = "finance/mytx"
    const val PATH_FINANCE_ACCOUNT_BALANCE_SEARCH_DATA = "finance/index"
    const val PATH_FINANCE_ACCOUNT_BALANCE_EXPORT_PDF = "finance/exportPdf"

    /** Check favorite project in trade*/
    const val PATH_CHECK_FAVORITE_PROJECT_TRADE = "trade/checkWatchList"

    /** Add Favorite Project*/
    const val PATH_ADD_FAVORITE_PROJECT = "trade/addFavorite"

    /** Trade Create Order*/
    const val PATH_TRADE_CREATE_ORDER = "trade/upTrade"

    /** Trade Chart*/
    const val PATH_TRADE_CHART = "trade/tradingView"

    /** Get Trade Order Pending*/
    const val PATH_GET_TRADE_ORDER_PENDING = "trade/getTradeOrder"

    /** Matching Trade Transaction*/
    const val PATH_TRADE_MATCHING_TRANSACTION = "trade/getTradeLog"

    /** Get Available Balance*/
    const val PATH_GET_AVAILABLE_BALANCE = "trade/getUserBalance"

    /** Trade Cancel Order*/
    const val PATH_TRADE_CANCEL_ORDER = "trade/cancelTradeOrder"

    /** Trade Transaction Detail*/
    const val PATH_TRANSACTION_DETAIL = "trade/matchingTransactionDetail"

    /** Trade Market Open */
    const val PATH_MARKET_OPEN = "trade/checkMarketOpen"


    /*Desposite Money*/

    const val PATH_LIST_PAYMENT_METHOD = "finance/listAvailableBank"
    const val PATH_ONLINE_DEPOSIT      ="finance/onlineMyczUp"
    const val PATH_FINANCE_TRANSFER_LOGS ="finance/outlog_transfer"

    const val PATH_QUERY_ORDER = "finance/DataQueryOrder"

    const val PATH_GET_DEPOSIT_FEE ="finance/getDepositFee"

    /** Finance Transfer */
    const val PATH_FINANCE_TRANSFER = "finance/direct_transfer"
    const val PATH_FINANCE_VALIDATE_TRANSFER = "finance/validate_transfer"


    const val PATH_LIST_AVAILABLE_WITHDRAWAL_BANK = "finance/available_withdrawal_bank"

    /** Portfolio */
    const val PATH_PORTFOLIO = "portfolio/index"
    const val PATH_DASHBOARD_CHART = "portfolio/dashboardchart"

}
