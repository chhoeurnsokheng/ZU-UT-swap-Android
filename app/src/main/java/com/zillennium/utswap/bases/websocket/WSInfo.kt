package com.zillennium.utswap.bases.websocket

import okhttp3.Response
import okhttp3.WebSocket
import okio.ByteString

class WSInfo {

    lateinit var webSocket: WebSocket
    var string: String? = null
    var byteString: ByteString? = null
    var throwable: Throwable? = null
    var isOnFailure: Boolean = false
    var isOnOpen : Boolean = false
    var isOnReconnect : Boolean = false

    private constructor() {}

    companion object {
        fun createReconnect(): WSInfo {
            val socketInfo = WSInfo()
            socketInfo.isOnReconnect = true
            return socketInfo
        }
    }

    internal constructor(webSocket: WebSocket, onOpen: Boolean) {
        this.webSocket = webSocket
        this.isOnOpen = onOpen
    }

    internal constructor(webSocket: WebSocket, mString: String) {
        this.webSocket = webSocket
        this.string = mString
    }

    internal constructor(webSocket: WebSocket, byteString: ByteString) {
        this.webSocket = webSocket
        this.byteString = byteString
    }

    internal constructor(webSocket: WebSocket, throwable: Throwable, onFailure: Boolean) {
        this.webSocket = webSocket
        this.isOnFailure = onFailure
        this.throwable = throwable
    }
}