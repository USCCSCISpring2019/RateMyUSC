import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

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
	public static void badForm() {
		// badform
		// send it back to registration form
		System.out.println(" BAD FORM");
	}
}
