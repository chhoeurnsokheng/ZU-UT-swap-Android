package com.zillennium.utswap.bases.websocket

import android.os.SystemClock
import android.util.ArrayMap
import android.util.Log
import okhttp3.*
import okio.ByteString
import rx.Observable
import rx.Subscriber
import rx.android.MainThreadSubscription
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

class RxWSUtil {
    private var client: OkHttpClient
    private val observableMap: MutableMap<String, Observable<WSInfo>?>
    private val webSocketMap: MutableMap<String, WebSocket>
    private var showLog = false
    private var logTag = "RxWebSocket"
    private var interval: Long = 1
    private var reconnectIntervalTimeUnit = TimeUnit.SECONDS

    /**
     * set your client
     *
     * @param client
     */
    fun setClient(client: OkHttpClient?) {
        if (client == null) {
            throw NullPointerException(" Are you stupid ? client == null")
        }
        this.client = client
    }

    /**
     * wss support
     *
     * @param sslSocketFactory
     * @param trustManager
     */
    fun setSSLSocketFactory(sslSocketFactory: SSLSocketFactory?, trustManager: X509TrustManager?) {
        client = client.newBuilder().sslSocketFactory(sslSocketFactory!!, trustManager!!).build()
    }

    fun setShowLog(showLog: Boolean) {
        this.showLog = showLog
    }

    fun setShowLog(showLog: Boolean, logTag: String) {
        setShowLog(showLog)
        this.logTag = logTag
    }

    fun setReconnectInterval(interval: Long, timeUnit: TimeUnit) {
        this.interval = interval
        reconnectIntervalTimeUnit = timeUnit
    }

    /**
     * @param url      ws://127.0.0.1:8080/websocket
     * @param timeout  The WebSocket will be reconnected after the specified time interval is not "onMessage",
     *
     */
    fun getWebSocketInfo(
        url: String,
        timeout: Long,
        timeUnit: TimeUnit?
    ): Observable<WSInfo>? {
        var observable = observableMap[url]
        if (observable == null) {
            observable = Observable.create<WSInfo>(WebSocketOnSubscribe(url))
                .timeout(timeout, timeUnit)
                .retry() //共享
                .doOnUnsubscribe {
                    observableMap.remove(url)
                    webSocketMap.remove(url)
                    if (showLog) {
                        Log.d(logTag, "unsubscribe")
                    }
                }
                .doOnNext { webSocketInfo ->
                    if (webSocketInfo.isOnOpen) {
                        webSocketMap[url] = webSocketInfo.webSocket
                    }
                }
                .share()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
            observableMap[url] = observable
        } else {
            val webSocket = webSocketMap[url]
            if (webSocket != null) {
                observable = observable.startWith(WSInfo(webSocket, true))
            }
        }
        return observable
    }

    /**
     * default timeout: 30 days
     */
    fun getWebSocketInfo(url: String): Observable<WSInfo>? {
        return getWebSocketInfo(url, 30, TimeUnit.DAYS)
    }

    fun getWebSocketString(url: String): Observable<String?>? {
        return getWebSocketInfo(url)
            ?.map(Func1 { webSocketInfo -> webSocketInfo.string })
            ?.filter { s -> s != null }
    }

    fun getWebSocketByteString(url: String): Observable<ByteString?>? {
        return getWebSocketInfo(url)
            ?.map(Func1 { webSocketInfo -> webSocketInfo.byteString })
            ?.filter { byteString -> byteString != null }
    }

    fun getWebSocket(url: String): Observable<WebSocket>? {
        return getWebSocketInfo(url)
            ?.map(Func1 { webSocketInfo -> webSocketInfo.webSocket })
    }

    /**
     * @param url
     * @param msg
     */
    fun send(url: String, msg: String?) {
        val webSocket = webSocketMap[url]
        webSocket?.send(msg!!) ?: throw IllegalStateException("The WebSokcet not open")
    }

