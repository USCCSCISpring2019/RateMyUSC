package project;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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



@WebServlet("/ReturnReviewsServlet")
public class ReturnReviewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String profname = "";
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
			query.put("profname", profname);
			int numReviews = 0;
			Double runningTotal = 0.0;

			FindIterable<Document> docs =  collection.find(query);

			if (docs != null) 
			{
				ArrayList<Review> reviews = new ArrayList<>();
				for (Document doc : docs) {
					int id = Integer.parseInt(doc.getString("id_"));
					String username = doc.getString("username");
					String content = doc.getString("ratingText");
					double rating = Double.parseDouble(doc.getString("rating"));
					Review curr = new Review(id, username, content, rating, profname);
					reviews.add(curr);


					runningTotal += Double.parseDouble(doc.getString("rating"));
					++numReviews;
				}
				request.getSession().setAttribute("reviews", reviews);
				request.getSession().setAttribute("averageRating", runningTotal/numReviews);
				System.out.println("Reviews stored in session ArrayList<Review> : reviews");
				System.out.println("Average rating stored in session var: averageRating");
			}


			client.close();
		} catch (Exception e) {
			System.out.println(e);
		}	
	}
}
