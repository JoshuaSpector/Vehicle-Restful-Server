//Update sales servlet - see update vehicle servlet

package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Sale;
import models.SaleDAO;


/**
 * The Class UpdateSaleServlet.
 */
public class UpdateSaleServlet extends HttpServlet{

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	//using the doGet to serve the page with a form on a GET request
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int vehicle_id = Integer.valueOf(req.getParameter("vehicle_id"));
		SaleDAO dao = new SaleDAO();
		Sale s;
		s = dao.GetSale(vehicle_id);
		req.setAttribute("s", s);
		RequestDispatcher view = req.getRequestDispatcher("updatesale.jsp");
		view.forward(req, resp);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	//using the doPost to handle what happens when the form is POST'ed
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		SaleDAO dao = new SaleDAO();

		int vehicle_id = Integer.valueOf(req.getParameter("vehicle_id"));
		String sold_date = (String) req.getParameter("sold_date");
		int sold_price = Integer.valueOf(req.getParameter("sold_price"));
		String status = (String) req.getParameter("status");


		Sale s = new Sale(vehicle_id, sold_date, sold_price, status);
		boolean done;
		try {
			done = dao.UpdateSale(vehicle_id, s); 
			System.out.println(done);
			resp.sendRedirect("salesindex"); //truefalse! check this 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}