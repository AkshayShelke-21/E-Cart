package com.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.entities.Order;
import com.messages.MessageProperties;



public class OrderServiceImpl  implements orderservice {
	private Connection Con;
	
	public OrderServiceImpl(Connection con) {
		super();
		this.Con=con;
	}
	

	

	@Override
	public int placeOrder(String token, Order order) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List checkuserOrderHistory(int userId, String token) {
		// TODO Auto-generated method stub
		return null;
	}

	
	}

	

