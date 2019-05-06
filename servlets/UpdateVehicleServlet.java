//Update vehicle servlet

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
 * The Class UpdateVehicleServlet.
 */
public class UpdateVehicleServlet extends HttpServlet{

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	//using the doGet to serve the page with a form on a GET request
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int vehicle_id = Integer.valueOf(req.getParameter("vehicle_id")); //get vehicle id of vehicle to be updated
		VehicleDAO dao = new VehicleDAO();
		Vehicle v; //find vehicle in database
		v = dao.getVehicle(vehicle_id);
		req.setAttribute("v", v); //pass vehicle back to the jsp
		RequestDispatcher view = req.getRequestDispatcher("update.jsp");
		view.forward(req, resp); 
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	//using the doPost to handle what happens when the form is POST'ed
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		VehicleDAO dao = new VehicleDAO(); //new vehicle dao & getting all the variables post'ed from update form
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
			done = dao.updateVehicle(vehicle_id, v); //create new vehicle then update into database
			System.out.println(done);
			resp.sendRedirect("adminIndex");  //redirect to adminIndex when done

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}