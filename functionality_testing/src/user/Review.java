package user;

import java.io.Serializable;

public class Review implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3209415621839227923L;
	int id;
	String username;
	String content;
	
	public Review() {
		
	}
	
	public Review(int id, String username, String content) {
		this.id = id;
		this.username = username;
		this.content = content;
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
}
