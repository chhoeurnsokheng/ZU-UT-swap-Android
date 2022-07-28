package com.gis.z1android.api.errorhandler

import android.content.Context
import android.util.Log
import android.view.View
import com.zillennium.utswap.api.manager.ApiManager
import com.zillennium.utswap.utils.AlertUtil
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

abstract class CallbackWrapper(e: Throwable, context: Context, var codesToPass: ArrayList<Int>) {

    init {
        when (e) {
            is NoConnectivityException -> onCallbackWrapper(
                ApiManager.NetworkErrorStatus.ON_NETWORK_ERROR,
                NETWORK_ERROR_MESSAGE
            )
            is IOException -> onCallbackWrapper(
                ApiManager.NetworkErrorStatus.ON_TIMEOUT,
                SERVER_ERROR_MESSAGE
            )
            is retrofit2.adapter.rxjava.HttpException -> {
                Log.d("Logger", "" + e.code())
                val responseBody = e.message()
                when (e.code()) {
                    ErrorCode.BadRequest -> {
                        if (!codesToPass.contains(e.code())) {
                            AlertUtil().alertRequestError(
                                context,
                                "400",
                                e.code().toString(),
                                "BAD REQUEST",
                                null,
                                false
                            )
                        }
                        onCallbackWrapper(ApiManager.NetworkErrorStatus.ON_ERROR, responseBody)
                    }
                    ErrorCode.Unauthorized -> {
                        AlertUtil().alertRequestError(
                            context,
                            "401",
                            e.code().toString(),
                            "UNAUTHORIZED",
                            object : View.OnClickListener {
                                override fun onClick(v: View?) {
                                    onCallbackWrapper(
                                        ApiManager.NetworkErrorStatus.UNAUTHORIZED,
                                        responseBody
                                    )
                                    //  popupSessionExpired(context, "")
                                }
                            },
                            false
                        )
                        onCallbackWrapper(ApiManager.NetworkErrorStatus.UNAUTHORIZED, "")
                        ///   popupSessionExpired(context, "")
                    }
                    ErrorCode.Forbidden -> {
                        if (!codesToPass.contains(e.code())) {
                            AlertUtil().alertRequestError(
                                context,
                                "403",
                                e.code().toString(),
                                "FORBIDDEN",
                                null,
                                false
                            )
                        }
                        onCallbackWrapper(ApiManager.NetworkErrorStatus.ON_ERROR, responseBody)
                    }
                    ErrorCode.NotFound -> {
                        if (!codesToPass.contains(e.code())) {
                            AlertUtil().alertRequestError(
                                context,
                                "404",
                                e.code().toString(),
                                "NOT FOUND",
                                null,
                                false
                            )
                        }

                        val responseBody = e.response()?.errorBody()
                        responseBody?.apply {
                            onCallbackWrapper(
                                ApiManager.NetworkErrorStatus.NOT_FOUND,
                                getErrorMessage(this)
                            )
                        }

                        if (responseBody != null) {
                            onCallbackWrapper(ApiManager.NetworkErrorStatus.NOT_FOUND, responseBody)
                        }
                    }
                    ErrorCode.MethodNotAllowed -> {
                        if (!codesToPass.contains(e.code())) {
                            AlertUtil().alertRequestError(
                                context,
                                "405",
                                e.code().toString(),
                                "METHOD NOT ALLOWED",
                                null,
                                false
                            )
                        }
                        onCallbackWrapper(ApiManager.NetworkErrorStatus.ON_ERROR, responseBody)
                    }
                    ErrorCode.RequestEntityTooLarge -> {
                        if (!codesToPass.contains(e.code())) {
                            AlertUtil().alertRequestError(
                                context,
                                "413",
                                e.code().toString(),
                                "REQUEST ENTITY TOO LARGE",
                                null,
                                false
                            )
                        }
                        onCallbackWrapper(ApiManager.NetworkErrorStatus.ON_ERROR, responseBody)
                    }
                    ErrorCode.UnProcessableEntity -> {
                        if (!codesToPass.contains(e.code())) {
                            AlertUtil().alertRequestError(
                                context,
                                "422",
                                e.code().toString(),
                                "UNPROCESSABLE ENTITY",
                                null,
                                false
                            )
                        }
                        val responseBody = e.response()?.errorBody()
                        responseBody?.apply {
                            onCallbackWrapper(
                                ApiManager.NetworkErrorStatus.ON_ERROR,
                                getErrorMessage(this)
                            )
                        }
//                        onCallbackWrapper(ApiManager.NetworkErrorStatus.ON_ERROR, responseBody)
                    }
                    ErrorCode.InternalServerError -> {
                        if (!codesToPass.contains(e.code())) {
                            AlertUtil().alertRequestError(
                                context,
                                "500",
                                e.code().toString(),
                                "INTERNAL SERVER ERROR",
                                null,
                                false
                            )
                        }
                        onCallbackWrapper(ApiManager.NetworkErrorStatus.ON_ERROR, responseBody)
                    }
                    ErrorCode.BadGateway -> {
                        if (!codesToPass.contains(e.code())) {
                            AlertUtil().alertRequestError(
                                context,
                                "502",
                                e.code().toString(),
                                "BAD GATEWAY",
                                null,
                                false
                            )
                        }
                        onCallbackWrapper(ApiManager.NetworkErrorStatus.ON_ERROR, responseBody)
                    }
                    ErrorCode.GatewayTimeout -> {
                        if (!codesToPass.contains(e.code())) {
                            AlertUtil().alertRequestError(
                                context,
                                "504",
                                e.code().toString(),
                                "GATEWAY TIME OUT",
                                null,
                                false
                            )
                        }
                        onCallbackWrapper(ApiManager.NetworkErrorStatus.ON_ERROR, responseBody)
                    }
                    else -> {
                        //  AlertUtil().alertAppRequestError(context)
                        val responseBody = e.response()?.errorBody()
                        onCallbackWrapper(
                            ApiManager.NetworkErrorStatus.ON_ERROR,
                            getErrorMessage(responseBody!!)
                        )
                    }
                }
            }
            is HttpException -> {
                val responseBody = e.response()?.errorBody()
                onCallbackWrapper(
                    ApiManager.NetworkErrorStatus.ON_ERROR,
                    getErrorMessage(responseBody!!)
                )
            }
            else -> {
                val responseBody = e.message
                onCallbackWrapper(ApiManager.NetworkErrorStatus.ON_ERROR, responseBody!!)
            }
        }
    }

    protected abstract fun onCallbackWrapper(status: ApiManager.NetworkErrorStatus, data: Any)

    /**
     * This function for generate error message from server
     * (code: 400,404)
     *
     * @param responseBody
     * @return
     */
    private fun getErrorMessage(responseBody: ResponseBody): String {
        return try {
            val jsonObject = JSONObject(responseBody.string())
            return jsonObject.toString()
        } catch (e: Exception) {
            e.message.toString()
        }
    }

    companion object {
        const val DEFAULT_ERROR_MESSAGE = "Please try again."
        const val NETWORK_ERROR_MESSAGE = "No Internet Connection!"
        const val SERVER_ERROR_MESSAGE = "We sorry your connection timeout, please try again later!"
    }


    object ErrorCode {
        const val BadRequest = 400
        const val Unauthorized = 401
        const val Forbidden = 403
        const val NotFound = 404
        const val MethodNotAllowed = 405
        const val RequestEntityTooLarge = 413
        const val UnProcessableEntity = 422
        const val InternalServerError = 500
        const val BadGateway = 502
        const val GatewayTimeout = 504
    }

}

