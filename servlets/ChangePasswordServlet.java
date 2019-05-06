//Change password servlet

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
 * The Class ChangePasswordServlet.
 */
public  class ChangePasswordServlet extends HttpServlet {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID= 1L;
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet( HttpServletRequest req, HttpServletResponse resp )
			throws ServletException,IOException {
		RequestDispatcher view = req.getRequestDispatcher("changepassword.jsp"); 
		view.forward(req, resp);



	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = (String)req.getParameter("username");
		String password = (String)req.getParameter("password");
		UserDAO dao = new UserDAO(); 
		if (dao.verifyUserWithoutPassword(username) == true) //Storing post'ed uname & pword then using UserDao to verify the user 
		{
			try {
				dao.updateUser(username, password); //trying to update the user
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher view = req.getRequestDispatcher("changepassword.jsp");
			view.forward(req, resp);
			resp.sendRedirect("adminIndex");  //if successful redirect to adminIndex

		}
	}
}