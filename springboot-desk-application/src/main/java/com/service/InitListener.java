package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Spirng容器启动完毕执行，用于程序初始化
 * 
 * @author zincredible
 * @date 2019/01/11 13:29:57
 */
@Slf4j
@Component
public class InitListener implements ApplicationRunner {

	@Autowired
	private FrameUI frame;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("start init ui");
		frame.initUI();
		log.info("ui init finish");
	}

}