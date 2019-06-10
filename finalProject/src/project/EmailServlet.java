package project;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// This servlet sends an email to interested people joining the newsletter
// Email forwarding from registration page is handled by RegisterServlet


@WebServlet("/EmailServlet")
public class EmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String stuff = "Dear " + name + ","
						+ "\n\n Thank you for joining our newsletter!."
						+ "\n\n Fight on,"
						+ "\n, CSCI201 Team 5";
		SendEmail se = new SendEmail(email, stuff);
		response.sendRedirect("index.jsp");
		return;
		
	}
	

}
