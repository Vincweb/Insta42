package com.gr42.insta.model;

import java.io.Serializable;

public class Employee1 implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private Integer age;

	public Employee1(){}
	
	public Employee1(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String toString() {
		return "Employee [" + getId() + ", " + getName() + "]";
	}
}