    /**
     * @param url
     * @param byteString
     */
    fun send(url: String, byteString: ByteString?) {
        val webSocket = webSocketMap[url]
        webSocket?.send(byteString!!) ?: throw IllegalStateException("The WebSokcet not open")
    }

    /**
     * @param url
     * @param msg
     */
    fun asyncSend(url: String, msg: String?) {
        getWebSocket(url)
            ?.first()
            ?.subscribe { webSocket -> webSocket.send(msg!!) }
    }

    /**
     * @param url
     * @param byteString
     */
    fun asyncSend(url: String, byteString: ByteString?) {
        getWebSocket(url)
            ?.first()
            ?.subscribe { webSocket -> webSocket.send(byteString!!) }
    }

    private fun getRequest(url: String): Request {
        return Request.Builder().get().url(url).build()
    }

    private inner class WebSocketOnSubscribe(private val url: String) :
        Observable.OnSubscribe<WSInfo?> {
        private var webSocket: WebSocket? = null
        override fun call(subscriber: Subscriber<in WSInfo?>) {
            if (webSocket != null) {
                if ("main" != Thread.currentThread().name) {
                    var ms = reconnectIntervalTimeUnit.toMillis(interval)
                    if (ms == 0L) {
                        ms = 1000
                    }
                    SystemClock.sleep(ms)
                    subscriber.onNext(WSInfo.createReconnect())
                }
            }
            initWebSocket(subscriber)
        }

        private fun initWebSocket(subscriber: Subscriber<in WSInfo?>) {
            webSocket = client.newWebSocket(getRequest(url), object : WebSocketListener() {
                override fun onOpen(webSocket: WebSocket, response: Response) {
                    if (showLog) {
                        Log.d(logTag, "$url --> onOpen")
                    }
                    webSocketMap[url] = webSocket
                    if (!subscriber.isUnsubscribed) {
                        subscriber.onNext(WSInfo(webSocket, true))
                    }
                }

                override fun onMessage(webSocket: WebSocket, text: String) {
                    if (!subscriber.isUnsubscribed) {
                        subscriber.onNext(WSInfo(webSocket, text))
                    }
                }

                override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                    if (!subscriber.isUnsubscribed) {
                        subscriber.onNext(WSInfo(webSocket, bytes))
                    }
                }

                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                    if (showLog) {
                        Log.e(logTag, t.toString() + webSocket.request().url.toUri().path)
                    }
                    if (!subscriber.isUnsubscribed) {
                        subscriber.onError(t)
                    }
                }

                override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                    webSocket.close(1000, null)
                }

                override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                    if (showLog) {
                        Log.d(
                            logTag,
                            "$url --> onClosed:code = $code, reason = $reason"
                        )
                    }
                }
            })
            subscriber.add(object : MainThreadSubscription() {
                override fun onUnsubscribe() {
                    webSocket!!.close(3000, "close WebSocket")
                    if (showLog) {
                        Log.d(logTag, "$url --> onUnsubscribe ")
                    }
                }
            })
        }
    }

    companion object {
        /**
         * please use [RxWS] to instead of it
         *
         * @return
         */
        var instance : RxWSUtil? = null


    }

    fun getInstance(): RxWSUtil? {
        if (instance == null) {
            synchronized(RxWSUtil::class.java) {
                if (instance == null) {
                    instance = RxWSUtil()
                }
            }
        }
        return instance
    }

    init {
        try {
            Class.forName("okhttp3.OkHttpClient")
        } catch (e: ClassNotFoundException) {
            throw RuntimeException("Must be dependency okhttp3 !")
        }
        try {
            Class.forName("rx.Observable")
        } catch (e: ClassNotFoundException) {
            throw RuntimeException("Must be dependency rxjava 1.x")
        }
        try {
            Class.forName("rx.android.schedulers.AndroidSchedulers")
        } catch (e: ClassNotFoundException) {
            throw RuntimeException("Must be dependency rxandroid 1.x")
        }
        observableMap = ArrayMap()
        webSocketMap = ArrayMap()
        client = OkHttpClient()
    }
}