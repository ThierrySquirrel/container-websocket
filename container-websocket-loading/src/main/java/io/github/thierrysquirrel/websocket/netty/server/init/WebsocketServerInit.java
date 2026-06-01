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
package io.github.thierrysquirrel.websocket.netty.server.init;

import io.github.thierrysquirrel.websocket.netty.core.factory.SocketAddressFactory;
import io.github.thierrysquirrel.websocket.netty.server.handler.HttpServerInitChannelHandler;
import io.github.thierrysquirrel.websocket.netty.server.handler.core.factory.constant.IdleStateConstant;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.MultiThreadIoEventLoopGroup;
import io.netty.channel.nio.NioIoHandler;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * ClassName: WebsocketServerInit
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class WebsocketServerInit {
    private String url;
    private int websocketMaxFramePayloadLength;
    private int websocketReadTimeoutMilli;

    public WebsocketServerInit(String url, int websocketMaxFramePayloadLength, int websocketReadTimeoutMilli) {
        this.url = url;
        this.websocketMaxFramePayloadLength = websocketMaxFramePayloadLength;
        this.websocketReadTimeoutMilli = websocketReadTimeoutMilli;
    }

    public void init() throws InterruptedException {
        new ServerBootstrap().group(new MultiThreadIoEventLoopGroup(NioIoHandler.newFactory()), new MultiThreadIoEventLoopGroup(NioIoHandler.newFactory()))
                .channel(NioServerSocketChannel.class)
                .childHandler(new HttpServerInitChannelHandler(IdleStateConstant.HTTP_READER_IDLE_TIME,
                        IdleStateConstant.HTTP_WRITER_IDLE_TIME,
                        IdleStateConstant.HTTP_ALL_IDLE_TIME,
                        IdleStateConstant.HTTP_MAX_CONTENT_LENGTH,
                        websocketMaxFramePayloadLength,
                        websocketReadTimeoutMilli))
                .bind(SocketAddressFactory.getSocketAddress(url))
                .sync()
                .channel()
                .closeFuture()
                .sync();

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWebsocketMaxFramePayloadLength() {
        return websocketMaxFramePayloadLength;
    }

    public void setWebsocketMaxFramePayloadLength(int websocketMaxFramePayloadLength) {
        this.websocketMaxFramePayloadLength = websocketMaxFramePayloadLength;
    }

    public int getWebsocketReadTimeoutMilli() {
        return websocketReadTimeoutMilli;
    }

    public void setWebsocketReadTimeoutMilli(int websocketReadTimeoutMilli) {
        this.websocketReadTimeoutMilli = websocketReadTimeoutMilli;
    }

    @Override
    public String toString() {
        return "WebsocketServerInit{" +
                "url='" + url + '\'' +
                ", websocketMaxFramePayloadLength=" + websocketMaxFramePayloadLength +
                ", websocketReadTimeoutMilli=" + websocketReadTimeoutMilli +
                '}';
    }
}
