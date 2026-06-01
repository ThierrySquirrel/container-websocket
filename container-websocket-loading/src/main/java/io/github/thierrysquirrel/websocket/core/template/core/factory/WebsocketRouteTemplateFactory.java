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
package io.github.thierrysquirrel.websocket.core.template.core.factory;

import io.github.thierrysquirrel.websocket.annotation.WebsocketRoute;
import io.github.thierrysquirrel.websocket.core.template.WebsocketRouteTemplate;

/**
 * ClassName: WebsocketRouteTemplateFactory
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class WebsocketRouteTemplateFactory {
    private WebsocketRouteTemplateFactory() {
    }

    public static String getRoute(WebsocketRouteTemplate routeTemplate) {
        return routeTemplate.getClass().getAnnotation(WebsocketRoute.class).value();
    }
}
