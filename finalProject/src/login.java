

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String username;
	String password;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		username = request.getParameter("username");
		password = request.getParameter("password");
		PrintWriter pw = response.getWriter();
		if(username.length() < 4) {
			pw.println("Username has to be longer than 4 characters.");
			pw.close();
			return;
		} else if (password.length() < 4) {
			pw.println("Password has to be longer than 4 characters.");
			pw.close();
			return;
		}
		
		
	}
	
	@Override
	public int hashCode() {
		int hash = 13;
		hash = 31*hash + password.hashCode();
		return hash;
	}
}
