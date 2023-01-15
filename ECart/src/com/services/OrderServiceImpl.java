package com.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.entities.Order;
import com.exceptions.EcartExceptions;
import com.messages.MessageProperties;
import com.struct.OrderStruct;


public class OrderServiceImpl  implements OrderServiceInterface {
	private Connection con;
	
	//Constuctor with Connection object as an argument
	public OrderServiceImpl(Connection con) {
		super();
		this.con = con;
	}

	//Function to place order by customer.
	@Override
	public int placeOrder(String query, Order order) {
		// TODO Auto-generated method stub
		UserServiceImpl userService = new UserServiceImpl(con);
		ProductServiceImpl productService = new ProductServiceImpl(con);
		CartServiceImpl cartService = new CartServiceImpl(con);
		ResultSet getProduct = null;
		ResultSet cartByUserIdAndProdutId = null;
		int orderPlaced = 0;
		PreparedStatement st = null;
		try {
			ResultSet getUser = userService.findByUsername(query);
			getProduct = productService.findProductById(order.getProductId());

			boolean isUserPresent = getUser.next();
			if (!isUserPresent) {
				throw new EcartExceptions(MessageProperties.PLEASE_LOGIN.getMessage());
			}
			cartByUserIdAndProdutId = cartService.findCartByUserIdAndProductId(getUser.getInt(4), order.getProductId());
			boolean isProductPresentInCart = cartByUserIdAndProdutId.next();
			if (!isProductPresentInCart) {
				throw new EcartExceptions(MessageProperties.SELECT_PRODUCT.getMessage());
			}
			st = con.prepareStatement("Insert into order_detail(product_qty, order_price, user_id, product_id) values(?,?,?,?)");
			if (order.getProductQty() < cartByUserIdAndProdutId.getInt(4)) {
				st.setInt(1, order.getProductQty());
			}
			int getProductPrice = getProduct.next() ? getProduct.getInt(4) : 0;
			st.setInt(2, order.getProductQty() * getProductPrice);
			st.setInt(3, cartByUserIdAndProdutId.getInt(2));
			st.setInt(4, cartByUserIdAndProdutId.getInt(3));
			orderPlaced = st.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (orderPlaced == 1) {
			cartService.decreaseQtyInCart(query, cartByUserIdAndProdutId, order);
			System.out.println("💥 Congratulation 🎉"+MessageProperties.ORDER_PLACED.getMessage()+" 👏  🎉💥");
			System.out.println("********************Your order details are as follows !*****************************");
			try {
				PreparedStatement stat = con
						.prepareStatement("select * from order_detail order by order_id desc limit 1");
				ResultSet set = stat.executeQuery();
				System.out.println(
						"___________________________________________________________________________________________________________________________________");
				System.out.println("OrderId: ProductQty: OrderPrice: Product name");
				System.out.println(
						"----------------------------------------------------------------------------------------------------------------------");
				while (set.next()) {
					System.out.println(set.getInt(1) + "     : " + set.getInt(2) + "     :  " + set.getInt(3) + "  : "
							+ getProduct.getString(2));
				}
				System.out.println(
						"-----------------------------------------------------------------------------------------------------------------------------");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return orderPlaced;
	}

	//Function to Check the order history for the user.
	@Override
	public List<OrderStruct> checkuserOrderHistory(int userId, String query) {
		// TODO Auto-generated method stub
		List<OrderStruct> orderList = new ArrayList<>();
		UserServiceImpl userService = new UserServiceImpl(con);
		ProductServiceImpl productSevice = new ProductServiceImpl(con);
		ResultSet getUser = userService.findByUsername(query);

		OrderStruct order = null;
		PreparedStatement pst = null;
		boolean isUserPresent;
		try {
			isUserPresent = getUser.next();
			if (!isUserPresent) {
				throw new EcartExceptions(MessageProperties.PLEASE_LOGIN.getMessage());
			}
			boolean isAdmin = getUser.getBoolean(3);
			if (!isAdmin) {
				throw new EcartExceptions(MessageProperties.GET_PERMISSION.getMessage(),
						EcartExceptions.ExceptionType.Unauthorised_User);
			}

			pst = con.prepareStatement("select * from order_detail where user_id=?");
			pst.setInt(1, userId);
			ResultSet getOrders = pst.executeQuery();
			while (getOrders.next()) {
				order = new OrderStruct();
				order.setOrderId((getOrders.getInt(1)));
				order.setProductQty(getOrders.getInt(2));
				order.setOrderTotal((getOrders.getInt(3)));

				ResultSet getProduct = productSevice.findProductById(getOrders.getInt(5));
				String productName = getProduct.next() ? getProduct.getString(2) : null;
				order.setProductName(productName);
				orderList.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}
}

	

