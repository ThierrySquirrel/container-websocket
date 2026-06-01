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
package io.github.thierrysquirrel.websocket.netty.server.thread.execution;

import io.github.thierrysquirrel.websocket.netty.server.init.WebsocketServerInit;
import io.github.thierrysquirrel.websocket.netty.server.thread.AbstractWebsocketServerInitThread;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ClassName: WebsocketServerInitThreadExecution
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class WebsocketServerInitThreadExecution extends AbstractWebsocketServerInitThread {

    private static final Logger logger = Logger.getLogger(WebsocketServerInitThreadExecution.class.getName());

    public WebsocketServerInitThreadExecution(String url, int websocketMaxFramePayloadLength, int websocketReadTimeoutMilli) {
        super(url, websocketMaxFramePayloadLength, websocketReadTimeoutMilli);
    }


    @Override
    protected void init(String url, int websocketMaxFramePayloadLength, int websocketReadTimeoutMilli) {
        try {
            new WebsocketServerInit(url, websocketMaxFramePayloadLength, websocketReadTimeoutMilli).init();
        } catch (Throwable e) {
            String logMsg = "WebsocketServerInit Error";
            logger.log(Level.WARNING, logMsg, e);
        }

    }
}
