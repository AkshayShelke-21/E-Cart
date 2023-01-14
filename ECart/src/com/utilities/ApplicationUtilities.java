package com.utilities;
import com.connection.Connections;
import com.services.OrderServiceImpl;

public class ApplicationUtilities {

	Connection connect;
	public static void orderHistoryOfUser(String token, int userId) {
		OrderServiceImpl orderService = new OrderServiceImpl(Connections.);
		System.out.println(" --");
		System.out.println("OrderId : Product name   : proudctQTY : OrderPrice");
		System.out.println("--------------");
		orderService.checkuserOrderHistory(userId, token).forEach(it -> {
			System.out.println(it.getOrderId() + ":" + it.getProductName() + ":" + it.getProductOty() + ":"+ it.getOrderPrice());
					
		});
		System.out.print("______");
	}

}
