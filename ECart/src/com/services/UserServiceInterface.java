package com.services;

import java.util.*;
import com.entities.User;
import com.entities.UserLogin;

public interface UserServiceInterface {

	public boolean createUserRegistration(User user);

	public String loginUser(UserLogin loginUser);

	public List<User> getAllUsers(String token);

	public int makeUserAsAdmin(String token, int userId);
}
