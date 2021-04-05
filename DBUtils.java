package utils;

import java.sql.*;

public class DBUtils {
	// add static method to get FIXED DB connection from D.M
	public static Connection fetchDBConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/test_acts?useSSL=false", 
				"root", "test");
	}

}
