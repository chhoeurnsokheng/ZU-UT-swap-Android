package com.gis.z1android.api.errorhandler

import android.util.Log
import com.zillennium.utswap.data.api.manager.ApiManager
import okhttp3.ResponseBody
import org.json.JSONObject
import java.io.IOException

abstract class CallBackZ1BackendPaymentWrapper(e: Throwable) {
    init {
        when (e) {is NoConnectivityException -> onCallBackWrapper(
                ApiManager.NetworkErrorStatus.ON_NETWORK_ERROR,
                CallbackWrapper.NETWORK_ERROR_MESSAGE
            )
            is IOException -> onCallBackWrapper(
                ApiManager.NetworkErrorStatus.ON_TIMEOUT,
                CallbackWrapper.SERVER_ERROR_MESSAGE
            )
            is retrofit2.adapter.rxjava.HttpException -> {
                Log.d("Logger", "" + e.code())
                val responseBody = e.response()?.errorBody()
                when (e.code()) {
                    CallbackWrapper.ErrorCode.Unauthorized -> {
                        responseBody?.apply {
                            onCallBackWrapper(
                                ApiManager.NetworkErrorStatus.UNAUTHORIZED,
                                this
                            )
                        }
                    }
                    CallbackWrapper.ErrorCode.UnProcessableEntity -> {
                        responseBody?.apply {
                            val jsonObject = JSONObject(responseBody.string())
                            onCallBackWrapper(
                                ApiManager.NetworkErrorStatus.UnProcessableEntity,
                                jsonObject
                            )
                        }
                    }
                    else -> {
                        responseBody?.apply {
                            onCallBackWrapper(
                                ApiManager.NetworkErrorStatus.ON_ERROR,
                                responseBody
                            )
                        }
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
    private fun getErrorMessage(responseBody: ResponseBody): Any? {
        return try {
            val jsonObject = JSONObject(responseBody.string())
            jsonObject
        } catch (e: Exception) {
            null
        }
    }

    protected abstract fun onCallBackWrapper(
        status: ApiManager.NetworkErrorStatus,
        data: Any
    )
}