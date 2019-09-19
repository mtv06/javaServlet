package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DB {
	static final String JDBC_DRIVER = "org.h2.Driver";   
	static final String DB_URL = "jdbc:h2:~/test";  
    static final String USER = "sa"; 
    static final String PASS = ""; 
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		Class.forName(JDBC_DRIVER).newInstance(); 
		Connection connection = DriverManager.getConnection(DB_URL,USER,PASS);
		return connection;
	}
	
}
