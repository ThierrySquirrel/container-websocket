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
package io.github.thierrysquirrel.websocket.core.template.core.constant;

/**
 * ClassName: WebsocketMessageConstant
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
public enum WebsocketMessageConstant {
    /**
     * PingResponse
     */
    PING_RESPONSE(new byte[]{-22, -110, -80, -30, -111, -123, -30, -128, -94, -31, -76, -105, -30, -128, -94, -30, -111, -123, -22, -110, -79}),
    /**
     * PongResponse
     */
    PONG_RESPONSE(new byte[]{-30, -126, -115, -31, -112, -94, 32, -30, -128, -94, -30, -116, -124, -30, -128, -94, 32, -31, -112, -94, -30, -126, -114});

    private final byte[] value;

    WebsocketMessageConstant(byte[] value) {
        this.value = value;
    }

    public byte[] getValue() {
        return value;
    }
}
