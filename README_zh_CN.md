# websocket-spring-boot-starter

Websocket Spring Book Edition

[English](./README.md)

支持功能:
- [x] Websocket

# Websocket:  
 WebSocket是一种在单个TCP连接上进行全双工通信的协议  

# 集群
 [websocket-route-spring-boot-starter](https://github.com/ThierrySquirrel/websocket-route-spring-boot-starter)

## Quick Start

```xml
<!--在pom.xml中添加依赖-->
        <dependency>
            <artifactId>websocket-spring-boot-starter</artifactId>
            <groupId>io.github.thierrysquirrel</groupId>
            <version>1.2.0.1-RELEASE</version>
        </dependency>
``` 

 ### 配置文件
 
 ```properties
 ## application.properties
websocket.url=127.0.0.1:8181
 ```

 # 启动 Websocket
 ```java
 @SpringBootApplication
 public class WebsocketApplication{
     public static void main(String[] args){
         SpringApplication.run(WebsocketApplication.class, args);
     }
    
 }
 ```

# 开始使用 Websocket
```java
@Slf4j
@WebsocketRoute ("/user/{id}")
public class UserWebsocket extends AbstractWebsocketRouteTemplate {
    public static List<WebsocketChannelTemplate>session=new ArrayList<> ();
    @Override
    public void upgradeSuccessEvent(HttpUpgradeRequest request, WebsocketChannelTemplate channelTemplate) {
        Map<String, String> uriParam = request.getUriParam ();
        Map<String, String> uriVariable = request.getUriVariable ();
        HttpHeaders headers = request.getHeaders ();
        String hello = headers.get ("hello");
        if(hello.equals ("world")){
            session.add (channelTemplate);
            return;
        }
        channelTemplate.textMessage ("hello Is not equal to world");
    }

   
    @Override
    public void pingMessageEvent(WebsocketChannelTemplate channelTemplate, WebsocketMessageTemplate messageTemplate) {
        log.info ("ping");
        channelTemplate.pongMessage ();
    }

   
    @Override
    public void pongMessageEvent(WebsocketChannelTemplate channelTemplate, WebsocketMessageTemplate messageTemplate) {
        log.info ("pong");
        channelTemplate.pongMessage ();
    }

    @Override
    public void textMessageEvent(WebsocketChannelTemplate channelTemplate, WebsocketMessageTemplate messageTemplate) {
            String text = messageTemplate.convertText ();
            System.out.println (text);
            channelTemplate.textMessage ("hello");
    }

   
    @Override
    public void binaryMessageEvent(WebsocketChannelTemplate channelTemplate, WebsocketMessageTemplate messageTemplate) {
        try {
            messageTemplate.transferTo ("/file");
            channelTemplate.textMessage ("success");
        } catch (IOException e) {
            e.printStackTrace ();
            channelTemplate.textMessage ("fail");
        }
    }

   
    @Override
    public void closeMessageEvent(WebsocketChannelTemplate channelTemplate, WebsocketMessageTemplate messageTemplate) {
        channelTemplate.closeMessage ();
        session.remove (channelTemplate);
    }

    @Override
    public void errorEvent(WebsocketChannelTemplate channelTemplate, WebsocketException websocketException) {
        websocketException.printStackTrace ();
    }

    @Override
    public void connectionTimeoutEvent(WebsocketChannelTemplate channelTemplate) {
        channelTemplate.closeMessage ();
        session.remove (channelTemplate);
    }

   
    @Override
    public void closeEvent(WebsocketChannelTemplate channelTemplate) {
        channelTemplate.closeMessage ();
        session.remove (channelTemplate);
    }
}
```


