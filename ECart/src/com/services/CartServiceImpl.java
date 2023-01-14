package com.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.struct.CartStruct;
import com.entities.Cart;
import com.struct.CartStruct;

public class CartServiceImpl implements CartServiceInterface {

	private Connection con;

	public CartServiceImpl(Connection con) {
		super();
		this.con = con;
	}

	public ResultSet findCartById(int cart_id) {
		

		ResultSet getCart = null;

		try {

			PreparedStatement ps = con.prepareStatement("SELECT * FROM Cart WHERE Cartid=?");
			ps.setInt(1, cartId);
			getCart = ps.executeQuery();
			
		} catch (SQLException e) {
			
			e.getStackTrace();
		}
		
		return getCart;
	}
	
	public ResultSet FindCartByProductId(int product_id) {
		
	}
	
	public 

	@Override
	public int increaseOtyAndPriceInCartWhenProductIsAddedInCart(String querry, Cart cart, int cartId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addToCart(String querry, Cart cart) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CartStruct> getCartDetails(String querry) {
		// TODO Auto-generated method stub
		return null;
	}

}
