package com.services;

import java.sql.Connection;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addToCart(String token, Cart cart) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CartStruct> getCartDetails(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
