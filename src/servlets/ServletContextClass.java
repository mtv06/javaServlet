package servlets;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import db.BaseTable;
import db.DB;

public class ServletContextClass implements ServletContextListener {
	private static final Logger log = Logger.getLogger(CommentsServlet.class.getName());
	public static Connection connection;

    public void contextInitialized(ServletContextEvent arg0) 
    {
    	try {
    		connection = DB.getConnection();
    		BaseTable.createTableNews(connection);
    		BaseTable.createTableComments(connection);
		} catch (ClassNotFoundException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} catch (InstantiationException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} catch (IllegalAccessException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} catch (SQLException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} 
    }

    public void contextDestroyed(ServletContextEvent arg0) 
    {
    	try {
			connection.close ();
		} catch (SQLException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		}       
    }

}
