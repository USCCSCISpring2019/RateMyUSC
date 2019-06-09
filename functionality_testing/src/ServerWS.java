import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.Vector;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


import user.*;

@ServerEndpoint (value = "/ServerWS")
public class ServerWS implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7170240247241699283L;
	private static transient Vector<Session> sessionVector = new Vector<Session>();
	private static HashMap<String, String> caches = new HashMap<>();
	private static ArrayList<Review> reviews = new ArrayList<>();
	private static HashMap<String, Pair<Integer, ArrayList<String> > > inbox = new HashMap<>();
	private String filename = "/Users/zhou/Documents/eclipse-workspace/CS201-WebSockets/src/data.txt";

	public ServerWS() {
		try {
			FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            caches = (HashMap<String, String>) ois.readObject();
            reviews = (ArrayList<Review>) ois.readObject();
            inbox = (HashMap<String, Pair<Integer, ArrayList<String> > >)ois.readObject();
            
            for(int i = 0; i < reviews.size(); i++) {
            	System.out.println(reviews.get(i).getContent());
            }
            ois.close();
            fis.close();
		} catch(IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
		} catch(ClassNotFoundException cne) {
			System.out.println("cne: " + cne.getMessage());
		}
		
		/*
		char rev_content = 'a';
		for(int i = 0; i < 10; i++){
			rev_content = (char)(rev_content + 1);
			String rev_str = Character.toString(rev_content);
			reviews.add(new Review(i, "User" + rev_content, rev_str));
			
			Pair<Integer, ArrayList<String> > temp = new Pair<>(0, new ArrayList<>());
			inbox.put("User" + rev_content, temp);
		}*/
		System.out.println("Server loading");
	}

	@OnOpen
	public void open(Session session) {
		System.out.println("Connection made!");
		sessionVector.add(session);
	}

	@OnMessage
	public void OnMessage(String message, Session session) {
		System.out.println("Message: " + message);
		if(message.substring(0, 5).equals("CACHE")) {
			int msgStart = message.indexOf("MSG");
			String username = message.substring(5, msgStart);
			String msg = message.substring(msgStart + 3);

			if(msg.length() > 0) {
				caches.put(username, msg);
				System.out.println(caches.get(username));
			}
		}
		else if(message.substring(0, 5).equals("LOGIN")){
			String username = message.substring(5);
			String cache = caches.get(username);
			if(cache != null) {
				try {
					session.getBasicRemote().sendText("CACHE" + cache);
				} catch(IOException ioe) {
					System.out.println("ioe: " + ioe.getMessage());
				}
			}
		} else if(message.substring(0, 6).equals("UPVOTE")) {
			int id = Integer.parseInt(message.substring(6));
			String username = review2user(id);
			inbox.get(username).first++;
			String msg = "Someone upvoted your review: " + id2review(id);
			inbox.get(username).second.add(msg);
			
		} else if(message.substring(0, 4).equals("READ")) {
			String username = message.substring(4);
			int unread = inbox.get(username).first;
			inbox.get(username).first = 0;
			ArrayList<String> temp = inbox.get(username).second;
			String response = "";
			for(int i = temp.size() - 1; i >= 0; i--) {
				response += temp.get(i) + "\n";
			}
			
			try {
				session.getBasicRemote().sendText("UR" + unread + "MSGS" + response);
			} catch(IOException ioe) {
				System.out.println("ioe: " + ioe.getMessage());
			}
		} else if(message.substring(0, 6).equals("REQMSG")) {
			String username = message.substring(6);
			System.out.println("REQMSG: " + username);
			try {
				session.getBasicRemote().sendText("UNREAD" + inbox.get(username).first);
			} catch(IOException ioe) {
				System.out.println("ioe: " + ioe.getMessage());
			}
		}
	}

	@OnClose
	public void close(Session session) {
		System.out.println("Disconnecting!");
		sessionVector.remove(session);
		try {
			FileOutputStream file = new FileOutputStream(filename); 
			ObjectOutputStream out = new ObjectOutputStream(file); 
			out.writeObject(caches);
			out.writeObject(reviews);
			out.writeObject(inbox);
			out.close();
			file.close();
		} catch(IOException ioe) {
			System.out.println("ioe: " + ioe.getMessage());
		}
	}

	@OnError
	public void onError(Throwable error) {

	}

	public String review2user(int id) {
		for(Review r : reviews) {
			if(r.getID() == id) return r.getUsername();
		}
		return null;
	}
	
	public String id2review(int id) {
		for(Review r : reviews) {
			if(r.getID() == id) return r.getContent();
		}
		return null;
	}
}

class Pair<F, V> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4979405107136299503L;
	F first;
	V second;
	
	public Pair(F f, V v) {
		first = f;
		second = v;
	}
}
