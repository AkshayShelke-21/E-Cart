package com.entities;

public class Order {
	private int orderid;
	private int userid;
	private int productid;
	private int productqty;
	private int orderprice;

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public int getProductqty() {
		return productqty;
	}

	public void setProductqty(int productqty) {
		this.productqty = productqty;
	}

	public int getOrderprice() {
		return orderprice;
	}

	public void setOrderprice(int orderprice) {
		this.orderprice = orderprice;
	}

	public Order(int orderid, int userid, int productid, int productqty, int orderprice) {
		super();
		this.orderid = orderid;
		this.userid = userid;
		this.productid = productid;
		this.productqty = productqty;
		this.orderprice = orderprice;
	}

}
