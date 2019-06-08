import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;


public class LoginServlet {
	
	public static void main(String[] args) throws Exception {
		String email = "tommytrojan@usc.edu";
		String password = "abcd1234";
		
		if (email == null) { badForm(); }
		if (password == null) { badForm(); }
		
		
		
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
				if (password.equals(passwordStored)) {
					// successful login
					// store user in session
					// redirect to JSP
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
