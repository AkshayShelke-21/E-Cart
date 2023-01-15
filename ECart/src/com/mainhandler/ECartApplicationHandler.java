package com.mainhandler;

import com.connection.Connections;
import com.services.UserServiceImpl;
import com.services.UserServiceInterface;

public class ECartApplicationHandler {
	
	public static void run(){
		
		UserServiceInterface userService=new UserServiceImpl(Connections.getConnect());
		
	}
	
	public static void main(String[] args) {
		
	}

}
