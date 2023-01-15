package com.services;

import java.util.*;
import com.entities.User;
import com.entities.UserLogin;

//Interface to define User related Services.
public interface UserServiceInterface {

	public boolean UserRegistration(User user);

	public String loginUser(UserLogin loginUser);

	public List<User> getAllUsers(String token);

	public int makeUserAsAdmin(String token, int userId);
}
