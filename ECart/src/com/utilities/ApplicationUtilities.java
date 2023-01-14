package com.utilities;
import com.services.ProductServiceImpl;
import com.services.ProductServiceInterface;
import com.services.UserServiceImpl;
import com.services.UserServiceInterface;
import com.struct.CartStruct;
import com.connection.Connections;
import com.services.CartServiceImpl;
import com.services.CartServiceInterface;
import com.services.OrderServiceImpl;
import com.services.OrderServiceInterface;
import com.entities.*;
import com.messages.MessageProperties;

import java.util.List;
import java.util.Scanner;

public class ApplicationUtilities {


	public static void allProducts() throws ClassNotFoundException {
		ProductServiceInterface productService = new ProductServiceImpl(Connections.getConnect());
		List<Product> getProductList = productService.fetchAllProduct();
		System.out.println(
				"________________________________________________________________________________________________________________________");
		System.out.println("productId : Product Name   Product Description    Proudct price ");
		getProductList.forEach(it -> {
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------");
			System.out.println(it.getId() + ",     " + it.getProductName() + ",  " + it.getProductDiscreption() + ", "
					+ it.getProductPrice());
		});
		System.out.println(
				"__________________________________________________________________________________________________________________________");
	}
	
	
	public static void getCartDetailsOfUser(String mod) throws ClassNotFoundException {
		System.out.println(MessageProperties.CART_DETAILS.getMessage());
		CartServiceInterface cartService = new CartServiceImpl(Connections.getConnect());
		List<CartStruct> cartDetails = cartService.getCartDetails(mod);
		System.out.println(
				"____________________________________________________________________________________________________________________________________");
		System.out.println("ProductId : Product Name   : ProductPrice: TotalQty of product : TotalPrice");
		cartDetails.forEach(it -> {
			System.out.println(it.getProductId() + "         : " + it.getProductName() + "  : " + it.getProductPrice()
					+ "         : " + it.getProductQty() + "                : " + it.getTotalPrice());
		});
		System.out.println(
				"_____________________________________________________________________________________________________________________________________");

	}
	
	
	public static void operation(String mod, Scanner sc) throws ClassNotFoundException {
		CartServiceInterface cartService = new CartServiceImpl(Connections.getConnect());
		OrderServiceInterface orderService = new OrderServiceImpl(Connections.getConnect());
		System.out.println("***************************************Proudct Details**************************************");
		ApplicationUtilities.allProducts();
		System.out.println("To Add Product in Cart, Please Select Product Id");
		int id = sc.nextInt();
		System.out.println("Please Select Product QTY");
		int oty = sc.nextInt();
		Cart cart = new Cart();
		cart.setProductId(id);
		cart.setProductQty(oty);
		int addToCart = cartService.addToCart(mod, cart);
		if (addToCart == 1) {
			System.out.println("*********************************Your Cart Detils****************************************");
			ApplicationUtilities.getCartDetailsOfUser(mod);
			Order order = new Order();
			System.out.println("To Place Order please Select Id of Product");
			int productId = sc.nextInt();
			System.out.println("Please Select Qty of product");
			int qty = sc.nextInt();
			order.setProductId(productId);
			order.setProductQty(qty);
			int placeOrder = orderService.placeOrder(mod, order);
			if (placeOrder == 1) {
				System.out.println("************************Your Cart Datails After Order is placed**********************");
				ApplicationUtilities.getCartDetailsOfUser(mod);
			} else {
				System.out.println(MessageProperties.INTERNAL_ERROER.getMessage());
			}

		} else {
			System.out.println(MessageProperties.INTERNAL_ERROER.getMessage());
		}

	}
	
	
	
	public static String login(String mod, Scanner sc) {
		UserServiceInterface userService = new UserServiceImpl(Connections.getConnect());
		System.out.println("Please Login first");
		System.out.print("Please Enter Email: ");
		String userName = sc.next();
		System.out.println("Please Enter Your Password");
		String pass = sc.next();
		UserLogin loginUser = new UserLogin(userName, pass);
		
		try {
			mod = userService.loginUser(loginUser);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return mod;
	}
	
	
	public static void AllUser(String mod) throws ClassNotFoundException {
		UserServiceImpl userService = new UserServiceImpl(Connections.getConnect());
		System.out.println("____________________________________________________________________________________________________________________________");
		System.out.println("UserId : Name         : Email                : IsAmdmin  " );
		System.out.println("--------------------------------------------------------------------------------------------------------------");
		userService.getAllUsers(mod).forEach(it->{
			System.out.println(it.getUserId() +"      : "+it.getUsername()+"  : "+ " : " +(it.isAdmin() ? "YES" : "NO"));
		});
		System.out.println("____________________________________________________________________________________________________________________________________________");
	}
	
	
	@SuppressWarnings("unchecked")
	public static void orderHistoryOfUser( int userId, String mod) {
		OrderServiceImpl orderService = new OrderServiceImpl(Connections.getConnect());
		System.out.println("________________________________________________________________________________________________________________________________");
		System.out.println("OrderId : Product name   : proudctQTY : OrderPrice");
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		orderService.checkuserOrderHistory(userId, mod).forEach(it->{
			System.out.println( it.getOrderId()+"       : "+ it.getProductName()+"   :  "+it.getProductOty()+" : "+it.getOrderPrice());
		});
		System.out.println("____________________________________________________________________________________________________________________________________");
	}
}
