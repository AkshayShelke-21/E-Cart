package com.services;

import java.util.List;

import com.entities.Order;
import com.struct.OrderStruct;


public interface OrderServiceInterface {

	//Function to place order by Customer.
	int placeOrder(String token, Order order);

	//Function to check order history of user.
	List<OrderStruct> checkuserOrderHistory(int userId, String token);
}
