package com.zillennium.utswap.data.api

import android.content.Context
import com.google.gson.Gson
import com.zillennium.utswap.models.user.ShowProfile
import com.zillennium.utswap.utils.Util

/**
 * @author chhoeurnsokheng
 * Created 6/7/22 at 9:20 AM
 * By Mac
 */

object MockUpData {
    var IS_SIGN_UP = false
    var IS_SIGN_IN = false
    var IS_UPDATE_PIN = false
    var IS_FORGOT_PIN = false
    var IS_KYC_USER = false
    var IS_UPDATE_PHONE = false
    var IS_VERIFY_UPDATE_PHONE = false
    var RETRY_IN_SECOND: Int? = null
    var NUMBER_PHONE_SIGN_IN_SIGN_UP: String? = null
    var NUMBER_PHONE_FORGOT_PIN: String? = null
    var USER_ID: String? = null
    var FIRST_NAME_REGISTER: String? = null
    var COUNTRY_CODE_FORGOT_PIN: String? = null
    var PHONE_NUMBER_FORGOT_PIN: String? = null
    var LAST_NAME_REGISTER: String? = null
    var CREATE_PIN: String? = null
    var OLD_PIN: String? = null
    var ACCESS_TOKEN_FORGOT_PIN: String? = null
    var ACCESS_TOKEN_SIGN_UP: String? = null
    var INVITATION_CODE: String? = null
    var NUMBER_PHONE_NEW_CHANGE: String? = null
    var COUNTRY_CODE_NEW_CHANGE: String? = null
    const val REQUEST_CODE_SIGN_IN_NOTIFICATION = 111
    const val REQUEST_CODE_SIGN_IN_FAVORITE = 222
    const val REQUEST_CODE_SIGN_IN_PROFILE = 333
    const val REQUEST_CODE_SIGN_IN = 444
    const val REQUEST_CODE_SIGN_UP = 555
    const val REQUEST_CODE_UPDATE_PIN = 666
    const val REQUEST_CODE_FORGOT_PIN = 777
    const val REQUEST_USER_CONSENT_FOR_RECEIVE_SMS = 666
    const val REQUEST_CODE_SIGN_IN_POST_PROPERTY = 888
    const val REQUEST_CODE_SIGN_IN_FIND_PROPERTY = 999
    const val REQUEST_CODE_SIGN_IN_BOOKING_PROPERTY = 0x999
    const val REQUEST_CODE_SIGN_IN_IND_PLUS = 1000
    const val REQUEST_CODE_SIGN_IN_RECENT_VIEW = 1111
    const val REQUEST_CODE_UPDATE_PHONE = 2222
    const val REQUEST_CODE_UPDATE_NAME = 3333
    const val REQUEST_CODE_SIGN_IN_SAVED_SEARCH_LIST = 1999
    const val REQUEST_CODE_SIGN_IN_MORE = 2000
    const val REQUEST_CODE_SIGN_IN_SAVED_SEARCH_ITEM = 1998
    var IS_RESET_PIN_SUCCESS = false
    fun removeLocalVariableSignIn() {
        IS_SIGN_IN = false
        USER_ID = null
        RETRY_IN_SECOND = null
        NUMBER_PHONE_SIGN_IN_SIGN_UP = null
    }

    fun removeLocalVariableRememberPhone() {
        PHONE_NUMBER_FORGOT_PIN = null
        COUNTRY_CODE_FORGOT_PIN = null
    }

    fun removeLocalVariableSignUp(context: Context?, isSuccessSignUp: Boolean) {
        IS_SIGN_UP = false
        USER_ID = null
        NUMBER_PHONE_SIGN_IN_SIGN_UP = null
        RETRY_IN_SECOND = null
        FIRST_NAME_REGISTER = null
        LAST_NAME_REGISTER = null
        INVITATION_CODE = null
        CREATE_PIN = null
        if (isSuccessSignUp) {
            setAccessToken(ACCESS_TOKEN_SIGN_UP, context)
            IS_SIGN_UP = true
        }
        ACCESS_TOKEN_SIGN_UP = null
    }

    fun removeLocalVariableForgotPIN() {
        IS_FORGOT_PIN = false
        NUMBER_PHONE_FORGOT_PIN = null
        ACCESS_TOKEN_FORGOT_PIN = null
        RETRY_IN_SECOND = null
    }

    fun removeLocalVariableUpdatePIN() {
        IS_UPDATE_PIN = false
        OLD_PIN = null
        CREATE_PIN = null
    }

    fun setDeviceToken(deviceToken: String?, context: Context?) {
        if (context != null) {
            Util.saveData("deviceToken", deviceToken, context)
        }
    }

    fun getDeviceToken(context: Context?): String? {
        return context?.let { Util.getData("deviceToken", it) }
    }

    fun removeUserData(context: Context?) {
        if (context != null) {
            Util.saveData("userData", "", context)
        }

        //Theara, remove userID
        if (context != null) {
            Util.saveData("userID", "", context)
        }
    }

    fun setUserData(userData: ShowProfile?, context: Context?) {
        val gson = Gson()
        val userDataObject = gson.toJson(userData)
        if (context != null) {
            Util.saveData("userData", userDataObject, context)
        }
    }

    fun getUserData(context: Context?): ShowProfile {
        return try {
            val gson = Gson()
            val userDataObject: String? = context?.let { Util.getData("userData", it) }
            gson.fromJson(userDataObject, ShowProfile::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            ShowProfile()
        }
    }

    fun setUserID(userID: String?, context: Context?) {
        if (context != null) {
            Util.saveData("userID", userID, context)
        }
    }

    fun getUserID(context: Context?): String? {
        return context?.let { Util.getData("userID", it) }
    }

    fun removeAccessToken(context: Context?) {
        if (context != null) {
            Util.saveData("token", "", context)
        }
        removeRecentSavedSearchLocal()
      //  resetBadgeCounterOfPushMessages()
    }

    fun removeRecentSavedSearchLocal() {
//        BuySellSearchActivity().removeAllRecentSavedSearch(Z1App.instance)
//        GeneralSearchActivity().removeAllRecentSavedSearch(Z1App.instance)
    }

//    fun resetBadgeCounterOfPushMessages() {
//        val notificationManager: NotificationManager
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            notificationManager =
//                UTSwapApp.instance.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE)
//            if (notificationManager != null) {
//                notificationManager.cancelAll()
//            }
//        }
//    }

    fun setAccessToken(accessToken: String?, context: Context?) {
        if (context != null) {
            Util.saveData("token", accessToken, context)
        }
    }

    /**
     * Note : if(getAccessToken.equals("")) meaning it hasn't access token
     */
    fun getAccessToken(context: Context?): String? {
        return context?.let { Util.getData("token", it) }
    }
}

