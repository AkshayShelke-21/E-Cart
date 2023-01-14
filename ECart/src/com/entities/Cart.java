package com.entities;

public class Cart {
	
	private int cartId;
	private int usreId;
	private int productId;
	private int productQty;
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getUsreId() {
		return usreId;
	}
	public void setUsreId(int usreId) {
		this.usreId = usreId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getProductQty() {
		return productQty;
	}
	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}
	public Cart(int cartId, int usreId, int productId, int productQty) {
		super();
		this.cartId = cartId;
		this.usreId = usreId;
		this.productId = productId;
		this.productQty = productQty;
	}
	public Cart() {
		// TODO Auto-generated constructor stub
	}
	
	

}
