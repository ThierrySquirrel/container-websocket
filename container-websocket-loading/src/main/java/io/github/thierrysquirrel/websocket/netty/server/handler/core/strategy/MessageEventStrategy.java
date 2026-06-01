/**
 * Copyright 2026/6/2 ThierrySquirrel
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/
package io.github.thierrysquirrel.websocket.netty.server.handler.core.strategy;

import io.github.thierrysquirrel.websocket.core.template.WebsocketChannelTemplate;
import io.github.thierrysquirrel.websocket.core.template.WebsocketMessageTemplate;
import io.github.thierrysquirrel.websocket.core.template.WebsocketRouteTemplate;
import io.netty.handler.codec.http.websocketx.*;

/**
 * ClassName: MessageEventStrategy
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class MessageEventStrategy {
    private MessageEventStrategy() {
    }

    public static void messageEvent(WebsocketRouteTemplate websocketRouteTemplate, WebsocketChannelTemplate websocketChannelTemplate, WebSocketFrame frame) {
        WebsocketMessageTemplate websocketMessageTemplate = new WebsocketMessageTemplate(frame);
        if (frame instanceof PingWebSocketFrame) {
            websocketRouteTemplate.pingMessageEvent(websocketChannelTemplate, websocketMessageTemplate);
        }
        if (frame instanceof PongWebSocketFrame) {
            websocketRouteTemplate.pongMessageEvent(websocketChannelTemplate, websocketMessageTemplate);
        }
        if (frame instanceof TextWebSocketFrame) {
            websocketRouteTemplate.textMessageEvent(websocketChannelTemplate, websocketMessageTemplate);
        }
        if (frame instanceof BinaryWebSocketFrame) {
            websocketRouteTemplate.binaryMessageEvent(websocketChannelTemplate, websocketMessageTemplate);
        }
        if (frame instanceof CloseWebSocketFrame) {
            websocketRouteTemplate.closeMessageEvent(websocketChannelTemplate, websocketMessageTemplate);
        }
        frame.release();

    }
}
