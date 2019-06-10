package project;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class ReviewServlet {
	public static void main(String[] args) throws Exception {
		String username = "TinaTrojan";
		String profname = "Sandra Batista";
		String ratingText = "Dummy review text";
		String rating = "3";
		
		
		try {
			MongoClient client = new MongoClient("localhost", 27017);
			//String connectPoint = client.getConnectPoint();
			System.out.println("Server connection successful");
			
			MongoDatabase dbs = client.getDatabase("RMUSC");
			System.out.println("Connected to database successful");
			System.out.println("Database: " + dbs.getName());
			
			// Inserting into the collection
			MongoCollection<Document> collection = dbs.getCollection("testreview");
			

			//Query
			Document query = new Document();
			query.put("username", username);
			query.put("profname", profname);
			
			
			// First see if that user exists by email
			Document doc =  collection.find(query).first();
			System.out.println(doc);
			if (doc == null) {
				// review does not exist yet
				// store new document
				doc = new Document("_id", (int)collection.count())
						.append("username", username)
						.append("profname", profname)
						.append("rating", rating)
						.append("ratingText", ratingText);
				collection.insertOne(doc);
				System.out.println("New review inserted");
			} else {
				int id_ = Integer.parseInt(doc.getString("id_"));
				collection.findOneAndDelete(doc);
				
				doc = new Document("_id", id_)
						.append("username", username)
						.append("profname", profname)
						.append("rating", rating)
						.append("ratingText", ratingText);
				collection.insertOne(doc);
				
				System.out.println("Old review updated");
			}
			
			/*
			List results = new ArrayList<>();
			collection.find(query).into(results);
			FindIterable<Document> docs = collection.find();	
			for (Document doc : docs) {
				System.out.println(doc.getString("email"));
			}
			*/
			
			/*
			if (doc == null) {
				// Doc is null so that email doesn't exist
				// return appropriate error
				System.out.println("Doesn't exist");
			} else {
				// doc is not null, user email already exists
				// confirm that password matches
				System.out.println("Found something");
				System.out.println(doc.toJson());			
			}
			*/	
			client.close();
		} catch (Exception e) {
			
		}	
	}
}
