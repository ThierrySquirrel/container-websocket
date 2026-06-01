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
package io.github.thierrysquirrel.websocket.netty.server.handler.core.factory;

import io.github.thierrysquirrel.websocket.core.domain.HttpUpgradeRequest;
import io.github.thierrysquirrel.websocket.core.template.WebsocketChannelTemplate;
import io.github.thierrysquirrel.websocket.core.template.WebsocketRouteTemplate;
import io.github.thierrysquirrel.websocket.netty.server.handler.HttpServerHandler;
import io.github.thierrysquirrel.websocket.netty.server.handler.WebsocketServerHandler;
import io.github.thierrysquirrel.websocket.netty.server.handler.core.factory.constant.IdleStateConstant;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * ClassName: ChannelPipelineFactory
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class ChannelPipelineFactory {

    private ChannelPipelineFactory() {
    }

    public static void upgradeWebsocketPipeline(Channel channel, int readTimeoutMilli, HttpUpgradeRequest request, WebsocketRouteTemplate websocketRouteTemplate) {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.remove(IdleStateHandler.class);
        pipeline.remove(HttpServerHandler.class);
        pipeline.addFirst(new IdleStateHandler(readTimeoutMilli,
                IdleStateConstant.WEBSOCKET_WRITER_IDLE_TIME,
                IdleStateConstant.WEBSOCKET_ALL_IDLE_TIME));
        WebsocketChannelTemplate channelTemplate = new WebsocketChannelTemplate(channel);
        pipeline.addLast(new WebsocketServerHandler(websocketRouteTemplate, channelTemplate));
        websocketRouteTemplate.upgradeSuccessEvent(request, channelTemplate);
    }
}
