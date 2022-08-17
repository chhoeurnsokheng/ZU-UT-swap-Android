package com.zillennium.utswap.bases.websocket

import okhttp3.WebSocket
import okio.ByteString
import rx.functions.Action1

/**
 * please use [WS]
 */
abstract class WSAction : Action1<WSInfo> {
    override fun call(webSocketInfo: WSInfo) {
        if (webSocketInfo.isOnOpen) {
            onOpen(webSocketInfo.webSocket)
        } else {
            webSocketInfo.string
            onMessage(webSocketInfo.string)
        }
    }

    abstract fun onOpen(webSocket: WebSocket?)
    abstract fun onMessage(text: String?)
    abstract fun onMessage(bytes: ByteString?)
}