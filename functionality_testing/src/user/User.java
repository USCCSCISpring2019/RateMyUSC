package user;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -406972693565647197L;
	private String name;
	private ArrayList<String> reviews;
	
	public User(String n) {
		name = n;
	}
	
	public User() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<String> getReviews(){
		return reviews;
	}
}
