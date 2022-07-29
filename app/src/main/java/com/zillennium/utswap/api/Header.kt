package com.zillennium.utswap.api

import android.content.Context
import android.util.Log
import com.zillennium.utswap.Datas.StoredPreferences.SessionPreferences
import com.zillennium.utswap.utils.Constants


/**
 * @author chhoeurnsokheng
 * Created 6/7/22 at 9:15 AM
 * By Mac
 */
class Header {
    companion object {
        enum class AuthType {
            REQUIRED,
            NOT_REQUIRED,
            REQUIRED_WITH_OPTION_AUTH,
            REQUIRED_WITH_OPTION_AUTH_MULTIPATH,
            REQUIRED_TOKEN
        }

        fun getHeader(authType: AuthType, context: Context): Map<String, String> {
            val map: Map<String, String>
            when (authType) {
                AuthType.REQUIRED -> {
                    map = mapOf(
                        Constants.Key.ContentType to Constants.Value.ContentType,
                        Constants.Key.Accept to Constants.Value.Accept
                    )
                    return map
                }
                AuthType.REQUIRED_WITH_OPTION_AUTH -> {
                    var accessToken = ""
                    accessToken = if (MockUpData.IS_FORGOT_PIN) {
                        "Bearer " + MockUpData.ACCESS_TOKEN_FORGOT_PIN
                    } else if (MockUpData.IS_SIGN_UP && null != MockUpData.ACCESS_TOKEN_SIGN_UP) {
                        "Bearer " + MockUpData.ACCESS_TOKEN_SIGN_UP
                    } else "Bearer "  + ""
                    MockUpData.getAccessToken(context)
                    map = mapOf(
                        Constants.Key.ContentType to Constants.Value.ContentType,
                        Constants.Key.Accept to Constants.Value.Accept,
                        Constants.Key.Authorization to accessToken
                    )
                    Log.i("header", "data: $map")
                    return map
                }
                AuthType.REQUIRED_WITH_OPTION_AUTH_MULTIPATH -> {
                    var accessToken = ""
                    accessToken = if (MockUpData.IS_FORGOT_PIN) {
                        "Bearer " + MockUpData.ACCESS_TOKEN_FORGOT_PIN
                    } else if (MockUpData.IS_SIGN_UP && null != MockUpData.ACCESS_TOKEN_SIGN_UP) {
                        "Bearer " + MockUpData.ACCESS_TOKEN_SIGN_UP
                    } else "Bearer " +  " "
                    MockUpData.getAccessToken(context)
                    map = mapOf(
                        Constants.Key.ContentType to Constants.Value.ContentTypeMultipart,
                        Constants.Key.Accept to Constants.Value.Accept,
                        Constants.Key.Authorization to accessToken
                    )
                    Log.i("header", "data: $map")
                    return map
                }
                AuthType.REQUIRED_TOKEN -> {
                    map = mapOf(
                        Constants.Key.ContentType to Constants.Value.ContentType,
                        Constants.Key.Accept to Constants.Value.Accept,
                        Constants.Key.ID to SessionPreferences().SESSION_ID.toString(),
                        Constants.Key.Token to SessionPreferences().SESSION_TOKEN.toString()
                    )
                    return map
                }

                else -> {

                }
            }
            return mapOf()
        }
    }
}
