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

import io.github.thierrysquirrel.websocket.core.domain.HttpUpgradeRequest;
import io.github.thierrysquirrel.websocket.core.exception.WebsocketException;

/**
 * ClassName: WebsocketRouteTemplate
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public interface WebsocketRouteTemplate {

    /**
     * upgradeSuccessEvent
     *
     * @param request         request
     * @param channelTemplate channelTemplate
     */
    void upgradeSuccessEvent(HttpUpgradeRequest request, WebsocketChannelTemplate channelTemplate);

    /**
     * pingMessageEvent
     *
     * @param channelTemplate channelTemplate
     * @param messageTemplate messageTemplate
     */
    void pingMessageEvent(WebsocketChannelTemplate channelTemplate, WebsocketMessageTemplate messageTemplate);

    /**
     * pongMessageEvent
     *
     * @param channelTemplate channelTemplate
     * @param messageTemplate messageTemplate
     */
    void pongMessageEvent(WebsocketChannelTemplate channelTemplate, WebsocketMessageTemplate messageTemplate);

    /**
     * textMessageEvent
     *
     * @param channelTemplate channelTemplate
     * @param messageTemplate messageTemplate
     */
    void textMessageEvent(WebsocketChannelTemplate channelTemplate, WebsocketMessageTemplate messageTemplate);

    /**
     * binaryMessageEvent
     *
     * @param channelTemplate channelTemplate
     * @param messageTemplate messageTemplate
     */
    void binaryMessageEvent(WebsocketChannelTemplate channelTemplate, WebsocketMessageTemplate messageTemplate);

    /**
     * closeMessageEvent
     *
     * @param channelTemplate channelTemplate
     * @param messageTemplate messageTemplate
     */
    void closeMessageEvent(WebsocketChannelTemplate channelTemplate, WebsocketMessageTemplate messageTemplate);

    /**
     * errorEvent
     *
     * @param channelTemplate    channelTemplate
     * @param websocketException websocketException
     */
    void errorEvent(WebsocketChannelTemplate channelTemplate, WebsocketException websocketException);

    /**
     * connectionTimeoutEvent
     *
     * @param channelTemplate channelTemplate
     */
    void connectionTimeoutEvent(WebsocketChannelTemplate channelTemplate);

    /**
     * closeEvent
     *
     * @param channelTemplate channelTemplate
     */
    void closeEvent(WebsocketChannelTemplate channelTemplate);
}
