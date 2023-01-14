package com.services;

import java.sql.Connection;
import java.util.List;

import com.entities.Product;

public class ProductServiceImpl implements ProductServiceInterface {

	private Connection con;
	
	public ProductServiceImpl(Connection con) {
		this.con = con;
	}
	
	@Override
	public int addProducts(Product product, String token) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Product> fetchAllProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updataStockOfProudct(String token, int productId, int productQty) {
		// TODO Auto-generated method stub
		return 0;
	}

}
