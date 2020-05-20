package com.service;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ServerEndpoint(value = "/websocket/{id}")
// 订阅方式为ws://localhost:7005/websocket/1
public class WebSocketServer {
	private static int onlineCount = 0;
	// 如果使用ConcurrentHashMap保存会出现 webSocket session has been closed的问题，
	// 服务器关闭session时会将session从线程安全队列移除
	// 使用CopyOnWriteArraySet保存整个类
	private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;
	private static Logger log = LoggerFactory.getLogger(WebSocketServer.class);
	private String id = "";

	public String getId() {
		return id;
	}

	/**
	 * 连接建立成功调用的方法
	 */
	@OnOpen
	public void onOpen(@PathParam(value = "id") String id, Session session) {
		this.session = session;
		this.id = id;// 接收到发送消息的人员编号
		webSocketSet.add(this); // 加入set中
		addOnlineCount(); // 在线数加1
		sendMessage("连接成功");
		log.info("用户" + id + "加入！" + "当前在线人数为" + getOnlineCount());
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		webSocketSet.remove(this); // 从set中删除
		subOnlineCount(); // 在线数减1
		log.info("用户" + id + "关闭连接！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 收到客户端消息后调用的方法
	 *
	 * @param message 客户端发送过来的消息
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		log.info("来自客户端" + id + "的消息:" + message);

		// 可以自己约定字符串内容，比如 内容|0 表示信息群发，内容|X 表示信息发给id为X的用户
		String sendMessage = message.split("[|]")[0];
		String sendUserId = message.split("[|]")[1];
		try {
			if (sendUserId.equals("0"))
				sendtoAll(sendMessage);
			else
				sendToUser(sendMessage, sendUserId);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 *
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		log.error("发生错误");
		error.printStackTrace();
	}

	public void sendMessage(String message) {
		try {
			this.session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送信息给指定ID用户，如果用户不在线则返回不在线信息给自己
	 * 
	 * @param message
	 * @param sendUserId
	 * @throws IOException
	 */
	public void sendToUser(String message, String sendUserId) throws IOException {
		if (webSocketSet.size() == 0)
			return;
		boolean sended = false;
		for (WebSocketServer s : webSocketSet) {
			if (s != null) {
				if (s.getId().equals(sendUserId)) {
					sended = true;
					s.sendMessage(message);
				} else {
					sendMessage(message);
				}
			}

		}
		if (!sended) {
			sendMessage("用户[" + sendUserId + "]不在线");
		}
	}

	/**
	 * 发送信息给所有人
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void sendtoAll(String message) {
		for (WebSocketServer s : webSocketSet) {
			if (s != null) {
				s.sendMessage(message);
			}
		}
	}

	public synchronized int getOnlineCount() {
		return onlineCount;
	}

	public synchronized void addOnlineCount() {
		WebSocketServer.onlineCount++;
	}

	public synchronized void subOnlineCount() {
		WebSocketServer.onlineCount--;
	}
}