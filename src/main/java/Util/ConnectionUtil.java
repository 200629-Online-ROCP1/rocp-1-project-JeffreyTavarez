package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	public static Connection getConnection() throws SQLException {
		
		try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		String url = "jdbc:postgresql://localhost:5432/banking";
		String username = "postgres";
		String password = "79JeDaTaCr";
		
		
		return DriverManager.getConnection(url, username, password);
		
	}

// The following method tests the connection, runned as java application	
//	public static void main(String[] args) {
//		
//		//Try with resources will automatically close the resource at the end of the try or catch block
//		try(Connection conn = ConnectionUtil.getConnection()){
//			System.out.println("connection successful");
//		}catch(SQLException e) {
//			System.out.println(e);
//		}
//	}	
}





