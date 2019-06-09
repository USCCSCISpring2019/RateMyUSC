import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;


public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public static void run(HttpServletRequest request, HttpServletResponse Response) throws ServletException, IOException {
		
		
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if (email == null || email == "") { 
			System.out.println("bad email"); 
		}
		if (password == null || password=="") { 
			System.out.println("bad password"); 
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
			// Find the matching email
			Document query = new Document();
			query.put("email", email);
			FindIterable<Document> docs =  collection.find(query);
			Document doc = collection.find(eq("email",email)).first();
			
			if (doc != null) {
				String passwordStored = doc.getString("password");
				if (password.trim().equals(passwordStored)) {
					//do we really want to trim?
					
					// successful login
					// store user in session
					HttpSession session = request.getSession();
					session.setAttribute("user", email);
					
					String fname = doc.getString("fname");
					String lname = doc.getString("lname");
					String major = doc.getString("major");
					session.setAttribute("fname", fname);
					session.setAttribute("lname", lname);
					session.setAttribute("major", major);

					// redirect to JSP (help :( ) 
					
					System.out.println("login Success");
				} else {
					// that password is wrong
					// redirect 
					System.out.println("Wrong password");
				}
				
			}
			else {
				// that email address does not exist
				// forward back to JSP
				System.out.println("That email doesn't exist");
				//might as well send to empty form
				
			}
		
			client.close();
		} catch (Exception e) {
			System.out.println(e);
		}	
	}
	public static void badForm() {
		// bad entry
		// redirect back to JSP
		System.out.println("Bad form");
		
	}
}
