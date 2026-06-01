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
package io.github.thierrysquirrel.websocket.registration;

import io.github.thierrysquirrel.websocket.annotation.WebsocketRoute;
import io.github.thierrysquirrel.websocket.core.template.WebsocketRouteTemplate;
import io.github.thierrysquirrel.websocket.init.core.factory.WebsocketRouteInitFactory;
import io.github.thierrysquirrel.websocket.init.core.factory.execution.WebsocketInitExecution;
import io.github.thierrysquirrel.websocket.loading.WebsocketLoading;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ClassName: WebsocketRegistration
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class WebsocketRegistration {
    private static final Logger logger = Logger.getLogger(WebsocketRegistration.class.getName());

    private WebsocketRegistration() {
    }

    public static void webSocketRegistrationScannerAll(List<Class<?>> scannerClassList, Map<Class<?>, Object> registrationMap) {
        for (Class<?> thisClass : scannerClassList) {
            WebsocketRoute websocketRoute = thisClass.getAnnotation(WebsocketRoute.class);
            if (Objects.isNull(websocketRoute)) {
                continue;
            }
            WebsocketRouteTemplate routeTemplate = newInstance(thisClass);
            WebsocketRouteInitFactory.addWebsocketRouteTemplate(routeTemplate);

            registrationMap.put(thisClass, routeTemplate);
        }
        WebsocketLoading websocketLoading = (WebsocketLoading) registrationMap.get(WebsocketLoading.class);

        WebsocketInitExecution.init(websocketLoading.getUrl(),
                websocketLoading.getMaxFramePayloadLength(),
                websocketLoading.getReadTimeoutMilli());

    }

    private static WebsocketRouteTemplate newInstance(Class<?> thisClass) {
        WebsocketRouteTemplate websocketRouteTemplate = null;
        try {
            websocketRouteTemplate = (WebsocketRouteTemplate) thisClass.getDeclaredConstructor().newInstance();
        } catch (Throwable e) {
            String logMsg = "newInstance Error";
            logger.log(Level.WARNING, logMsg, e);
        }
        return websocketRouteTemplate;
    }
}
