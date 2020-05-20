package com.service;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class TestComponent {

	public String getTestMsg() {
		return "zincredible test " + UUID.randomUUID();
	}
}
