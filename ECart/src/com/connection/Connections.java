package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {
	static Connection connection=null;
	
	public static Connection getConnect() throws ClassNotFoundException{
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
		    connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/E-Commers", "root", "root");
		}catch(SQLException e) {
			e.getStackTrace();
		}
		return connection;
	}
}
