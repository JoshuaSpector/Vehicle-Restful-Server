//generate API key

package servlets;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.UserDAO;
import models.Developer;
import models.DeveloperDAO;
import models.User;



/**
 * The Class GenerateAPIKeyServlet
 */
public  class GenerateAPIKeyServlet extends HttpServlet {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID= 1L;
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet( HttpServletRequest req, HttpServletResponse resp )
			throws ServletException,IOException { 
		RequestDispatcher view = req.getRequestDispatcher("generateAPI.jsp"); 
		view.forward(req, resp); 



	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = (String)req.getParameter("username"); //get username off page
		DeveloperDAO dao = new DeveloperDAO(); //using UserDAO to verify if user exists
		if (dao.verifyDeveloperWithoutKey(username) == true) //if user does exist:
		{
			
		}
		else if (dao.verifyDeveloperWithoutKey(username) == false) //if user doesn't exist:
		{
			String apikey;
			try {
				apikey = dao.hash(username);
				dao.insertDeveloper(username, apikey);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Developer d = dao.getDeveloper(username);
			req.setAttribute("d", d); //get user and then pass user to jsp
			RequestDispatcher view = req.getRequestDispatcher("generateAPI.jsp");
			view.forward(req, resp);
		}
	}



}