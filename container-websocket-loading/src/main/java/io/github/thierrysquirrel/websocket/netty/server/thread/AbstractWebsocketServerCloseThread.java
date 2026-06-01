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
package io.github.thierrysquirrel.websocket.netty.server.thread;

import io.github.thierrysquirrel.websocket.core.template.WebsocketChannelTemplate;
import io.github.thierrysquirrel.websocket.core.template.WebsocketRouteTemplate;

/**
 * ClassName: AbstractWebsocketServerCloseThread
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public abstract class AbstractWebsocketServerCloseThread implements Runnable {
    private WebsocketRouteTemplate websocketRouteTemplate;
    private WebsocketChannelTemplate websocketChannelTemplate;

    protected AbstractWebsocketServerCloseThread(WebsocketRouteTemplate websocketRouteTemplate, WebsocketChannelTemplate websocketChannelTemplate) {
        this.websocketRouteTemplate = websocketRouteTemplate;
        this.websocketChannelTemplate = websocketChannelTemplate;
    }

    /**
     * close
     *
     * @param websocketRouteTemplate   websocketRouteTemplate
     * @param websocketChannelTemplate websocketChannelTemplate
     */
    protected abstract void close(WebsocketRouteTemplate websocketRouteTemplate, WebsocketChannelTemplate websocketChannelTemplate);

    @Override
    public void run() {
        close(this.websocketRouteTemplate, this.websocketChannelTemplate);
    }

    public WebsocketRouteTemplate getWebsocketRouteTemplate() {
        return websocketRouteTemplate;
    }

    public void setWebsocketRouteTemplate(WebsocketRouteTemplate websocketRouteTemplate) {
        this.websocketRouteTemplate = websocketRouteTemplate;
    }

    public WebsocketChannelTemplate getWebsocketChannelTemplate() {
        return websocketChannelTemplate;
    }

    public void setWebsocketChannelTemplate(WebsocketChannelTemplate websocketChannelTemplate) {
        this.websocketChannelTemplate = websocketChannelTemplate;
    }

    @Override
    public String toString() {
        return "AbstractWebsocketServerCloseThread{" +
                "websocketRouteTemplate=" + websocketRouteTemplate +
                ", websocketChannelTemplate=" + websocketChannelTemplate +
                '}';
    }
}
