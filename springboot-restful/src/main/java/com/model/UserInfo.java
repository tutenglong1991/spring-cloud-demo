package com.model;

//import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class UserInfo {

	//@JsonProperty：若不想实现类属性的getter和setter可使用该修饰方法处理
	private Integer personId;
	private String name;
	private int age;
	
	public UserInfo(Integer personId, String name, int age) {
		super();
		this.personId = personId;
		this.name = name;
		this.age = age;
	}
	
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
