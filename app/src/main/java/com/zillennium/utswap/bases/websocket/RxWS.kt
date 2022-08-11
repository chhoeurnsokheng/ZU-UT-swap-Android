package com.zillennium.utswap.bases.websocket

import okio.ByteString
import rx.Observable
import java.util.concurrent.TimeUnit

object RxWS {
    fun setConfig(config: WSConfig) {
        val instance = RxWSUtil().getInstance()
        instance?.setShowLog(config.showLog, config.logTag)
        instance?.setClient(config.client)
        instance?.setReconnectInterval(config.reconnectInterval, config.reconnectIntervalTimeUnit)
        if (config.sslSocketFactory != null && config.trustManager != null) {
            instance?.setSSLSocketFactory(config.sslSocketFactory, config.trustManager)
        }
    }

    /**
     * default timeout: 30 days
     */
    operator fun get(url: String): Observable<WSInfo> {
        return RxWSUtil.instance?.getWebSocketInfo(url)!!
    }

    /**
     * @param url      ws://127.0.0.1:8080/websocket
     * @param timeout  The WebSocket will be reconnected after the specified time interval is not "onMessage",
     *
     * @param timeUnit unit
     * @return
     */
    operator fun get(url: String, timeout: Long, timeUnit: TimeUnit): Observable<WSInfo> {
        return RxWSUtil.instance?.getWebSocketInfo(url, timeout, timeUnit)!!
    }

    /**
     * @param url
     * @param msg
     */
    fun send(url: String, msg: String) {
        RxWSUtil.instance?.send(url, msg)
    }

    /**
     * @param url
     * @param byteString
     */
    fun send(url: String, byteString: ByteString) {
        RxWSUtil.instance?.send(url, byteString)
    }

    /**
     * @param url
     * @param msg
     */
    fun asyncSend(url: String, msg: String) {
        RxWSUtil.instance?.asyncSend(url, msg)
    }

    /**
     * @param url
     * @param byteString
     */
    fun asyncSend(url: String, byteString: ByteString) {
        RxWSUtil.instance?.asyncSend(url, byteString)
    }
}