package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import db.DB;
import models.Comments;
import models.News;

public class NewsDAO implements DAO<News> {
	private static final Logger log = Logger.getLogger(NewsDAO.class.getName());
	private PreparedStatement ps;
	private ResultSet rs;
	private ResultSet rs2;
	private News news;
	private Comments comments;
	private Collection<Comments> collection;
	 
	@Override
	public Optional<News> get(int id) {
        try {
        	ps = DB.getPreparedStatement("select * from news where id=?");
		    ps.setInt(1, id);
		    rs = ps.executeQuery();
            if (rs.next()) {
            	collection = new ArrayList<Comments>();
            	rs2 = DB.getPreparedStatement("select * from comments where news_id=" + rs.getInt("id")).executeQuery();
             	while(rs2.next()) {
            		comments = new Comments(rs2.getInt(1), rs2.getString(2), rs2.getString(3), rs2.getInt(4));
            		collection.add(comments);
            	}
            	news = new News();
                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setDate(rs.getString("date"));
                news.setDescription(rs.getString("description"));
                news.setComments(collection);
            }
        } catch (ClassNotFoundException | SQLException ex) {
        	log.log(Level.SEVERE, "Exception: ", ex);
        } catch (InstantiationException ex) {
        	log.log(Level.SEVERE, "Exception: ", ex);
		} catch (IllegalAccessException ex) {
			log.log(Level.SEVERE, "Exception: ", ex);
		}
		return Optional.ofNullable(news);
	}

	@Override
	public List<News> getAll() {
		List<News> list = new LinkedList<>();	 
		
        try {
            rs = DB.getPreparedStatement("select * from news").executeQuery();
            while(rs.next()){
            	collection = new ArrayList<Comments>();
            	rs2 = DB.getPreparedStatement("select * from comments where news_id=" + rs.getInt(1)).executeQuery();
             	while(rs2.next()) {
            		comments = new Comments(rs2.getInt(1), rs2.getString(2), rs2.getString(3), rs2.getInt(4));
            		collection.add(comments);
            	}
                news = new News(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), collection);
                list.add(news);
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
	public void add(News t) {
		try {
	        ps = DB.getPreparedStatement("insert into news(title, description, date) values(?,?,?)");
		    ps.setString(1, t.getTitle());
		    ps.setString(2, t.getDescription());
		    ps.setString(3, t.getDate());
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
	public void update(News t) {
		try {
	        ps = DB.getPreparedStatement("update news set title=?, date=?, description=? where id=?");
		    ps.setString(1, t.getTitle());
		    ps.setString(2, t.getDate());
		    ps.setString(3, t.getDescription());
		    ps.setInt(4, t.getId());
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
	        ps = DB.getPreparedStatement("delete from news where id=?");
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
