package com.zillennium.utswap.api.manager

import android.annotation.SuppressLint
import android.content.Context
import com.zillennium.utswap.BuildConfig
import com.zillennium.utswap.api.ApiSettings
import com.zillennium.utswap.bases.websocket.RxWS
import com.zillennium.utswap.bases.websocket.WSConfig
import com.zillennium.utswap.bases.websocket.WSInfo
import retrofit2.converter.moshi.MoshiConverterFactory
import rx.Observable
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.*

open class SocketManager {

    private val mServerUrl = getBaseServerUrl()



    protected lateinit var mContext: Context
    open lateinit var mTradeListSocket: Observable<WSInfo>
    open lateinit var mTradeListOrderBookTable: Observable<WSInfo>


    init {
        initServices()
    }

    open fun getBaseServerUrl(): String {
        return  BuildConfig.BASE_SOCKET_URL
    }

    //INIT ALL PROFIT OBJECT
    private fun initServices() {
        mTradeListSocket = initSocket(ApiSettings.PATH_LIST_TRADE)
        mTradeListOrderBookTable = initSocket(ApiSettings.PATH_LIST_TRADE)
    }

    private fun initSocket(endpoint: String = ""): Observable<WSInfo> {

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

        val config = WSConfig.Builder()
            .setShowLog(true) //show  log
//            .setClient(client.build()) //if you want to set your okhttpClient
//            .setShowLog(true, "your logTag")
//            .setReconnectInterval(120, TimeUnit.SECONDS) //set reconnect interval
            .setSSLSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager) // wss support
            .build()
        RxWS.setConfig(config)
        return RxWS.get(mServerUrl+endpoint)
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
