package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		// 解决 java.awt.HeadlessException: null异常
		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(Application.class, args);
	}

}
