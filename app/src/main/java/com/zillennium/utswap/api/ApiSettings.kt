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

    /**     Account Logs       **/
    const val PATH_ACCOUNT_LOGS = "user/accountLogs"

    /** Register **/
    const val PATH_REGISTER = "login/register"

    /**     Customer Support     **/
    const val PATH_CUSTOMER_SUPPORT = "customer_support"

    /**      Project List        **/
    const val PATH_PROJECT_LIST = "issue?name=NR5&page=1"

}
