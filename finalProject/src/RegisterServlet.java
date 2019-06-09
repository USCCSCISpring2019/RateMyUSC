import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import com.mongodb.CommandResult;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String fname = null;
	private String lname = null;
	private String email = null;
	private String password = null;
	private String password_copy = null;
	private String major = null;
	private String errorMsg = null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		fname 			= request.getParameter("fname");
		lname 			= request.getParameter("lname");
		email 			= request.getParameter("email");
		password 		= request.getParameter("password");
		password_copy 	= request.getParameter("password_copy");
		major			= request.getParameter("major");
		
		if (password == null || password.trim().equals("")) {
			errorMsg = "Please enter a password";
			request.getRequestDispatcher("register.html").forward(request, response);
			return;
			
		}
		if (password_copy == null || password_copy.trim().equals("")) {
			errorMsg = "Please enter a password";
			request.getRequestDispatcher("register.html").forward(request, response);
			return;
		} 
		
		if (!password.equals(password_copy)) {
			errorMsg = "Passwords do not match";
			request.getRequestDispatcher("register.html").forward(request, response);
			return;
		}
		if (fname == null || fname.trim().equals("")) 
		{ 
			errorMsg = "Please enter a first name";
			request.getRequestDispatcher("register.html").forward(request, response);
			return;
		}
		if (lname == null || fname.trim().equals("")) {
			errorMsg = "Please enter a last name";
			request.getRequestDispatcher("register.html").forward(request, response);
			return;
		}
		if (email != null) {
			String[] tokens = email.split("@");
			if ((tokens.length != 2) ||  (tokens[0].trim().equals(""))|| (!tokens[1].equalsIgnoreCase("usc.edu"))) { 
				errorMsg = "Please enter a valid USC email address";
				request.getRequestDispatcher("register.html").forward(request, response);
				return;
			}
		}
		else {
			errorMsg = "Please enter a USC email address";
			request.getRequestDispatcher("register.html").forward(request, response);
			return;
		}
		
		if (major == null || major.trim().equals("")) {
			errorMsg = "Please enter your major";
			request.getRequestDispatcher("register.html").forward(request, response);
			return;
		
		}
		try {
			MongoClient client = new MongoClient("localhost", 27017);
			//String connectPoint = client.getConnectPoint();
			System.out.println("Server connection successful");
			MongoDatabase dbs = client.getDatabase("RMUSC");
			
			System.out.println("Connected to database successful");
			System.out.println("Database: " + dbs.getName());
			
			
			// Inserting into the collection
			MongoCollection<Document> collection = dbs.getCollection("testuser");
			System.out.println(collection.count());
			Document doc = collection.find(eq("email",email.toLowerCase())).first();
			if (doc == null) {
				// Create this new user
				doc = new Document("_id", (int)collection.count())
						.append("fname", fname)
						.append("lname", lname)
						.append("email", email.toLowerCase())
						.append("password", password)
						.append("major", major);
				collection.insertOne(doc);
				System.out.println("Inserted one document");
			} else {
				System.out.println("Email already exists");
				// email already exists
				// send it back to registration form
			}	
			client.close();
		} catch (Exception e) {
			System.out.println("Error in DB");
		}	
	}
	
}
/*
public class RegisterServlet {
	public static void main(String[] args) throws Exception {
		
		
		// Assume we just got all the parameters here
		// Make sure they are all good
		String fname = "Tommy";
		String lname = "Trojan";
		String email = "TommyTrojan@usc.edu".toLowerCase();
		String password = "abcd1234";
		String major = "CSCI";
		
		
		if (fname == null || fname.trim().equals("")) { badForm();}
		if (lname == null || fname.trim().equals("")) { badForm(); }
		if (email != null) {
			String[] tokens = email.split("@");
			if (tokens.length != 2) { badForm(); }
			if (tokens[0].trim().equals("")) { badForm(); }
			if (!tokens[1].equalsIgnoreCase("usc.edu")) {badForm();}
		}
		else {
			badForm();
		}
		if (password == null || password.trim().equals("")) badForm();
		if (major == null || major.trim().equals("")) badForm();
		
		
		try {
			MongoClient client = new MongoClient("localhost", 27017);
			//String connectPoint = client.getConnectPoint();
			System.out.println("Server connection successful");
			
			MongoDatabase dbs = client.getDatabase("RMUSC");
			System.out.println("Connected to database successful");
			System.out.println("Database: " + dbs.getName());
			
			// Inserting into the collection
			MongoCollection<Document> collection = dbs.getCollection("testuser");
			Document doc = collection.find(eq("email",email)).first();
			if (doc == null) {
				// Create this new user
				doc = new Document("fname", fname)
						.append("lname", lname)
						.append("email", email)
						.append("password", password)
						.append("major", major);
				collection.insertOne(doc);
				System.out.println("Inserted one document");
			} else {
				System.out.println("Email already exists");
				// email already exists
				// send it back to registration form
			}	
			client.close();
		} catch (Exception e) {
			System.out.println("Error in DB");
		}	
	}
	
		// send it back to registration form
		System.out.println(" BAD FORM");
	}
	
	
}

*/