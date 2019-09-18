package db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseTable {
	public static Statement statement;
	public static String sql;
	public static boolean check;
	
	public static void createTableNews(Connection connection) throws SQLException {
		check = checkTableDB(connection, "NEWS");
		if (check == false) {
			statement = connection.createStatement();
			sql = "CREATE TABLE NEWS (" + 
					"    id INT NOT NULL AUTO_INCREMENT," + 
					"    title VARCHAR(255) NOT NULL," + 
					"	 description TEXT NOT NULL," + 
					"	 date DATETIME," + 
					"    PRIMARY KEY (id)" + 
					")";
			statement.execute(sql);
			statement.close();
		}
	}
	
	public static void createTableComments(Connection connection) throws SQLException {
		check = checkTableDB(connection, "COMMENTS");
		if (check == false) {
			statement = connection.createStatement();
			sql = "CREATE TABLE COMMENTS (" + 
					"    id INT NOT NULL AUTO_INCREMENT," + 
					"    description TEXT NOT NULL," + 
					"	 date DATETIME," + 
					"    news_id INT UNSIGNED," + 
					"    PRIMARY KEY (id)," + 
					"    FOREIGN KEY (news_id) REFERENCES news (id)" + 
					"        ON DELETE CASCADE" + 
					"        ON UPDATE CASCADE" + 
					")";
			statement.execute(sql);
			statement.close();
		}
	}
	
	public static boolean checkTableDB(Connection connection, String nameTable) throws SQLException {
		 DatabaseMetaData metadata = connection.getMetaData();
		 ResultSet resultSet;
		 resultSet = metadata.getTables(null, null, nameTable, null);
		 if(resultSet.next()) {
		   return true;
		 }
		 return false;
	}
}
