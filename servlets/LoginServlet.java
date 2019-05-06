//Login servlet

package servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.UserDAO;


/**
 * The Class LoginServlet.
 */
public  class LoginServlet extends HttpServlet {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID= 1L;
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet( HttpServletRequest req, HttpServletResponse resp )
			throws ServletException,IOException {
		resp.setContentType("text/html");
		resp.setStatus(HttpServletResponse.SC_OK);
		RequestDispatcher view = req.getRequestDispatcher("login.jsp");
		view.forward(req, resp);

	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uname = req.getParameter("username");
		String password = req.getParameter("password"); //get uname and pword input fromuser 
		UserDAO dao = new UserDAO();
		boolean done;
		try {
			done = dao.verifyUser(uname, dao.hash(password)); //see if uname and and hashed pword input match with database
			if (done == true)
			{
				resp.sendRedirect("adminIndex"); //redirect to admin page
			}
			else done = dao.verifyUser(uname, password); //if above doesn't work see if user has inputed the hash (temporary password hashing functionality) 
			if (done == true)
			{
				resp.sendRedirect("adminIndex"); //redirect to admin page
			}
			else if( done == false) {
				doGet(req, resp); //if input is invalid stay on login page
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}