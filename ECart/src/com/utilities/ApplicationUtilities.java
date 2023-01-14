package com.utilities;

import com.connection.Connections;
import com.services.OrderServiceImpl;

public class ApplicationUtilities {

	public static void orderHistoryOfUser(String token, int userId) {
		OrderServiceImpl orderService = new OrderServiceImpl(Connections.getConnect());
		System.out.println(" --");
		System.out.println("OrderId : Product name   : proudctQTY : OrderPrice");

		orderService.checkuserOrderHistory(userId, token).forEach(it -> {
			System.out.println(( it.getOrderId() + ":" +  it.getProductId() + ":"
					+ ( it.getProductQty() + ":" + (it.getOrderPrice());
		});
		System.out.print("______");
	}

}
