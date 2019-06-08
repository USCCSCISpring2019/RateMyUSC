
public class Person {
	private String fname;
	private String lname;
	private String email;
	
	public Person(String fname_, String lname_, String email_) {
		fname=fname_;
		lname=lname_;
		email=email_;
	}
	
	public String getName() {
		return fname+" "+lname;
	}
	public String getEmail() {
		return email;
	}
}