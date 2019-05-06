//forgot password 

package servlets;
import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.UserDAO;
import models.User;



/**
 * The Class ForgotPasswordServlet.
 */
public  class ForgotPasswordServlet extends HttpServlet {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID= 1L;
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet( HttpServletRequest req, HttpServletResponse resp )
			throws ServletException,IOException { 
		RequestDispatcher view = req.getRequestDispatcher("forgotpassword.jsp"); 
		view.forward(req, resp); 



	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = (String)req.getParameter("username"); //get username off page
		UserDAO dao = new UserDAO(); //using UserDAO to verify if user exists
		if (dao.verifyUserWithoutPassword(username) == true) //if user exists:
		{
			User u = dao.getUser(username);
			req.setAttribute("u", u); //get user and then pass user to jsp
			RequestDispatcher view = req.getRequestDispatcher("forgotpassword.jsp");
			view.forward(req, resp);
		}
	}
}