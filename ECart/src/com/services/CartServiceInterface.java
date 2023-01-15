package com.services;

import java.util.List;

import com.entities.Cart;
import com.struct.CartStruct;

public interface CartServiceInterface {
	

	int increasePriceAndQtyWhenProductIsAdded(String querry, Cart cart, int cartId);

	int addToCart(String querry, Cart cart);

	List<CartStruct> getCartDetails(String querry);

}
