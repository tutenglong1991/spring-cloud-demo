package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		// java.awt.headless默认为true，适配无输出输出设备环境（服务器），单机版程序设置为false
		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(Application.class, args);
	}

}
