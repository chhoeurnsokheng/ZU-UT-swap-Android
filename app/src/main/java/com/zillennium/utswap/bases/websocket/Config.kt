package com.zillennium.utswap.bases.websocket

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

class Config private constructor() {
    var reconnectInterval: Long = 1
    var reconnectIntervalTimeUnit = TimeUnit.SECONDS
    var showLog = false
    var logTag = "RxWebSocket"
    var client = OkHttpClient()
    var sslSocketFactory: SSLSocketFactory? = null
    var trustManager: X509TrustManager? = null

    class Builder {
        private val config: Config

        /**
         * set your client
         *
         * @param client
         */
        fun setClient(client: OkHttpClient): Builder {
            config.client = client
            return this
        }

        /**
         * wss support
         *
         * @param sslSocketFactory
         * @param trustManager
         */
        fun setSSLSocketFactory(
            sslSocketFactory: SSLSocketFactory?,
            trustManager: X509TrustManager?
        ): Builder {
            config.sslSocketFactory = sslSocketFactory
            config.trustManager = trustManager
            return this
        }

        /**
         * set reconnect interval
         *
         * @param Interval reconncet interval
         * @param timeUnit unit
         * @return
         */
        fun setReconnectInterval(Interval: Long, timeUnit: TimeUnit): Builder {
            config.reconnectInterval = Interval
            config.reconnectIntervalTimeUnit = timeUnit
            return this
        }

        fun setShowLog(showLog: Boolean): Builder {
            config.showLog = showLog
            return this
        }

        fun setShowLog(showLog: Boolean, logTag: String): Builder {
            config.showLog = showLog
            config.logTag = logTag
            return this
        }

        fun build(): Config {
            return config
        }

        init {
            config = Config()
        }
    }
}