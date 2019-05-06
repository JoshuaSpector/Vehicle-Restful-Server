//Sales index page

package servlets;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Sale;
import models.SaleDAO;


/**
 * The Class ServletSalesHome.
 */
public class ServletSalesHome extends HttpServlet {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID= 1L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException , IOException
	{
		SaleDAO dao = new SaleDAO();
		ArrayList<Sale> allSales = null;
		allSales = dao.GetAllSales(); //get all sales 
		RequestDispatcher view = req.getRequestDispatcher("salesindex.jsp");
		req.setAttribute("allSales" , allSales);
		view.forward(req,resp); //pass allSales arraylist to page 

	}

}
