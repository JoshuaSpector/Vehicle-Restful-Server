//Search by make, model, id or price range 

package servlets;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Vehicle;
import models.VehicleDAO;

/**
 * The Class SearchVehicleServlet.
 */
public class SearchVehicleServlet extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID= 1L;
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		RequestDispatcher view = req.getRequestDispatcher("search.jsp");
		view.forward(req, resp);
	}


	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req , HttpServletResponse resp) throws ServletException , IOException
	{
		VehicleDAO dao = new VehicleDAO(); 
		if ( req.getParameter("vehicle_id") != null) //if user inputs to vehicle_id searchbar
		{ 
			String parameter = "vehicle_id"; //search by vehicle_id
			String vehicle_id = (String) req.getParameter("vehicle_id"); //get vehicle_id to search for
			ArrayList<Vehicle> vehicle = dao.getVehiclesByParameter(parameter, vehicle_id); //search for vehicle_id 
			req.setAttribute("vehicle" , vehicle); //pass vehicle back to page
			RequestDispatcher view = req.getRequestDispatcher("search.jsp"); 
			view.forward(req, resp); //forward on to page
		}

		else if  (req.getParameter("make") != null) //same as above for search by make 
		{
			String parameter = "make";
			String make = 	"'" + (String) req.getParameter("make") + "'" ;
			ArrayList<Vehicle> vehicle = dao.getVehiclesByParameter(parameter, make);
			req.setAttribute("vehicle" , vehicle);
			RequestDispatcher view = req.getRequestDispatcher("search.jsp");
			view.forward(req, resp);

		}
		else if  (req.getParameter("model") != null) //same as above for search by model
		{
			String parameter = "model";
			String model = "'" +(String) req.getParameter("model") + "'" ;
			ArrayList<Vehicle> vehicle = dao.getVehiclesByParameter(parameter, model);
			req.setAttribute("vehicle" , vehicle);
			RequestDispatcher view = req.getRequestDispatcher("search.jsp");
			view.forward(req, resp);

		}
		else { //search by price range - similar to above three cases but uses another three cases as there are two variables being used 
			if ( req.getParameter("minprice") == null && req.getParameter("maxprice") != null ) 
			{
				int minprice = 0; // set minprice/maxprice
				int maxprice = Integer.valueOf(req.getParameter("maxprice"));
				ArrayList<Vehicle> vehicle;
				try {
					vehicle = dao.getVehiclesByPriceRange(minprice, maxprice); //get by price range
					req.setAttribute("vehicle" , vehicle); //give variable to page
					RequestDispatcher view = req.getRequestDispatcher("search.jsp");
					view.forward(req, resp); 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			if ( req.getParameter("maxprice") == null && req.getParameter("minprice") != null )
			{
				int maxprice = 0;
				int minprice = Integer.valueOf(req.getParameter("minprice"));
				ArrayList<Vehicle> vehicle;
				try {
					vehicle = dao.getVehiclesByPriceRange(minprice, maxprice);	
					req.setAttribute("vehicle" , vehicle);
					RequestDispatcher view = req.getRequestDispatcher("search.jsp");
					view.forward(req, resp);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			else
			{
				int maxprice = Integer.valueOf(req.getParameter("maxprice"));
				int minprice = Integer.valueOf(req.getParameter("minprice"));
				ArrayList<Vehicle> vehicle;
				try {
					vehicle = dao.getVehiclesByPriceRange(minprice, maxprice);
					req.setAttribute("vehicle" , vehicle);
					RequestDispatcher view = req.getRequestDispatcher("search.jsp");
					view.forward(req, resp);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}


		}


	}

}