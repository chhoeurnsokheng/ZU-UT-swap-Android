package com.zillennium.utswap.module.finance.depositScreen.OpenWebViewToComfirmPayment

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.webkit.*
import androidx.annotation.RequiresApi
import com.zillennium.utswap.module.finance.depositScreen.OpenWebViewToComfirmPayment.WebClientObject.HttpStatusCode.BAD_GATEWAY
import com.zillennium.utswap.module.finance.depositScreen.OpenWebViewToComfirmPayment.WebClientObject.HttpStatusCode.SERVER_DOWN
import com.zillennium.utswap.module.finance.depositScreen.OpenWebViewToComfirmPayment.WebClientObject.HttpStatusCode.SERVICE_UNAVAILABLE
import com.zillennium.utswap.utils.InternetUtils

/**
 * Created by Sokheng Chhoeurn on 20/8/22.
 * Build in Mac
 */

object WebClientObject {
    object HttpStatusCode {
        var UNAUTHORIZED = 401
        var BAD_GATEWAY = 502
        var SERVICE_UNAVAILABLE = 503
        var SERVER_DOWN = 521
        var NOT_FOUND = 404

        enum class STATUS {
            UNAUTHORIZED
        }
    }

    class WebClient() : WebViewClient() {
        var listener: ((loadingStatus: Boolean, view: WebView) -> Unit)? = null
        var listenerNoInternet: ((status: Boolean) -> Unit)? = null
        var listenHttpError: ((status: Int) -> Unit)? = null
        var listenOpenDeepLink: ((url: String) -> Unit)? = null

        private var isErrorLoading = false
        private var mailto = "mailto:"
        private var tel = "tel:"

        override fun onLoadResource(view: WebView?, url: String?) {
            super.onLoadResource(view, url)
        }
        override fun shouldOverrideUrlLoading(
            view: WebView,
            url: String
        ): Boolean {
            if (!InternetUtils.ConnectivityMonitor.isConnected) {
                listenerNoInternet?.invoke(true)
            } else if (url.startsWith(mailto)) {
                val urltype = url.indexOf("@")
                if (urltype > 0) {
                    if (url.isEmpty()) {
                        return true
                    }
                    val dataSend = url.split("?")
                    if (dataSend.size == 1) {
                        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:")
                            putExtra(Intent.EXTRA_EMAIL, arrayOf(dataSend[0]))
                            putExtra(Intent.EXTRA_SUBJECT, "");
                        }
                        view.context.startActivity(Intent.createChooser(emailIntent, "Send email"))
                    } else if (dataSend.size == 2) {
                        val subTitle = dataSend[1].split("=")
                        var subject = ""
                        var to = ""
                        if (subTitle.size >= 2) {
                            subject = Uri.decode(subTitle[1])
                        }
                        if (dataSend[0].split(":").size == 2) {
                            to = dataSend[0].split(":")[1]
                        }
                        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:")
                            putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
                            putExtra(Intent.EXTRA_SUBJECT, subject)
                        }
                        view.context.startActivity(Intent.createChooser(emailIntent, "Send email"))
                    }
                } else {
                    if (url.isNotEmpty()) {
                        val shareUrl = url.substring("mailto:?subject=share&body=".length, url.length)
                        val shareIntent = Intent(Intent.ACTION_SEND)
                        shareIntent.type = "text/plain"
                        shareIntent.putExtra(Intent.EXTRA_TEXT, Uri.decode(shareUrl))
                        view.context.startActivity(Intent.createChooser(shareIntent, "Share..."))
                    }
                }
                return true
            } else if (url.startsWith(tel)) {
                view.context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(url)))
                return true
            } else {
                //val url = "abamobilebank://ababank.com?"
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    listenOpenDeepLink?.invoke(url)
                    return true
                } else {
                    view.loadUrl(url)
                }
            }
            return true
        }

        override fun onReceivedHttpError(
            view: WebView?,
            request: WebResourceRequest?,
            errorResponse: WebResourceResponse
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (errorResponse.statusCode == BAD_GATEWAY
                    || errorResponse.statusCode == SERVICE_UNAVAILABLE
                    || errorResponse.statusCode == SERVER_DOWN
                ) {
                    isErrorLoading = true
                    listenHttpError?.invoke(errorResponse.statusCode)
                }
            }
            super.onReceivedHttpError(view, request, errorResponse)
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError
        ) {
            if (error.errorCode == ERROR_HOST_LOOKUP || error.errorCode == ERROR_BAD_URL
            ) {
                isErrorLoading = true
                listenHttpError?.invoke(error.errorCode)
            }
            super.onReceivedError(view, request, error)
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            if (isErrorLoading) {
                listener?.invoke(true, view)
                isErrorLoading = false
            } else {
                listener?.invoke(false, view)
            }
        }
    }

}