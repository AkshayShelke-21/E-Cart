package com.mainhandler;

import java.util.Scanner;

import com.connection.Connections;
import com.entities.Product;
import com.entities.User;
import com.messages.MessageProperties;
import com.services.ProductServiceImpl;
import com.services.ProductServiceInterface;
import com.services.UserServiceImpl;
import com.services.UserServiceInterface;
import com.utilities.ApplicationUtilities;

public class ECartApplicationHandler {
	
	public static void run() throws ClassNotFoundException{
		
		UserServiceInterface userService=new UserServiceImpl(Connections.getConnect());
		ProductServiceInterface productService=new ProductServiceImpl(Connections.getConnect());
		Scanner sc=new Scanner(System.in);
		String query=null;
		query=ApplicationUtilities.login(query, sc);
		if(query==null) {
			System.out.println("Do you want to register with us, Please enter 1 for YES or 0 for NO .");
			int response=sc.nextInt();
			if(response==1) {
				User user=new User();
				System.out.println("Please enter your UserName :");
				String username=sc.next();
				System.out.println("Please enter your Password");
				String password=sc.next();
				user.setUsername(username);
				user.setPassword(password);
				userService.UserRegistration(user);
				query=ApplicationUtilities.login(query, sc);
				ApplicationUtilities.operation(query, sc);
				
			}
			else {
				System.out.println(MessageProperties.VISITE_AGAIN);
				
			}
			
		}
		else {

			Boolean running=true;
			while(running) {
				System.out.println("*****************Product Details**********************************");
				ApplicationUtilities.allProducts();
				System.out.println("Select Option from following : ");
				System.out.println("To know all Users : 1");
				System.out.println("To check order history of user : 2");
				System.out.println("To add products: 3");
				System.out.println("To Update Stock of Product : 4");
				System.out.println("To Purchase Product : 5");
				System.out.println("To Logout : 6");
				int option = sc.nextInt();
				switch (option) {
				
				
				case 1:
					System.out.println("******************************Users Details******************************");
					ApplicationUtilities.AllUser(query);
					break;
				case 2:
					System.out.println("******************************Users Details******************************");
					ApplicationUtilities.AllUser(query);
					System.out.println("Please Select Id of User from above to check Order History: ");
					int id = sc.nextInt();
					ApplicationUtilities.orderHistoryOfUser(id,query);
					break;
				case 3:
					Product pr = new Product();
					System.out.println("Please Enter Product Name: ");
					String pName = sc.next();
					pr.setProductName(pName);
					System.out.println("Please set Product price :");
					int pPrice = sc.nextInt();
					pr.setProductPrice(pPrice);
					System.out.println("Please Enter Product Description : ");
					String desc = sc.next();
					pr.setProductDiscreption(desc);
					System.out.println("Please Set Qty Of prodcut : ");
					int qty = sc.nextInt();
					pr.setProductQty(qty);
					productService.addProducts(pr, query);
					break;
				case 4:
					System.out.println("*******************************Product Details******************************************");
					ApplicationUtilities.allProducts();
					System.out.println("Select ProductId form above proudct list which you want to update.");
					int productId = sc.nextInt();
					System.out.println("Enter Product qty to update. :");
					int productQty = sc.nextInt();
					int updateStockOfProduct = productService.updataStockOfProudct(query, productId, productQty);
					if (updateStockOfProduct == 1) {
						System.out.println(MessageProperties.UPDATED_PRODUCT_STOCK.getMessage());
					} else {
						System.out.println(MessageProperties.INTERNAL_ERROER.getMessage());
					}
					break;
				case 5:
					ApplicationUtilities.operation(query, sc);
					break;
				case 6:
					running = false;
					break;
				default:
					System.out.println("Please Select correct option!");
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		run();
		
	}

}
