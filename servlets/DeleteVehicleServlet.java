//Delete Vehicle 

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
 * The Class DeleteVehicleServlet.
 */
public class DeleteVehicleServlet extends HttpServlet{

	/**Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	//using the doGet to serve the page with a form on a GET request
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int vehicle_id = Integer.valueOf(req.getParameter("vehicle_id")); //getting the id of the item to be deleted
		VehicleDAO dao = new VehicleDAO();
		Vehicle v;
		v = dao.getVehicle(vehicle_id); //getting the vehicle by id from the database
		req.setAttribute("v", v); //setting variable v on jsp
		RequestDispatcher view = req.getRequestDispatcher("delete.jsp");
		view.forward(req, resp); //sending the vehicle on to the delete.jsp page
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	//using the doPost to handle what happens when the form is POST'ed
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int vehicle_id = Integer.valueOf(req.getParameter("vehicle_id")); //get the vehicle id again off the delete page
		VehicleDAO dao = new VehicleDAO();
		try {
			dao.deleteVehicle(vehicle_id); 	//try deleting then redirect - should alway works because delete button is implemented for each real record
			resp.sendRedirect("adminIndex");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
