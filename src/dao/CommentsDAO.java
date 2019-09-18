package dao;

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

public class CommentsDAO implements DAO<Comments> {
	private static final Logger log = Logger.getLogger(NewsDAO.class.getName());
	private PreparedStatement ps;
	private ResultSet rs;
	private Comments comments;	 

	@Override
	public Optional<Comments> get(int new_id) {
		try {
        	ps = DB.getPreparedStatement("select * from comments where new_id=?");
		    ps.setInt(1, new_id);
		    rs = ps.executeQuery();
            if (rs.next()) {
            	comments = new Comments();
            	comments.setId(rs.getInt("id"));
            	comments.setDate(rs.getString("date"));
            	comments.setDescription(rs.getString("description"));
            	comments.setNews_id(rs.getInt("news_id"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
        	log.log(Level.SEVERE, "Exception: ", ex);
        } catch (InstantiationException ex) {
        	log.log(Level.SEVERE, "Exception: ", ex);
		} catch (IllegalAccessException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		}
		return Optional.ofNullable(comments);
	}

	@Override
	public List<Comments> getAll() {
		List<Comments> list = new LinkedList<>();	 
		
        try {
            rs = DB.getPreparedStatement("select * from comments").executeQuery();
            while(rs.next()){
            	comments = new Comments(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                list.add(comments);
            }
        } catch (ClassNotFoundException | SQLException ex) {
        	log.log(Level.SEVERE, "Exception: ", ex);
        } catch (InstantiationException ex) {
        	log.log(Level.SEVERE, "Exception: ", ex);
		} catch (IllegalAccessException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		}
        
        return list;
	}

	@Override
	public void add(Comments c) {
		try {
	        ps = DB.getPreparedStatement("insert into comments(description, date, news_id) values(?,?,?)");
		    ps.setString(1, c.getDescription());
		    ps.setString(2, c.getDate());
		    ps.setInt(3, c.getNews_id());
		    ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} catch (InstantiationException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} catch (IllegalAccessException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} 
	}

	@Override
	public void update(Comments t) {
		try {
	        ps = DB.getPreparedStatement("update comments set description=?, date=? where id=?");
		    ps.setString(1, t.getDescription());
		    ps.setString(2, t.getDate());
		    ps.setInt(3, t.getId());
		    ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} catch (InstantiationException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} catch (IllegalAccessException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} 	
	}

	@Override
	public void delete(int id) {
		try {
	        ps = DB.getPreparedStatement("delete from comments where id=?");
		    ps.setInt(1, id);
		    ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} catch (InstantiationException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} catch (IllegalAccessException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		} 		
	}

}
