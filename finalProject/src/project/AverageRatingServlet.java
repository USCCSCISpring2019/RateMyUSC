package project;
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


public class AverageRatingServlet {
	
	public static void main(String[] args) throws Exception {
		String pemail = "Professor@usc.edu";
		
		
		try {
			MongoClient client = new MongoClient("localhost", 27017);
			//String connectPoint = client.getConnectPoint();
			System.out.println("Server connection successful");
			
			MongoDatabase dbs = client.getDatabase("RMUSC");
			System.out.println("Connected to database successful");
			System.out.println("Database: " + dbs.getName());
			
			// Inserting into the collection
			MongoCollection<Document> collection = dbs.getCollection("testreview");
			
			// Find the matching email
			Document query = new Document();
			query.put("pemail", pemail);
			int numReviews = 0;
			Double currRating = 0.0;
			
			FindIterable<Document> docs =  collection.find(query);
			
			if (docs != null) 
			{
				for (Document doc : docs) {
					currRating += Double.parseDouble(doc.getString("rating"));
					++numReviews;
				}
				System.out.println("Average rating: " + currRating/numReviews);
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