package com.wjk.base.java.reflect;

public class User {
	private int age;
	private String name;
	public User() {
		super();
	}
	public User(String name) {
		super();
		this.name = name;
	}

	/**
	 * 私有构造
	 * @param age
	 * @param name
	 */
	@SuppressWarnings("unused")
	private User(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}
	
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
