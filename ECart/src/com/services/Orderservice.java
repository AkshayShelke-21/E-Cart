package com.services;

import java.util.List;

import com.entities.Order;
public interface Orderservice {
	int placeOrder(String token, Order order);

	List checkuserOrderHistory(int userId, String token);


}
