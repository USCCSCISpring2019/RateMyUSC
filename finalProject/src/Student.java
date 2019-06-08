
public class Student extends Person{
	//private String password;
	private String major;
	
	public Student(String fname, String lname, String email, String major_) {
		super(fname, lname, email);
		major=major_;
	}
	
	void leaveReview() {
		//taken from doc
	}
	void updateReview() {
		//taken from doc
	}
	//not sure how to implement these two, i think even the following 
	//getter for the frontend to use make more sense:
	String getMajor() {
		return major;
	}
}
