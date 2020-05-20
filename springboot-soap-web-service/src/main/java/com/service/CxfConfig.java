package com.service;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

import javax.xml.ws.Endpoint;

/**
 * CXF配置
 * 
 * @author zincredible
 * @date 2019-01-23 11:20:24
 */
@Slf4j
@Configuration
public class CxfConfig {

	@Autowired
	private IService ws;

	/**
	 * 注册servlert,访问地址http://127.0.0.1:8090/test
	 * 
	 * @return
	 */
	@Bean
	public ServletRegistrationBean dispatcherServlet() {
		return new ServletRegistrationBean(new CXFServlet(), "/test/*");
	}

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}

	@Bean
	public Endpoint endpoint() {
		log.info("开始发布webService接口.");
		EndpointImpl endpoint = new EndpointImpl(springBus(), ws);
		endpoint.publish("/api");
		log.info("webService接口发布完成.");
		return endpoint;
	}
}
