package models;

import java.util.Collection;

public class News {
	
	private int id;
	private String title;
	private String description;
    private String date;
    private Collection<Comments> comments;
    
    public News() {
    }
    
    public News(int id, String title, String description, String date, Collection<Comments> comments) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = date;
		this.comments = comments;
	}
    
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<Comments> getComments() {
		return comments;
	}

	public void setComments(Collection<Comments> comments) {
		this.comments = comments;
	}
    
}
