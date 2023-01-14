package com.services;

import java.util.List;

import com.entities.Order;
import com.entities.orderModel1;

public interface orderservice {
	int placeOrder(String token, Order order);

	List<orderModel1> checkuserOrderHistory(int userId, String token);


}
