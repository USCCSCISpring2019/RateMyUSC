package project;
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
import javax.servlet.http.HttpSession;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String username = null;
	private String email = null;
	private String password = null;
	private String password_copy = null;
	private String major = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean pingDB = true;
		username		= request.getParameter("username").toLowerCase();
		email 			= request.getParameter("email").toLowerCase();
		password 		= request.getParameter("password");
		password_copy 	= request.getParameter("password_copy");
		major			= request.getParameter("major");
		
		if (password == null || password.equals("")) {
			System.out.println("password is null");
			response.getWriter().append("Password cannot be null");
			pingDB = false;
		}
		else if (password_copy == null || password_copy.equals("")) {
			System.out.println("Password copy is null");
			response.getWriter().append("Passwords do not match");
			pingDB = false;
		} 
		
		else if (!password.equals(password_copy)) {
			System.out.println("Password copy is null");
			response.getWriter().append("Passwords do not match");
			pingDB = false;
		}
		else if (email == null) {
			System.out.println("Email is null");
			response.getWriter().append("Please enter a valid USC email address");
			pingDB = false;
		}
		else if (email != null) {
			String[] tokens = email.split("@");
			if ((tokens.length != 2) ||  (tokens[0].trim().equals(""))|| (!tokens[1].equalsIgnoreCase("usc.edu"))) { 
				System.out.println("Email is not valid");
				response.getWriter().append("Please enter a valid USC email address");
				pingDB = false;
			}
		}
		else if (username == null || username.trim().equals("")) {
			System.out.println("Username is nulll");
			response.getWriter().append("Please enter a username");
			pingDB = false;
		}
		else if (major == null || major.trim().equals("")) {
			System.out.println("Major not entered");
			response.getWriter().append("Please enter a valid USC email address");
			pingDB = false;
		}
		
		if (pingDB) {
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
				
				
				
				if (doc != null) {
					System.out.println("Email already exists");
					response.getWriter().append("That email address is already taken.");
				} else {
					doc = collection.find(eq("username", username.toLowerCase())).first();
					if (doc != null) {
						System.out.println("Username already exists");
						response.getWriter().append("That email address is already taken.");
					}
					else {
						// Set session user
						HttpSession session = request.getSession();
						User user = new User(username, major, email);
						session.setAttribute("user", user);
						
						// Create this new user
						doc = new Document("_id", (int)collection.count())
								.append("username", username)
								.append("email", email.toLowerCase())
								.append("password", password)
								.append("major", major);
						collection.insertOne(doc);
						System.out.println("Inserted one document");
					}
				}
				client.close();
			} catch (Exception e) {
				System.out.println("Error in DB");
			} 
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
