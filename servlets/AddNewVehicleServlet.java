//Add new vehicle servlet

package servlets;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Vehicle;
import models.VehicleDAO;

/**
 * Add Vehicle Servlet.
 */
public class AddNewVehicleServlet extends HttpServlet{

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	//using the doGet to serve the page with a form on a GET request
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		RequestDispatcher view = req.getRequestDispatcher("addnew.jsp"); //handle jsp get request
		view.forward(req, resp);
	}

	//using the doPost to handle what happens when the form is POST'ed
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	//Create dao & new vehicle using post'ed variables (from user input), then insert and redirect back to adminIndex.jsp
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		VehicleDAO dao = new VehicleDAO(); 

		int vehicle_id = Integer.valueOf(req.getParameter("vehicle_id"));
		String make = (String) req.getParameter("make");
		String model = (String) req.getParameter("model");
		int year = Integer.valueOf(req.getParameter("year"));
		int price = Integer.valueOf(req.getParameter("price"));
		String license_number = (String) req.getParameter("license_number");
		String colour = (String) req.getParameter("colour");
		int number_doors = Integer.valueOf(req.getParameter("number_doors"));
		String transmission = (String) req.getParameter("transmission");
		int mileage = Integer.valueOf(req.getParameter("mileage"));
		String fuel_type = (String) req.getParameter("fuel_type");
		int engine_size = Integer.valueOf(req.getParameter("engine_size"));
		String body_style = (String) req.getParameter("body_style");
		String condition = (String) req.getParameter("condition");
		String Notes = (String) req.getParameter("Notes");

		Vehicle v = new Vehicle(vehicle_id, make, model, year, price, license_number, colour, number_doors, transmission, mileage, fuel_type, engine_size, body_style, condition, Notes);
		boolean done;
		try {
			done = dao.InsertVehicle(v); 
			System.out.println(done);
			resp.sendRedirect("adminIndex"); //truefalse! check this 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}



