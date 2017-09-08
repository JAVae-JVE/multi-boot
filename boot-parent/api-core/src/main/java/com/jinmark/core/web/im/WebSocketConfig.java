package com.jinmark.core.web.im;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * WebScoket配置处理器
 * QC
 * 2016年12月21日 下午1:57:26
 */
@Configuration  
@EnableWebSocket
public class WebSocketConfig implements
		WebSocketConfigurer {

	@Resource
	private MyWebSocketHandler webSocketHandler;
	
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(webSocketHandler, "/ws.html").addInterceptors(new HandshakeInterceptor());
		//支持SockJS， SockJS是一个浏览器上运行的 JavaScript 库，如果浏览器不支持 WebSocket，该库可以模拟对 WebSocket 的支持，实现浏览器和 Web 服务器之间低延迟、全双工、跨域的通讯通道。
		registry.addHandler(webSocketHandler, "/ws/sockjs.html").addInterceptors(new HandshakeInterceptor()).withSockJS();
	}

}
