package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import db.DB;
import models.Comments;

public class CommentsDAO implements IDAO<Comments> {
	private static final Logger log = Logger.getLogger(NewsDAO.class.getName());
	private Connection connection; 
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private Comments comments;	 

	@Override
	public Optional<Comments> get(int id) {
		try {
			connection = DB.getConnection();
	        preparedStatement = connection.prepareStatement("select * from comments where id=?");      
	        preparedStatement.setInt(1, id);
	        resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
            	comments = new Comments();
            	comments.setId(resultSet.getInt("id"));
            	comments.setDate(resultSet.getString("date"));
            	comments.setDescription(resultSet.getString("description"));
            	comments.setNews_id(resultSet.getInt("news_id"));
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
	        if (preparedStatement != null) 
	          try { 
	        	  preparedStatement.close(); 
	          } catch (SQLException ignore) { }
		 	if (connection != null)
		      try { 
		    	  connection.close(); 
		      } catch (SQLException ignore) { }
		}
		return Optional.ofNullable(comments);
	}

	@Override
	public List<Comments> getAll() {
		List<Comments> list = new LinkedList<>();	 
		
        try {
        	connection = DB.getConnection();
        	resultSet = connection.prepareStatement("select * from comments").executeQuery();
            while(resultSet.next()){
            	comments = new Comments(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
                list.add(comments);
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
	public void add(Comments c) {
		try {
			connection = DB.getConnection();
			preparedStatement = connection.prepareStatement("insert into comments(description, date, news_id) values(?,?,?)");
			preparedStatement.setString(1, c.getDescription());
			preparedStatement.setString(2, c.getDate());
			preparedStatement.setInt(3, c.getNews_id());
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

	@Override
	public void update(Comments t) {
		try {
			connection = DB.getConnection();
			preparedStatement = connection.prepareStatement("update comments set description=?, date=? where id=?");
			preparedStatement.setString(1, t.getDescription());
			preparedStatement.setString(2, t.getDate());
			preparedStatement.setInt(3, t.getId());
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

	@Override
	public void delete(int id) {
		try {
			connection = DB.getConnection();
			preparedStatement = connection.prepareStatement("delete from comments where id=?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} catch (InstantiationException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} catch (IllegalAccessException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		}  finally {
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
