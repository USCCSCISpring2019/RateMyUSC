package project;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


@WebServlet("/TestLogin")
public class TestLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String email = request.getParameter("username");
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
				String username = doc.getString("username");
				String major = doc.getString("major");
				System.out.println("Is password null?");
				System.out.println(passwordStored);
				if (password.equals(passwordStored)) {
					// successful login
					// store user in session
					HttpSession session = request.getSession();
					User user = new User(username, major, email);
					session.setAttribute("user", user);
					System.out.println("Login success");
					client.close();
					return;
					
				} else {
					// that password is wrong
					response.getWriter().append("Wrong password");
				}
			}
			else {
				// that email address does not exist
				// forward back to page
				response.getWriter().append("That email doesn't exist");
			}
			client.close();
		} catch (Exception e) {
			System.out.println(e);
		}	
	}
}
