package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * <ul>
 * <li>1.@EnableWebSocketMessageBroker注解表示开启使用STOMP协议来传输基于代理的消息，Broker就是代理的意思。</li>
 * <li>2.registerStompEndpoints方法表示注册STOMP协议的节点，并指定映射的URL。</li>
 * <li>3.stompEndpointRegistry.addEndpoint("/guide-link-point").withSockJS(),这一行代码用来注册STOMP协议节点，同时指定使用SockJS协议。</li>
 * <li>4.configureMessageBroker方法用来配置消息代理，由于我们是实现推送功能，这里的消息代理是/topic</li>
 * </ul>
 * @Description: webSocket配置
 * @author zhangsike
 * @date 2018年6月15日
 *
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer
{
	@Override
	public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry)
	{
		// 是前台连接的端点url
		stompEndpointRegistry.addEndpoint("/guide-link-point").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry)
	{
		//服务端发送消息给客户端的域,多个用逗号隔开
		registry.enableSimpleBroker("/topic","/user");
		//定义一对一推送的时候前缀
        registry.setUserDestinationPrefix("/user");
		//配置客户端发送消息路径前缀
		registry.setApplicationDestinationPrefixes("/app");
	}
}
