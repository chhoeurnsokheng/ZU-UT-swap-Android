package com.zillennium.utswap.bases.websocket;

import okhttp3.WebSocket;
import okio.ByteString;
import rx.functions.Action1;

/**
 * please use {@link WS}
 */
public abstract class WSAction implements Action1<WSInfo> {

    @Override
    public void call(WSInfo webSocketInfo) {
        if (webSocketInfo.isOnOpen()) {

            onOpen(webSocketInfo.getWebSocket());
        } else {
            webSocketInfo.getString();
            onMessage(webSocketInfo.getString());
        }
    }

    public abstract void onOpen(WebSocket webSocket);

    public abstract void onMessage(String text);

    public abstract void onMessage(ByteString bytes);
}
