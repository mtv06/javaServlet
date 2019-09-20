package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import db.DB;
import models.Comments;
import models.News;

public class NewsDAO implements IDAO<News> {
	private static final Logger log = Logger.getLogger(NewsDAO.class.getName());
	private Connection connection; 
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private ResultSet resultSet2;
	private News news;
	private Comments comments;
	private Collection<Comments> collection;
	 
	@Override
	public Optional<News> get(int id) {
        try {
        	connection = DB.getConnection();
        	preparedStatement = connection.prepareStatement("select * from news where id=?");
        	preparedStatement.setInt(1, id);
        	resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	collection = new ArrayList<Comments>();
            	resultSet2 = connection.prepareStatement("select * from comments where news_id=" + resultSet.getInt("id")).executeQuery();
             	while(resultSet2.next()) {
            		comments = new Comments(resultSet2.getInt(1), resultSet2.getString(2), resultSet2.getString(3), resultSet2.getInt(4));
            		collection.add(comments);
            	}
            	news = new News();
                news.setId(resultSet.getInt("id"));
                news.setTitle(resultSet.getString("title"));
                news.setDate(resultSet.getString("date"));
                news.setDescription(resultSet.getString("description"));
                news.setComments(collection);
            }
        } catch (ClassNotFoundException | SQLException ex) {
        	log.log(Level.SEVERE, "Exception: ", ex);
        } catch (InstantiationException ex) {
        	log.log(Level.SEVERE, "Exception: ", ex);
		} catch (IllegalAccessException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} finally {
			if (resultSet != null) 
	          try { 
	        	  resultSet.close(); 
	          } catch (SQLException ignore) { }
			if (resultSet2 != null) 
	          try { 
	        	  resultSet2.close(); 
	          } catch (SQLException ignore) { }
	        if (preparedStatement != null) 
	          try { 
	        	  preparedStatement.close(); 
	          } catch (SQLException ignore) { }
		 	if (connection != null)
		      try { 
		    	  connection.close(); 
		      } catch (SQLException ignore) { }
		}
		return Optional.ofNullable(news);
	}

	@Override
	public List<News> getAll() {
		List<News> list = new LinkedList<>();	 
		
        try {
        	connection = DB.getConnection();
        	resultSet = connection.prepareStatement("select * from news").executeQuery();
            while(resultSet.next()){
            	collection = new ArrayList<Comments>();
            	resultSet2 = connection.prepareStatement("select * from comments where news_id=" + resultSet.getInt(1)).executeQuery();
             	while(resultSet2.next()) {
            		comments = new Comments(resultSet2.getInt(1), resultSet2.getString(2), resultSet2.getString(3), resultSet2.getInt(4));
            		collection.add(comments);
            	}
                news = new News(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), collection);
                list.add(news);
            }
        } catch (ClassNotFoundException | SQLException ex) {
        	log.log(Level.SEVERE, "Exception: ", ex);
        } catch (InstantiationException ex) {
        	log.log(Level.SEVERE, "Exception: ", ex);
		} catch (IllegalAccessException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} finally {
			if (resultSet != null) 
	          try { 
	        	  resultSet.close(); 
	          } catch (SQLException ignore) { }
			if (resultSet2 != null) 
	          try { 
	        	  resultSet2.close(); 
	          } catch (SQLException ignore) { }
	        if (preparedStatement != null) 
	          try { 
	        	  preparedStatement.close(); 
	          } catch (SQLException ignore) { }
		 	if (connection != null)
		      try { 
		    	  connection.close(); 
		      } catch (SQLException ignore) { }
		}
        
        return list;
	}

	@Override
	public void add(News n) {
		try {
			connection = DB.getConnection();
			preparedStatement = connection.prepareStatement("insert into news(title, description, date) values(?,?,?)");
			preparedStatement.setString(1, n.getTitle());
			preparedStatement.setString(2, n.getDescription());
			preparedStatement.setString(3, n.getDate());
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} catch (InstantiationException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} catch (IllegalAccessException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} finally {
	        if (preparedStatement != null) 
	          try { 
	        	  preparedStatement.close(); 
	          } catch (SQLException ignore) { }
		 	if (connection != null)
		      try { 
		    	  connection.close(); 
		      } catch (SQLException ignore) { }
		}
	}

	@Override
	public void update(News n) {
		try {
			connection = DB.getConnection();
			preparedStatement = connection.prepareStatement("update news set title=?, date=?, description=? where id=?");
			preparedStatement.setString(1, n.getTitle());
			preparedStatement.setString(2, n.getDate());
			preparedStatement.setString(3, n.getDescription());
			preparedStatement.setInt(4, n.getId());
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} catch (InstantiationException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} catch (IllegalAccessException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} finally {
	        if (preparedStatement != null) 
	          try { 
	        	  preparedStatement.close(); 
	          } catch (SQLException ignore) { }
		 	if (connection != null)
		      try { 
		    	  connection.close(); 
		      } catch (SQLException ignore) { }
		} 		
	}

	@Override
	public void delete(int id) {
		try {
			connection = DB.getConnection();
			preparedStatement = connection.prepareStatement("delete from news where id=?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} catch (InstantiationException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} catch (IllegalAccessException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} finally {
			if (resultSet != null) 
	          try { 
	        	  resultSet.close(); 
	          } catch (SQLException ignore) { }
	        if (preparedStatement != null) 
	          try { 
	        	  preparedStatement.close(); 
	          } catch (SQLException ignore) { }
		 	if (connection != null)
		      try { 
		    	  connection.close(); 
		      } catch (SQLException ignore) { }
		} 				
	}

}
