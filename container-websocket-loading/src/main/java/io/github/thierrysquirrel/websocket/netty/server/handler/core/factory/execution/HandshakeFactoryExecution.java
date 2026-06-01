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

import io.github.thierrysquirrel.websocket.core.exception.WebsocketException;
import io.github.thierrysquirrel.websocket.core.template.WebsocketRouteTemplate;
import io.github.thierrysquirrel.websocket.netty.server.handler.core.factory.HandshakeFactory;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * ClassName: HandshakeFactoryExecution
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class HandshakeFactoryExecution {
    private HandshakeFactoryExecution() {
    }

    public static void upgradeWebsocket(Channel channel, FullHttpRequest request, int maxFramePayloadLength, int readTimeoutMilli) throws WebsocketException {
        String requestUri = request.uri();
        WebsocketRouteTemplate routeTemplate = HandshakeFactory.pathValidation(requestUri);
        HandshakeFactory.handshake(request, maxFramePayloadLength, channel);
        HandshakeFactory.upgradePipeline(routeTemplate, readTimeoutMilli, channel, request);
    }
}
