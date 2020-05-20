package com.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.param.response.MsgParam;
import com.param.response.MsgResponse;

@Controller
public class MsgController
{
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index()
	{
		return "index";
	}

	@RequestMapping(value = "/to/{server}", method = RequestMethod.GET)
	public String server(@PathVariable("server") String name)
	{
		return name;
	}

	/**
	 * 服务端发送消息到客户端
	 * @param msg
	 * @return
	 */
	@MessageMapping("/welcome")
	@SendTo("/topic/msg")
	public MsgResponse say(@RequestBody MsgParam msg)
	{
		return new MsgResponse("welcome," + msg.getMsg() + " !");
	}
	
	/**
	 * 服务端发送消息到客户端
	 * 
	 * @param msg
	 * @return
	 */
	@MessageMapping(value="/server/{serverId}/{userId}")
	public void say(MsgParam msg,@DestinationVariable("serverId") String serverId, @DestinationVariable("userId") String userId)
	{
		System.out.println(serverId+","+userId);
		messagingTemplate.convertAndSendToUser(userId, "/msgClient", msg.getMsg());
		messagingTemplate.convertAndSendToUser(serverId,"/msgServer","向," + userId + "推送：" + msg.getMsg() + " 成功 !");
	}
}