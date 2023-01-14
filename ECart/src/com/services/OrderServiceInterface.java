package com.services;

import java.util.List;

import com.entities.Order;
import com.struct.OrderStruct;


public interface OrderServiceInterface {

	int placeOrder(String token, Order order);

	List<OrderStruct> checkuserOrderHistory(int userId, String token);
}
