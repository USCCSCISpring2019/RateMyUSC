package project;

import java.io.Serializable;
import java.util.ArrayList;

public class User extends Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8070377995123945751L;
	private String name;
	private String major;
	private String password;
	
	public User(String n, String mr, String em){
		super(em);
		name = n;
		major = mr;
	}
	
	public User() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String s) {
		name = s;
	}
	
	public void setPassword(String s) {
		password = s;
	}
	
	public String getPassword(String s) {
		return password;
	}
	
	public String getMajor() {
		return major;
	}
	
	public void setMajor(String s) {
		major = s;
	}
}
