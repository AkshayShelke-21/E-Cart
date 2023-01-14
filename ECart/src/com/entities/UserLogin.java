package com.entities;

public class UserLogin {
	//Entities for UserLogin class
	private String username;
	private String password;
	
	//Constructor with User object argument
	public UserLogin(User user){
		this.username = user.getUsername();
		this.password = user.getPassword();
	}
	
	//No argument constructor
	public UserLogin() {}
	
	//Constructor with username and password argument
	public UserLogin(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	
	//Getter and Setter Methods
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
