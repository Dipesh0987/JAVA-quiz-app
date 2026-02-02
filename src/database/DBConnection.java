package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {
	public static void main(String[] args) {
		String url ="jdbc:mysql://localhost/registration";
		String username = "root";
		String password = "";
		try(Connection conn = DriverManager.getConnection(url, username, password);
				Statement stm = conn.createStatement();){
			System.out.println("Database connection successful");
		}catch(Exception e) {
			
		}
	}
}
