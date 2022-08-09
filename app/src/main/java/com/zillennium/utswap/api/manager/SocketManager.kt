package com.zillennium.utswap.api.manager

import android.annotation.SuppressLint
import android.content.Context
import com.zillennium.utswap.BuildConfig
import com.zillennium.utswap.Datas.APIs.APIInstance
import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.bases.websocket.Config
import com.zillennium.utswap.bases.websocket.RxWebSocket
import com.zillennium.utswap.bases.websocket.WebSocketInfo
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.converter.moshi.MoshiConverterFactory
import rx.Observable
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

open class SocketManager {

    private val mServerUrl = getBaseServerUrl()



    protected lateinit var mContext: Context
    open lateinit var mTradeListSocket: Observable<WebSocketInfo>


    init {
        initServices()
    }

    open fun getBaseServerUrl(): String {
        return  BuildConfig.BASE_SOCKET_URL
    }

    //INIT ALL PROFIT OBJECT
    private fun initServices() {
        mTradeListSocket = initSocket(ApiSettings.PATH_LIST_TRADE)
    }

    private fun initSocket(endpoint: String = ""): Observable<WebSocketInfo> {

        val trustAllCerts: Array<TrustManager> = arrayOf<TrustManager>(
            @SuppressLint("CustomX509TrustManager")
            object : X509TrustManager {
                @SuppressLint("TrustAllX509TrustManager")
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?
                ) {
                }

                @SuppressLint("TrustAllX509TrustManager")
                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            }
        )
        val sslContext: SSLContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory

        val config = Config.Builder()
            .setShowLog(true) //show  log
//            .setClient(client.build()) //if you want to set your okhttpClient
//            .setShowLog(true, "your logTag")
            .setReconnectInterval(50, TimeUnit.SECONDS) //set reconnect interval
            .setSSLSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager) // wss support
            .build()
        RxWebSocket.setConfig(config)

        return RxWebSocket.get(mServerUrl+endpoint)
    }

    /**
     * Implement Observable for retry to handle error 401 only.
     *
     * @param o Throwable when error by pass to Observable to resolve error.
     * @return Observable type object.
     */


    private fun createMoshiConverter(): MoshiConverterFactory = MoshiConverterFactory.create()

    enum class NetworkErrorStatus {
        UnProcessableEntity,
        ON_UNKNOW_ERROR,
        ON_ERROR,
        UNAUTHORIZED,
        ON_TIMEOUT,
        ON_NETWORK_ERROR,
        NOT_FOUND
    }
}
