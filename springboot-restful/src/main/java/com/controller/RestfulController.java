package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.model.UserInfo;

//import io.swagger.annotations.Api;

//@Api(tags = { "Restful API demo" })
@RestController
public class RestfulController {
	
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello World";
	}
	
	@RequestMapping(value = "/person/{personId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserInfo findPerson(@PathVariable("personId") Integer personId){
		UserInfo u = new UserInfo(personId, "Crazyit", 30);
		return u ;
	}

	@PostMapping(value = "/person/addUser")
	public void addUser(@RequestBody UserInfo userInfo) {
//		return users;
	}
	
	@GetMapping(value = "/person/queryAll")
	public List<UserInfo> getUsers() {
		List<UserInfo> users = new ArrayList<UserInfo>();
		users.add(new UserInfo(1,"zhangsan", 10));
		users.add(new UserInfo(2,"lisw", 20));
		return users;
	}
//
//	@DeleteMapping(value = "/v1/user/{id}")
//	public void deleteUser(@PathVariable String id) {
//
//	}
//
//	@PutMapping(value = "/v1/user")
//	public void updateUser(@RequestBody UserInfo userInfo) {
//
//	}
//
//	@GetMapping(value = "/v1/user")
//	public List<UserInfo> getUsers() {
//		List<UserInfo> users = new ArrayList<UserInfo>();
//		users.add(new UserInfo());
//		return users;
//	}
}
