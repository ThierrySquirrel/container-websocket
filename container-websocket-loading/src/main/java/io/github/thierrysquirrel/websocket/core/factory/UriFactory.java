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
package io.github.thierrysquirrel.websocket.core.factory;

import io.github.thierrysquirrel.websocket.core.constant.UriFactoryConstant;
import io.github.thierrysquirrel.websocket.core.exception.WebsocketException;
import io.netty.util.CharsetUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName: UriFactory
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public class UriFactory {
    private UriFactory() {
    }

    public static boolean containVariable(String route) {
        return route.contains(UriFactoryConstant.OPEN_BRACE);
    }

    public static boolean isMatch(String route, String requestUri) {
        requestUri = conversionRouteUri(requestUri);
        String[] routeUriSplit = route.split(UriFactoryConstant.SLASH);
        String[] requestUriSplit = requestUri.split(UriFactoryConstant.SLASH);
        for (int i = 0; i < routeUriSplit.length; i++) {
            if (routeUriSplit[i].contains(UriFactoryConstant.OPEN_BRACE)) {
                continue;
            }
            if (!routeUriSplit[i].equals(requestUriSplit[i])) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;

    }

    public static String conversionRouteUri(String requestUri) {
        if (!requestUri.contains(UriFactoryConstant.QUESTION_MARK)) {
            return requestUri;
        }
        int index = requestUri.indexOf(UriFactoryConstant.QUESTION_MARK);
        return requestUri.substring(0, index);
    }


    public static Map<String, String> getUriParamMap(String requestUri) throws WebsocketException {
        try {
            requestUri = URLDecoder.decode(requestUri, CharsetUtil.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new WebsocketException("Uri Decoder Error", e);
        }
        if (!requestUri.contains(UriFactoryConstant.QUESTION_MARK)) {
            return new ConcurrentHashMap<>();
        }
        int index = requestUri.indexOf(UriFactoryConstant.QUESTION_MARK);
        String filterUri = requestUri.substring(index + 1);
        String[] splitUri = filterUri.split(UriFactoryConstant.AMPERSAND);

        Map<String, String> paramMap = new ConcurrentHashMap<>();
        for (String uriParam : splitUri) {
            int substringIndex = uriParam.indexOf(UriFactoryConstant.IS_EQUAL_TO);
            String key = uriParam.substring(0, substringIndex);
            String value = uriParam.substring(substringIndex + 1);
            paramMap.put(key, value);
        }
        return paramMap;
    }


    public static Map<String, String> getUriVariable(String routeUri, String clientUri) {
        clientUri = conversionRouteUri(clientUri);

        Map<String, String> uriVariableMap = new ConcurrentHashMap<>();
        String[] routeUriSplit = routeUri.split(UriFactoryConstant.SLASH);
        String[] clientUriSplit = clientUri.split(UriFactoryConstant.SLASH);
        for (int i = 0; i < routeUriSplit.length; i++) {
            String serverUriVariable = routeUriSplit[i];
            if (serverUriVariable.contains(UriFactoryConstant.OPEN_BRACE)) {
                String key = serverUriVariable.substring(1, serverUriVariable.length() - 1);
                String value = clientUriSplit[i];
                uriVariableMap.put(key, value);
            }
        }
        return uriVariableMap;
    }
}
