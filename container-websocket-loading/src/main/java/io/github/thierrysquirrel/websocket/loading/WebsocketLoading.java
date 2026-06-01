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

package io.github.thierrysquirrel.websocket.loading;

import io.github.thierrysquirrel.container.scanner.annotation.ScannerPackage;
import io.github.thierrysquirrel.container.scanner.registration.InterfaceManualRegistration;
import io.github.thierrysquirrel.websocket.loading.constant.WebsocketLoadingConstant;

import java.util.List;
import java.util.Map;

/**
 * ClassName: WebsocketLoading
 * Description:
 * date: 2026/6/2
 *
 * @author ThierrySquirrel
 * @since JDK 25
 */
@ScannerPackage(packageName = "io.github.thierrysquirrel.websocket.loading")
public class WebsocketLoading implements InterfaceManualRegistration {
    /**
     * ServerUrl
     * 服务Url
     */
    private String url = WebsocketLoadingConstant.DEFAULT_URL;
    /**
     * MaxFramePayloadLength
     * 最大帧载荷长度
     */
    private int maxFramePayloadLength = WebsocketLoadingConstant.DEFAULT_MAX_FRAME_PAYLOAD_LENGTH;
    /**
     * ReadTimeoutMilli
     * 读取超时
     */
    private int readTimeoutMilli = WebsocketLoadingConstant.DEFAULT_READ_TIMEOUT_MILLI;

    @Override
    public void scannerAll(List<Class<?>> list, Map<Class<?>, Object> map) {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getMaxFramePayloadLength() {
        return maxFramePayloadLength;
    }

    public void setMaxFramePayloadLength(int maxFramePayloadLength) {
        this.maxFramePayloadLength = maxFramePayloadLength;
    }

    public int getReadTimeoutMilli() {
        return readTimeoutMilli;
    }

    public void setReadTimeoutMilli(int readTimeoutMilli) {
        this.readTimeoutMilli = readTimeoutMilli;
    }

    @Override
    public String toString() {
        return "WebsocketLoading{" +
                "url='" + url + '\'' +
                ", maxFramePayloadLength=" + maxFramePayloadLength +
                ", readTimeoutMilli=" + readTimeoutMilli +
                '}';
    }
}
