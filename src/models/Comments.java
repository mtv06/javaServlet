package models;

public class Comments {
	
	private int id;
	private String description;
	private String date;
	private int news_id;
	
	public Comments() {
	}
	   	 
    public Comments(int id, String description, String date, int news_id) {
    	super();
        this.id = id;
        this.description = description;
        this.date = date;
        this.news_id = news_id;
    }
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getNews_id() {
		return news_id;
	}
	public void setNews_id(int news_id) {
		this.news_id = news_id;
	}
}
