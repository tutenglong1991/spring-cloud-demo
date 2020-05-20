package com.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebSocketTemp
{
	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	// 向订阅了 /topic/hello 客户端websocket实例发送数据
	public void sendMessage(String message)
	{
		messagingTemplate.convertAndSend("/topic/log",message);
	}
}