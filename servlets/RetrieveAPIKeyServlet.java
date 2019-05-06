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
public  class RetrieveAPIKeyServlet extends HttpServlet {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID= 1L;
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet( HttpServletRequest req, HttpServletResponse resp )
			throws ServletException,IOException { 
		RequestDispatcher view = req.getRequestDispatcher("retrieveAPI.jsp"); 
		view.forward(req, resp); 



	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = (String)req.getParameter("username"); //get username off page
		DeveloperDAO dao = new DeveloperDAO(); //using UserDAO to verify if user exists
		if (dao.verifyDeveloperWithoutKey(username) == false) //if user doesn't exist:
		{
			RequestDispatcher view = req.getRequestDispatcher("retrieveAPI.jsp");
			view.forward(req, resp);
		}
		else if (dao.verifyDeveloperWithoutKey(username) == true) //if user does exist:
		{

			String apikey = dao.getDeveloper(username).getApiKey();
			dao.insertDeveloper(username, apikey);
			Developer d = dao.getDeveloper(username);
			req.setAttribute("d", d); //get user and then pass user to jsp
			RequestDispatcher view = req.getRequestDispatcher("retrieveAPI.jsp");
			view.forward(req, resp);
		}
	}



}