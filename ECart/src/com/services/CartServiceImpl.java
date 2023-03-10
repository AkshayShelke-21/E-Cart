package com.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entities.Cart;
import com.entities.Order;
import com.exceptions.EcartExceptions;
import com.messages.MessageProperties;
import com.struct.CartStruct;

public class CartServiceImpl implements CartServiceInterface {
	private Connection con;
	
	//Constructor with Connection object argument.
	public CartServiceImpl(Connection con) {
		this.con = con;
	}
	
	//Function to find Cart byusing cartId.
	public ResultSet findCartById(int cartId) {
		ResultSet getCart = null;
		try {
			PreparedStatement pst = con.prepareStatement("select * from cart where cart_id=?");
			pst.setInt(1, cartId);
			getCart = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getCart;
	}
	
	//Function to find Cart by productId.
	public ResultSet findCartByProductId(int productId) {
		ResultSet getCart = null;
		try {
			PreparedStatement pst = con.prepareStatement("select * from cart where product_id=?");
			pst.setInt(1, productId);
			getCart = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getCart;
	}
	
	//Function to find Cart by userId.
	public ResultSet findCartByUserId(int userId) {
		ResultSet getCart = null;
		try {
			PreparedStatement pst = con.prepareStatement("select * from cart where user_id=?");
			pst.setInt(1, userId);
			getCart = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getCart;
	}
	
	//Function to find Cart by userId and productId combinely.
	public ResultSet findCartByUserIdAndProductId(int userId, int productId) {
		ResultSet getCart = null;
		try {
			PreparedStatement pst = con.prepareStatement("select * from cart where user_id=? and product_id=?");
			pst.setInt(1, userId);
			pst.setInt(2, productId);
			getCart = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getCart;
	}
	
	//Function to decrease quantity in cart when customer doesn't want product.
	@Override
	public int decreaseQtyInCart(String token, ResultSet getCart, Order order) {
		int reducedQty = 0;
		ProductServiceImpl productService = new ProductServiceImpl(con);
		PreparedStatement pst = null;
		try {
			ResultSet getProduct = productService.findProductById(getCart.getInt(3));
			pst = con.prepareStatement("update cart set product_qty=?, total_price=? where cart_id=?");
			int qty = getCart.getInt(4) - order.getProductQty();
			int price = getCart.getInt(5) - (order.getProductQty() * (getProduct.next() ? getProduct.getInt(4) : 1));
			pst.setInt(1, qty);
			pst.setInt(2, price);
			pst.setInt(3, getCart.getInt(1));
			reducedQty = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reducedQty;
	}

	
	//Function to increase Price and Quantity of Cart when customer adds the product in cart.
	@Override
	public int increasePriceAndQtyWhenProductIsAdded(String username, Cart cart, int cartId) {
		// TODO Auto-generated method stub
		UserServiceImpl userService = new UserServiceImpl(con);
		ProductServiceImpl productService = new ProductServiceImpl(con);
		int updateQty = 0;
		PreparedStatement pst = null;
		try {
			ResultSet getUser = userService.findByUsername(username);
			ResultSet findCart = findCartById(cartId);
			ResultSet product = productService.findProductById(cart.getProductId());
			boolean isLoggedIn = getUser.next();
			if (!isLoggedIn) {
				throw new EcartExceptions(MessageProperties.PLEASE_LOGIN.getMessage());
			}
			if (!findCart.next()) {
				throw new EcartExceptions(MessageProperties.CART_NOT_FOUND.getMessage());
			}
			pst = con.prepareStatement("update cart set product_qty=?, total_price=? where cart_id=?");
			pst.setInt(1, cart.getProductQty() + findCart.getInt(4));
			pst.setInt(2, findCart.getInt(5) + cart.getProductQty() * (product.next() ? product.getInt(4) : 1));
			pst.setInt(3, cartId);
			updateQty = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQty;
	}

	//Function for adding into cart.
	@Override
	public int addToCart(String username, Cart cart) {
		// TODO Auto-generated method stub
		int addeToCart = 0;
		PreparedStatement state = null;
		try {
			UserServiceImpl userService = new UserServiceImpl(con);
			ProductServiceImpl productService = new ProductServiceImpl(con);

			ResultSet getUser = userService.findByUsername(username);
			ResultSet product = productService.findProductById(cart.getProductId());

			boolean isLoggedIn = getUser.next();
			if (!isLoggedIn) {
				throw new EcartExceptions(MessageProperties.PLEASE_LOGIN.getMessage());
			}
			boolean isProductPresent = product.next();
			if (!isProductPresent) {
				throw new EcartExceptions(MessageProperties.PRODUCT_NOT_FOUND.getMessage());
			}
			state = con.prepareStatement("insert into cart(user_id, product_id, product_qty, total_price) values(?,?,?,?)");
			ResultSet cartByUserIdAndProductId=findCartByUserIdAndProductId(getUser.getInt(4), cart.getProductId());
			boolean check = cartByUserIdAndProductId.next();
			if (check) {
				addeToCart = increasePriceAndQtyWhenProductIsAdded(username, cart, cartByUserIdAndProductId.getInt(1));

			} else {
				state.setInt(1, getUser.getInt(4));
				state.setInt(2, cart.getProductId());
				state.setInt(3, cart.getProductQty());
				int totalProcutPrice = cart.getProductQty() * product.getInt(4);
				state.setInt(4, totalProcutPrice);
				addeToCart = state.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addeToCart;
	}

	
	//Function to get details of Cart.
	@Override
	public List<CartStruct> getCartDetails(String username) {
		// TODO Auto-generated method stub
		List<CartStruct> cartList = new ArrayList<>();
		UserServiceImpl userService = new UserServiceImpl(con);
		ResultSet getUser = userService.findByUsername(username);
		ProductServiceImpl productService = new ProductServiceImpl(con);
		PreparedStatement pst = null;
		CartStruct cartStruct = null;
		try {
			boolean isLoggedIn = getUser.next();
			if (!isLoggedIn) {
				throw new EcartExceptions(MessageProperties.PLEASE_LOGIN.getMessage());
			}
			pst = con.prepareStatement("select * from cart where user_id=?");
			pst.setInt(1, getUser.getInt(4));
			ResultSet cart = pst.executeQuery();
			while (cart.next()) {
				cartStruct = new CartStruct();
				cartStruct.setProductId(cart.getInt(3));
				ResultSet product = productService.findProductById(cart.getInt(3));
				if (product.next()) {
					cartStruct.setProductName(product.getString(2));
					cartStruct.setProductPrice(product.getInt(4));
				}
				cartStruct.setProductQty(cart.getInt(4));
				cartStruct.setTotalPrice(cart.getInt(5));
				cartList.add(cartStruct);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cartList;
	}

}
