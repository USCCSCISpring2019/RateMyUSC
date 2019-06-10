package project;

import java.io.Serializable;

public class Review implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7422026579896230985L;
	int id;
	String username;
	String content;
	double rating;
	String prof;
	
	public Review() {
		
	}
	
	public Review(int id, String username, String content, double rating, String profname) {
		this.id = id;
		this.username = username;
		this.content = content;
		this.rating = rating;
		this.prof = profname;
	}
	
	public int getID() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getContent() {
		return content;
	}
	
	public double getRating() {
		return rating;
	}
	
	public String getProf() {
		return prof;
	}
	
}
