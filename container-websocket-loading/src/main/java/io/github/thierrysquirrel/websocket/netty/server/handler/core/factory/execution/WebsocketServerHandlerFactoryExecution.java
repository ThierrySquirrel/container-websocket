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
package io.github.thierrysquirrel.websocket.netty.server.handler.core.factory.execution;

import io.github.thierrysquirrel.websocket.core.template.WebsocketChannelTemplate;
import io.github.thierrysquirrel.websocket.core.template.WebsocketRouteTemplate;
import io.github.thierrysquirrel.websocket.netty.core.factory.constant.ThreadPoolFactoryConstant;
import io.github.thierrysquirrel.websocket.netty.server.handler.core.factory.WebsocketServerHandlerFactory;
import io.github.thierrysquirrel.websocket.netty.server.thread.execution.WebsocketServerBusinessThreadExecution;
import io.github.thierrysquirrel.websocket.netty.server.thread.execution.WebsocketServerCloseThreadExecution;
import io.github.thierrysquirrel.websocket.netty.server.thread.execution.WebsocketServerTimeoutThreadExecution;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.timeout.IdleState;


/**
 * ClassName: WebsocketServerHandlerFactoryExecution
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class WebsocketServerHandlerFactoryExecution {
    private WebsocketServerHandlerFactoryExecution() {
    }

    public static void business(WebsocketRouteTemplate websocketRouteTemplate, WebsocketChannelTemplate websocketChannelTemplate, WebSocketFrame webSocketFrame) {
        WebsocketServerBusinessThreadExecution businessThreadExecution = new WebsocketServerBusinessThreadExecution(websocketRouteTemplate, websocketChannelTemplate, webSocketFrame.retain());
        ThreadPoolFactoryConstant.WEBSOCKET_SERVER_BUSINESS_THREAD_POOL.execute(businessThreadExecution);
    }

    public static void timeoutEvent(IdleState state, WebsocketRouteTemplate websocketRouteTemplate, WebsocketChannelTemplate websocketChannelTemplate) {
        if (WebsocketServerHandlerFactory.timeout(state)) {
            timeoutEvent(websocketRouteTemplate, websocketChannelTemplate);
        }
    }

    public static void closeEvent(WebsocketRouteTemplate websocketRouteTemplate, WebsocketChannelTemplate websocketChannelTemplate) {
        WebsocketServerCloseThreadExecution closeThreadExecution = new WebsocketServerCloseThreadExecution(websocketRouteTemplate, websocketChannelTemplate);
        ThreadPoolFactoryConstant.WEBSOCKET_SERVER_BUSINESS_THREAD_POOL.execute(closeThreadExecution);
    }

    private static void timeoutEvent(WebsocketRouteTemplate websocketRouteTemplate, WebsocketChannelTemplate websocketChannelTemplate) {
        WebsocketServerTimeoutThreadExecution timeoutThreadExecution = new WebsocketServerTimeoutThreadExecution(websocketRouteTemplate, websocketChannelTemplate);
        ThreadPoolFactoryConstant.WEBSOCKET_SERVER_BUSINESS_THREAD_POOL.execute(timeoutThreadExecution);
    }
}
