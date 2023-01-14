package com.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entities.User;
import com.entities.UserLogin;
import com.exceptions.EcartExceptions;
import com.messages.MessageProperties;

public class UserServiceImpl implements UserServiceInterface{
	//
	private Connection con;
	
	public UserServiceImpl(Connection con) {
		this.con = con;
	}
	
	public ResultSet findByUsername(String email) {
		PreparedStatement findByUsername = null;
		ResultSet set = null;
		try {
			 findByUsername = con.prepareStatement("select user_password, user_name, is_admin, user_id from user where user_name=?");
			findByUsername.setString(1, email);
			set = findByUsername.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return set;
	}
	
	
	@Override
	public boolean createUserRegistration(User user) {
		boolean isRegistred = false;
		
		try {
			if(findByUsername(user.getUsername()).next()){
				throw new EcartExceptions("This User Name is already registerd, Please Login!", 
						EcartExceptions.ExceptionType.User_Already_Present);
			}
			PreparedStatement statemet = con.prepareStatement("INSERT INTO user(is_admin, user_name, user_id, user_password) VALUES (?,?,?,?)");
			statemet.setBoolean(1, user.isAdmin());
			statemet.setString(2, user.getUsername());
			statemet.setInt(3, user.getUserId());
			statemet.setString(4, user.getPassword());
			isRegistred = statemet.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isRegistred;
	}



	@Override
	public String loginUser(UserLogin loginUser) {
		// TODO Auto-generated method stub
		
		String mod = "";
		try {
			ResultSet userByUsername = findByUsername(loginUser.getUsername());
			if(!userByUsername.next()) {
				throw new EcartExceptions("User with this username is not registered, Please signUp First ", EcartExceptions.ExceptionType.Email_Not_Found);
			}
			boolean password = loginUser.getPassword().equals(userByUsername.getString(1));
			if(!password) {
				throw new EcartExceptions("Incorrect password", EcartExceptions.ExceptionType.Password_Incorrect);
			}
			mod = userByUsername.getString(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(MessageProperties.LOGIN_SUCCESSFUL.getMessage());
		return mod;
	}



	@Override
	public List<User> getAllUsers(String mod) {
		// TODO Auto-generated method stub
		
		List<User> userList = new ArrayList<>();
		User user = null;
		try {
			ResultSet userByEmail = findByUsername(mod);
			if(!userByEmail.next()) {
				throw new EcartExceptions(MessageProperties.PLEASE_LOGIN.getMessage());
			}
			boolean isAdmin = userByEmail.getBoolean(3);
			if(!isAdmin) {
				throw new EcartExceptions(MessageProperties.GET_PERMISSION.getMessage(),
						EcartExceptions.ExceptionType.Unauthorised_User );
			}
			PreparedStatement state = con.prepareStatement("select * from user");
			ResultSet rs = state.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setUserId(rs.getInt(1));
				user.setAdmin(rs.getBoolean(2));
				user.setUsername(rs.getString(3));
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}



	@Override
	public int makeUserAsAdmin(String mod, int userId) {
		// TODO Auto-generated method stub
		
		int implVar =0;
		PreparedStatement stat = null;
		ResultSet user = findByUsername(mod);
		String query="update user set is_admin=? where user_id=?";
		try {
			if(!user.next()) {
				throw new EcartExceptions(MessageProperties.PLEASE_LOGIN.getMessage());
			}
			boolean isAdmin = user.getBoolean(3);
			if(!isAdmin) {
				throw new EcartExceptions(MessageProperties.GET_PERMISSION.getMessage());
			}
			stat = con.prepareStatement(query);
			stat.setBoolean(1, true);
			stat.setInt(2, userId);
			implVar = stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return implVar;
	}
}
