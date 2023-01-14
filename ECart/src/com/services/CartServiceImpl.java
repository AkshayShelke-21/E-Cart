package com.services;

import java.sql.Connection;
<<<<<<< HEAD
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
=======
import java.util.List;

import com.entities.Cart;
import com.struct.CartStruct;

public class CartServiceImpl implements CartServiceInterface{
	
	
	private Connection con;

	public CartServiceImpl(Connection con) {
		this.con = con;
	}
	
	@Override
	public int increaseOtyAndPriceInCartWhenProductIsAddedInCart(String token, Cart cart, int cartId) {
>>>>>>> 7feeeb385439fdb06d326dc720e71082e8ae6408
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
<<<<<<< HEAD
	public int addToCart(String querry, Cart cart) {
=======
	public int addToCart(String token, Cart cart) {
>>>>>>> 7feeeb385439fdb06d326dc720e71082e8ae6408
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
<<<<<<< HEAD
	public List<CartStruct> getCartDetails(String querry) {
=======
	public List<CartStruct> getCartDetails(String token) {
>>>>>>> 7feeeb385439fdb06d326dc720e71082e8ae6408
		// TODO Auto-generated method stub
		return null;
	}

}
