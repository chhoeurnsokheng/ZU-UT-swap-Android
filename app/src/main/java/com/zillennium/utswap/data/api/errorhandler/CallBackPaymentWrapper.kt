package com.gis.z1android.api.errorhandler

import android.util.Log
import com.zillennium.utswap.UTSwapApp
import com.zillennium.utswap.data.api.manager.ApiManager
import okhttp3.ResponseBody
import org.json.JSONObject
import java.io.IOException

abstract class CallBackPaymentWrapper(
    e: Throwable
) {
    init {
        when (e) {
            is NoConnectivityException -> onCallBackPaymentWrapper(
                ApiManager.NetworkErrorStatus.ON_NETWORK_ERROR,
                CallbackWrapper.NETWORK_ERROR_MESSAGE
            )
            is IOException -> onCallBackPaymentWrapper(
                ApiManager.NetworkErrorStatus.ON_TIMEOUT,
                CallbackWrapper.SERVER_ERROR_MESSAGE
            )
            is retrofit2.adapter.rxjava.HttpException -> {
                Log.d("Logger", "" + e.code())
                val responseBody = e.response()?.errorBody()
                when (e.code()) {
                    CallbackWrapper.ErrorCode.Unauthorized -> {
                        UTSwapApp.instance.apply {
                           // MockUpData.removeUserData(this)
                           // MockUpData.removeAccessToken(this)
                        }
                        onCallBackPaymentWrapper(
                            ApiManager.NetworkErrorStatus.UNAUTHORIZED,
                            getErrorMessage(responseBody!!)
                        )
                    }
                    else -> {
                        onCallBackPaymentWrapper(
                            ApiManager.NetworkErrorStatus.ON_ERROR,
                            getErrorMessage(responseBody!!)
                        )
                    }
                }
            }
        }
    }

    /**
     * This function for generate error message from server
     * (code: 400,404)
     *
     * @param responseBody
     * @return
     */
    private fun getErrorMessage(responseBody: ResponseBody): JSONObject {
        return try {
            return JSONObject(responseBody.string())
        } catch (e: Exception) {
            val data  = JSONObject()
            data.put("message", e.message.toString())
        }
    }

    protected abstract fun onCallBackPaymentWrapper(
        status: ApiManager.NetworkErrorStatus,
        data: Any
    )
}