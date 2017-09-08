package com.jinmark.core.web.im;

import java.util.Map;


import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;


/**
 * Socket建立连接（握手）和断开 ClassName: HandshakeInterceptor
 * QC
 * 2016年12月21日 下午1:55:27
 */
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {

	@Override
	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		/*System.out.println("Websocket:用户[ID:"
				+ ((BsUser)((ServletServerHttpRequest) request).getServletRequest()
						.getSession(false).getAttribute(Constants.CURRENT_USER)).getUserId() + "]已经建立连接");
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest()
					.getSession(false);
			// 标记用户
			BsUser user = (BsUser) session.getAttribute(Constants.CURRENT_USER);
			if (user.getUserId() != null) {
				attributes.put(Constants.SOCKET_UID, user.getUserId());
			} else {
				return false;
			}
		}*/
		return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {
		// TODO Auto-generated method stub
		super.afterHandshake(request, response, wsHandler, ex);
	}

}
