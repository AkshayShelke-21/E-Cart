package com.services;

import java.util.*;
import com.entities.User;
import com.entities.UserLogin;

//Interface to define User related Services.
public interface UserServiceInterface {

	//Fuction to register new user to platform.
	public boolean UserRegistration(User user);

	//Function to login user to platform.
	public String loginUser(UserLogin loginUser);

	//Function to get details of all user.
	public List<User> getAllUsers(String token);

	//Function to make user admin (Rights exclusively reserved for admin).
	public int makeUserAdmin(String token, int userId);
}
