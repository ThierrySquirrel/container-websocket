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
package io.github.thierrysquirrel.websocket.core.template;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;

/**
 * ClassName: WebsocketChannelTemplate
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class WebsocketChannelTemplate {
    private Channel channel;

    public WebsocketChannelTemplate(Channel channel) {
        this.channel = channel;
    }

    public void pingMessage() {
        channel.writeAndFlush(new PingWebSocketFrame());
    }

    public void pingMessage(String message) {
        channel.writeAndFlush(new PingWebSocketFrame(Unpooled.copiedBuffer(message, CharsetUtil.UTF_8)));
    }

    public void pingMessage(byte[] message) {
        channel.writeAndFlush(new PingWebSocketFrame(Unpooled.copiedBuffer(message)));
    }

    public void pongMessage() {
        channel.writeAndFlush(new PongWebSocketFrame());
    }

    public void pongMessage(String message) {
        channel.writeAndFlush(new PongWebSocketFrame(Unpooled.copiedBuffer(message, CharsetUtil.UTF_8)));
    }

    public void pongMessage(byte[] message) {
        channel.writeAndFlush(new PongWebSocketFrame(Unpooled.copiedBuffer(message)));
    }

    public void textMessage() {
        channel.writeAndFlush(new TextWebSocketFrame());
    }

    public void textMessage(String message) {
        channel.writeAndFlush(new TextWebSocketFrame(message));
    }

    public void textMessage(byte[] message) {
        channel.writeAndFlush(new TextWebSocketFrame(Unpooled.copiedBuffer(message)));
    }

    public void binaryMessage() {
        channel.writeAndFlush(new BinaryWebSocketFrame());
    }

    public void binaryMessage(byte[] message) {
        channel.writeAndFlush(new BinaryWebSocketFrame(Unpooled.copiedBuffer(message)));
    }

    public void closeMessage() {
        channel.writeAndFlush(new CloseWebSocketFrame(), channel.newPromise()).addListener(ChannelFutureListener.CLOSE);
    }

    public void closeChannel() {
        channel.close();
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "WebsocketChannelTemplate{" +
                "channel=" + channel +
                '}';
    }
}
