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
package io.github.thierrysquirrel.websocket.core.container;

import io.github.thierrysquirrel.websocket.core.exception.WebsocketException;
import io.github.thierrysquirrel.websocket.core.factory.UriFactory;
import io.github.thierrysquirrel.websocket.core.template.WebsocketRouteTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName: WebsocketRouteContainer
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class WebsocketRouteContainer {
    private static final Map<String, WebsocketRouteTemplate> ROUTE_TEMPLATE = new ConcurrentHashMap<>();
    private static final Map<String, WebsocketRouteTemplate> VARIABLE_ROUTE_TEMPLATE = new ConcurrentHashMap<>();

    private WebsocketRouteContainer() {
    }

    public static void putWebsocketRouteTemplate(String route, WebsocketRouteTemplate websocketRouteTemplate) {
        boolean isVariable = UriFactory.containVariable(route);
        if (isVariable) {
            VARIABLE_ROUTE_TEMPLATE.put(route, websocketRouteTemplate);
        } else {
            ROUTE_TEMPLATE.put(route, websocketRouteTemplate);
        }
    }

    public static WebsocketRouteTemplate getWebsocketRouteTemplate(String requestUri) throws WebsocketException {
        WebsocketRouteTemplate websocketRouteTemplate = ROUTE_TEMPLATE.get(requestUri);
        if (null != websocketRouteTemplate) {
            return websocketRouteTemplate;
        }
        for (Map.Entry<String, WebsocketRouteTemplate> entry : VARIABLE_ROUTE_TEMPLATE.entrySet()) {
            if (UriFactory.isMatch(entry.getKey(), requestUri)) {
                return entry.getValue();
            }
        }
        throw new WebsocketException("unMatch RequestUri:" + requestUri);
    }
}
