package com.connection;

import java.sql.DriverManager;

public class Connection {
	Connection connection=new Connection();
	
	public Connection getConnect(){
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			DriverManager.getConnection("jdbc:mysql://localhost:3306/E-Commers", "root", "root");
		}catch(Exception e) {
			e.getStackTrace();
		}
		return connection;
	}
}
