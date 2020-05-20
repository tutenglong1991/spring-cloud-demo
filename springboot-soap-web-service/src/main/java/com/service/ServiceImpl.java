package com.service;

import javax.jws.WebService;

import org.springframework.stereotype.Component;

@Component
@WebService
public class ServiceImpl implements IService {

	@Override
	public String test1() {
		return "test1()";
	}

	@Override
	public String test2() {
		return "test2()";

	}

}
