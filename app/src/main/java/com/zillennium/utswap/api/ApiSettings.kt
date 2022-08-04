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

}
