package com.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entities.Product;
import com.exceptions.EcartExceptions;
import com.messages.MessageProperties;


//Class to implement methods in ProductServiceInterface and more.
public class ProductServiceImpl implements ProductServiceInterface {

	private Connection con;
	
	public ProductServiceImpl(Connection con) {
		this.con = con;
	}
	
	//Function to find Product by Product_Id. 
	public ResultSet findProductById(int productId) {
		PreparedStatement st =null;
		ResultSet getProduct = null;
		try {
			st = con.prepareStatement("select * from product where product_id=?");
			st.setInt(1, productId);
			getProduct = st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getProduct;
	}
	
	//Function to Add new product
	@Override
	public int addProducts(Product product, String queryToken) {
		// TODO Auto-generated method stub
		int count = 0;
		PreparedStatement pst = null;
		UserServiceImpl userService = new UserServiceImpl(con);
		try {
			ResultSet getUser = userService.findByUsername(queryToken);
			boolean isLoggedIn = getUser.next();
			if (!isLoggedIn) {
				throw new EcartExceptions(MessageProperties.PLEASE_LOGIN.getMessage());
			}
			boolean isAdmin = getUser.getBoolean(3);
			if (!isAdmin) {
				throw new EcartExceptions(MessageProperties.GET_PERMISSION.getMessage(),
						EcartExceptions.ExceptionType.Unauthorised_User);
			}
			pst = con.prepareStatement("Insert into product(product_name, product_description,product_price, product_qty) values(?,?,?,?)");
			pst.setString(1, product.getProductName());
			pst.setString(2, product.getProductDiscreption());
			pst.setFloat(3, product.getProductPrice());
			pst.setInt(4, product.getProductQty());
			count = pst.executeUpdate();
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	
	//Function to fetch all products from Database.
	@Override
	public List<Product> fetchAllProduct() {
		// TODO Auto-generated method stub
		List<Product> productList = new ArrayList<>();
		PreparedStatement pst = null;
		Product product = null;
		try {
			pst = con.prepareStatement("select * from product");
			ResultSet resultSet = pst.executeQuery();
			while (resultSet.next()) {
				product = new Product();
				product.setId(resultSet.getInt(1));
				product.setProductName(resultSet.getString(2));
				product.setProductDiscreption(resultSet.getString(3));
				product.setProductPrice(resultSet.getInt(4));
				product.setProductQty(resultSet.getInt(5));
				productList.add(product);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return productList;
	}

	//Function to Update the stock of products.
	@Override
	public int updataStockOfProudct(String queryToken, int productId, int productQty) {
		// TODO Auto-generated method stub
		UserServiceImpl userService = new UserServiceImpl(con);
		ProductServiceImpl productService = new ProductServiceImpl(con);
		int updatedStock =0;
		PreparedStatement stat = null;
		ResultSet product = null;
		ResultSet user = userService.findByUsername(queryToken);
		try {
			if(!user.next()) {
				throw new EcartExceptions(MessageProperties.PLEASE_LOGIN.getMessage());
			}
			boolean isAdmin = user.getBoolean(3);
			if(!isAdmin) {
				throw new EcartExceptions(MessageProperties.GET_PERMISSION.getMessage());
			}
			product = productService.findProductById(productId);
			if(!product.next()) {
				throw new EcartExceptions(MessageProperties.PRODUCT_NOT_FOUND.getMessage());
			}
			stat = con.prepareStatement("update product set product_qty=? where product_id=?");
			stat.setInt(1, productQty);
			stat.setInt(2, productId);
			updatedStock=stat.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return updatedStock;
	}

}
