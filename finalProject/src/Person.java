package project;

import java.io.Serializable;

public class Person implements Serializable{

	private String email;
	
	public Person(String email_) {
		email=email_;
	}
	
	public Person() {
		
	}
	
	public String getEmail() {
		return email;
	}
}