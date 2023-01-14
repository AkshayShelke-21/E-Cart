package com.services;

import java.util.List;

import com.entities.Cart;
import com.struct.CartStruct;

public interface CartServiceInterface {
	

	int increaseOtyAndPriceInCartWhenProductIsAddedInCart(String token, Cart cart, int cartId);

	int addToCart(String token, Cart cart);

	List<CartStruct> getCartDetails(String token);

}
