package com.entities;

public class Product {
	private int id;
	 private String productName;
	 private String productDiscreption;
	 private float productPrice;
	 private int productQty;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDiscreption() {
		return productDiscreption;
	}
	public void setProductDiscreption(String productDiscreption) {
		this.productDiscreption = productDiscreption;
	}
	public float getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductQty() {
		return productQty;
	}
	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}
	public Product(int id, String productName, String productDiscreption, float productPrice, float productQty) {
		super();
		this.id = id;
		this.productName = productName;
		this.productDiscreption = productDiscreption;
		this.productPrice = productPrice;
		this.productQty = productQty;
	}
}
