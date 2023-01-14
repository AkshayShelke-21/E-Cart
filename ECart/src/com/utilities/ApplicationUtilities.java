package com.utilities;

import com.services.OrderServiceImpl;

public class ApplicationUtilities {

	public static void orderHistoryOfUser(String token, int userId) {
		OrderServiceImpl orderService = new OrderServiceImpl(DB.connectDb());
		System.out.println(" --");
		System.out.println("OrderId : Product name   : proudctQTY : OrderPrice");
		System.out.println("--------------");
		orderService.checkuserOrderHistory(userId, token).forEach(it -> {
			System.out.println(it.getOrderId() + ":" + it.getProductName() + ":" + it.getProductOty() + ":"+ it.getOrderPrice());
					
		});
		System.out.print("______");
	}
	

}
