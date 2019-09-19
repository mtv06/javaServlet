package servlets;

import java.sql.Connection;
import java.sql.SQLException;
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
    		connection.close();
		} catch (ClassNotFoundException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} catch (InstantiationException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} catch (IllegalAccessException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} catch (SQLException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} finally {
			if (connection != null)
		      try { 
		    	  connection.close(); 
		      } catch (SQLException ignore) { }
		}
    }

    public void contextDestroyed(ServletContextEvent arg0) 
    {
    	try {
    		if (connection != null) {
    			connection.close ();
    		}
		} catch (SQLException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		}       
    }

}
