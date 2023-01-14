package com.services;

import java.util.List;

import com.entities.Order;
public interface orderservice {
	int placeOrder(String token, Order order);

	public List checkuserOrderHistory(int userId, String token);
	
}
