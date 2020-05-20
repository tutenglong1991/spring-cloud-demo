package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-a")
public class ServiceAContoller
{
	@RequestMapping("/test")
	public String getATest()
	{
		return "this is service a api";
	}
}
