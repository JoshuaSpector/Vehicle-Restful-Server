package servlets;
//non admin homepage
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
 * The Class ServletHome.
 */
public class ServletHome extends HttpServlet {

/** Constant serialVersionUID. */
private static final long serialVersionUID= 1L;
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException , IOException
		{
		VehicleDAO dao = new VehicleDAO();
		ArrayList<Vehicle> allVehicles = null;
		try {
			allVehicles = dao.getAllVehicles();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			RequestDispatcher view = req.getRequestDispatcher("index.jsp");
			req.setAttribute("allVehicles" , allVehicles);
			view.forward(req,resp);
			
		}
}
	