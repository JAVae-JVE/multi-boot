package com.jinmark.core.web.im;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinmark.core.Constants;
/**
 * websocket处理类
 * QC
 * 2016年12月21日 下午1:56:27
 */
@Component
public class MyWebSocketHandler implements WebSocketHandler {

	public static final Map<String, WebSocketSession> userSocketSessionMap;
	
	static {
		userSocketSessionMap = new HashMap<String, WebSocketSession>();
	}
	
	/**
	 * 建立连接后
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		String uid = (String) session.getAttributes().get(Constants.SOCKET_UID);
		if(StringUtils.isNotBlank(uid)) {
			//暂时写法，后面会修改，处理离线问题
			userSocketSessionMap.put(uid, session);
		}
	}

	/**
	 * 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
	 */
	@Override
	public void handleMessage(WebSocketSession session,
			WebSocketMessage<?> message) throws Exception {
		if(message.getPayloadLength() == 0)return;
		ObjectMapper mapper = new ObjectMapper(); 
		Message msg = mapper.readValue(message.getPayload().toString(), Message.class);
		msg.setDate(new Date());//消息接收時間
		//服务器接收到发送者消息，转发给接收者
		sendMessageToUser(msg.getTo(), new TextMessage(mapper.writeValueAsString(msg)));
	}

	
	/**
	 * 消息传输错误处理
	 */
	@Override
	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {
		for (String uid : userSocketSessionMap.keySet()) {
			WebSocketSession webSocketSession = userSocketSessionMap.get(uid);
			if(webSocketSession.getId().equals(session.getId())) {
				//暂时写法，后面会修改，处理离线问题
				userSocketSessionMap.remove(uid);
				System.out.println("Socket会话已经移除:用户ID" + uid);
				break;
			}
		}
		
	}

	
	/**
	 * 关闭连接后
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus closeStatus) throws Exception {
		System.out.println("Websocket:" + session.getId() + "已经关闭");
		for (String uid : userSocketSessionMap.keySet()) {
			WebSocketSession webSocketSession = userSocketSessionMap.get(uid);
			if(webSocketSession.getId().equals(session.getId())) {
				//暂时写法，后面会修改，处理离线问题
				userSocketSessionMap.remove(uid);
				System.out.println("Socket会话已经移除:用户ID" + uid);
				break;
			}
		}
	}
	
	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	/**
	 * 给所有在线用户发送消息
	 * @param message 消息
	 */
	public void broadcast(final TextMessage message) {
		//多线程群发
		for (String uid : userSocketSessionMap.keySet()) {
			final WebSocketSession webSocketSession = userSocketSessionMap.get(uid);
			if(webSocketSession.isOpen()) {//在线
				new Thread(new Runnable() {
					
					public void run() {
						try {
							if (webSocketSession.isOpen()) {
								webSocketSession.sendMessage(message);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}).start();
			}else {//离线
				
			}
		}
	}
	
	/**
	 * 给某个用户发送消息
	 * @param uid 用户id
	 * @param message 消息
	 * @throws IOException 异常
	 */
	public void sendMessageToUser(String uid, TextMessage message) throws IOException {
		WebSocketSession session = userSocketSessionMap.get(uid);
		if(session != null && session.isOpen()) {// 用户在线
			session.sendMessage(message);
		}else {//用户离线
			//待处理
		}
	}
}
