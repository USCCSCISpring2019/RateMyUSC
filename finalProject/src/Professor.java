
public class Professor extends Person{
	String dept;
	double avgRating;
	
	public Professor(String fname, String lname, String email, 
					 String dept_, double avgRating_) {
		super(fname, lname, email);
		dept=dept_;
		avgRating=avgRating_;
	}
	
	double getAverageRating() {
		return avgRating;
	}
	String getdept() {
		return dept;
	}
}
