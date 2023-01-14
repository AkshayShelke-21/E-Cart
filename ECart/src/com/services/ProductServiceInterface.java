package com.services;
import java.util.List;

import com.entities.Product;

public interface ProductServiceInterface {
	
	//Function to add Products
	public int addProducts(Product product, String token);

	//Function to retrieve all products
	public List<Product> fetchAllProduct();

	//Function to update stock of products
	public int updataStockOfProudct(String token, int productId, int productQty);
}
