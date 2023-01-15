package com.services;

import java.sql.ResultSet;
import java.util.List;

import com.entities.Cart;
import com.entities.Order;
import com.struct.CartStruct;

public interface CartServiceInterface {
	
	//Function to change Price and Quantity in Cart wwhen product is added.
	int increasePriceAndQtyWhenProductIsAdded(String querry, Cart cart, int cartId);

	//Function to add to Cart.
	int addToCart(String querry, Cart cart);

	//Function to retrieve Cart details.
	List<CartStruct> getCartDetails(String querry);

	//Function to decrease Quantity in Cart.
	public int decreaseQtyInCart(String token, ResultSet getCart, Order order);
}
