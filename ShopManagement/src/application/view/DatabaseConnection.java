package application.view;

import java.sql.*;
public class DatabaseConnection {
	
	public static Connection checkConnection()
	{
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:shop.db");
			
			return conn;
		}
		catch(Exception ex)
		{
			
			System.out.println(ex);
			return null;
		}
		
		
	}
}
