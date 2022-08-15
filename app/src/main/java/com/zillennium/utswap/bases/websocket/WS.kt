package com.zillennium.utswap.bases.websocket

import okhttp3.WebSocket
import okio.ByteString
import rx.Subscriber

abstract class WS : Subscriber<WSInfo>() {

    private var hasOpened: Boolean? = null

    override fun onNext(webSocketInfo: WSInfo?) {
        if (webSocketInfo?.isOnOpen == true) {
            hasOpened = true
            onOpen(webSocketInfo.webSocket)
        } else if (webSocketInfo?.string != null) {
            onMessage(webSocketInfo.string)
        } else if (webSocketInfo?.byteString != null) {
            onMessage(webSocketInfo.byteString)
        } else if (webSocketInfo?.isOnReconnect == true) {
            onReconnect()
        }

        if(webSocketInfo?.isOnFailure == true){
            onFailure(webSocketInfo.throwable)
        }
    }

    /**
     * Callback when the WebSocket is opened
     *
     * @param webSocket
     */
    protected open fun onOpen(webSocket: WebSocket?) {}
    protected open fun onMessage(text: String?) {}
    protected open fun onMessage(byteString: ByteString?) {}
    protected open fun onFailure(throwable: Throwable?){}

    /**
     * Callback when the WebSocket is reconnecting
     */
    protected open fun onReconnect() {}
    protected open fun onClose() {}
    override fun onCompleted() {
        if (hasOpened == true) {
            onClose()
        }
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
    }
}