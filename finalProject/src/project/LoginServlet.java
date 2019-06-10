package project;
import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.io.PrintWriter;
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
	public static void run(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		PrintWriter pw=response.getWriter();
		
		if (email == null || email == "") { 
			System.out.println("bad email"); 
			pw.println("bad email");
		}
		if (password == null || password=="") { 
			System.out.println("bad password"); 
			pw.println("bad password");
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
					HttpSession session = request.getSession();
					String fname = doc.getString("fname");
					String lname = doc.getString("lname");
					String major = doc.getString("major");
					
//					//so from this
//					session.setAttribute("fname", fname);
//					session.setAttribute("lname", lname);
//					session.setAttribute("user", email);
//					session.setAttribute("major", major);
					
					//to this
					Student user = new Student(fname, lname, email, major);
					session.setAttribute("user", user);
					System.out.println("login Success");
					pw.println("login success");
				} else {
					System.out.println("Wrong password");
					pw.println("Wrong password");
				}
				
			}
			else {
				System.out.println("That email doesn't exist");
				pw.println("That email doesn't exit");
				
			}
			client.close();
		} catch (Exception e) {
			System.out.println(e);
		}	
	}
}
