/* ServletApi class
 * This is the Restful access point for the mobile application to the server
 * contains doGet, doPost(insert), doPut(update), and doDelete methods
 * Makes use of gson for Json string functionality
 * Programmed with Jetty servlets
 */

package servlets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;
import models.DeveloperDAO;
import models.Vehicle;
import models.VehicleDAO;

//SERVLET CLASS
public class ServletApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	
	//GET METHOD
	protected void doGet(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException,
	IOException {
		//Get apikey from request parameter
		String apikey = req.getParameter("apikey");
		//Print key (test)
		System.out.println("testkeyget" + apikey);
		//Use DeveloperDAO class to validate apikey against database
		DeveloperDAO devdao = new DeveloperDAO();
		//If valid key then:
		if (devdao.verifyDeveloperWithoutUser(apikey) == true)
		{
			//Create vehicle arraylist
			ArrayList<Vehicle> allVehicles;
			try {
				//Create gson object and dao
				Gson gson = new Gson();
				VehicleDAO dao = new VehicleDAO();
				//Get all vehicle objects from dao and pass them to arraylist
				allVehicles = dao.getAllVehicles();
				//Use gson to convert arraylist to Json formatted string
				String myJson = gson.toJson(allVehicles);
				//Use PrintWriter to write string back to application
				PrintWriter writer;
				writer = resp.getWriter();
				writer.write(myJson);
				writer.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//else if invalid/null key
		else 
		{
			//write error message to application
			PrintWriter writer;
			writer = resp.getWriter();
			writer.write("API_KEY INVALID OR NOT SENT");
			System.out.println("API_KEY RECEIVED INVALID OR MISSING");
			writer.close();
		}
	}
	//END GET

	//POST METHOD
	@Override
	protected void doPost(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException,
	IOException {	
		//Use BufferedReader to read buffered input stream
		BufferedReader reader= req.getReader();
		//Use StringBuilder and BufferedReader to convert input stream to string 'params'
		StringBuilder builder= new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null ) {
			builder.append(line);
		}
		//params will contain url encoded Json string and apikey
		String params = builder.toString();
		//use substring methods to split string into key and encoded json
		String apikey = params.substring(0, params.indexOf("="));
		String vehicleJSONEncoded = params.substring(params.indexOf("=") + 1);
		//decode json with URLDecoder.decode
		String vehicleJSON = java.net.URLDecoder.decode(vehicleJSONEncoded, StandardCharsets.UTF_8.name());
		//print strings (test)
		System.out.println("postparams" + params);
		System.out.println("postkey" + apikey);
		System.out.println("postjson" + vehicleJSON);
		//Use Developer dao to verify key against database
		DeveloperDAO devdao = new DeveloperDAO();
		//If key is valid then:
		if (devdao.verifyDeveloperWithoutUser(apikey) == true)
		{
			//create gson and dao objects
			Gson gson = new Gson();
			VehicleDAO dao = new VehicleDAO();
			//use gson.fromJson to create vehicle object from Json string & class template
			Vehicle v = gson.fromJson(vehicleJSON, Vehicle.class);
			try {
				/* Use dao to insert new vehicle *without* a vehicle ID
				 * ID will be automatically generated for user 
				 * using SQLite AUTOINCREMENT primary key attribute
				 */
				boolean inserted = false;
				//inserted = dao.InsertVehicle(v);
				inserted = dao.InsertVehicleSansID(v);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Else if invalid/null key
		else 
		{
			//write error to application
			PrintWriter writer;
			writer = resp.getWriter();
			writer.write("API_KEY INVALID OR NOT SENT");
			System.out.println("API_KEY RECEIVED INVALID OR MISSING");
			writer.close();
		}
	}
	//END POST METHOD
	
	//PUT METHOD - Very similar to POST 
	@Override
	protected void doPut(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException,
	IOException {;
	//Use BufferedReader to read buffered input stream
	BufferedReader reader= req.getReader();
	//Use StringBuilder and BufferedReader to convert input stream to string 'params'
	StringBuilder builder= new StringBuilder();
	String line;
	while ((line = reader.readLine()) != null ) {
		builder.append(line);
	}
	//params will contain url encoded Json string and apikey
	String params = builder.toString();
	//use substring methods to split string into key and encoded json
	String apikey = params.substring(0, params.indexOf("="));
	String vehicleJSONEncoded = params.substring(params.indexOf("=") + 1);
	//decode json with URLDecoder.decode
	String vehicleJSON = java.net.URLDecoder.decode(vehicleJSONEncoded, StandardCharsets.UTF_8.name());
	//print strings (test)
	System.out.println("putparams" + params);
	System.out.println("putkey" + apikey);
	System.out.println("putjson" + vehicleJSON);
	//Use Developer dao to verify key against database
	DeveloperDAO devdao = new DeveloperDAO();
	//If key is valid then:
	if (devdao.verifyDeveloperWithoutUser(apikey) == true)
	{
		//create gson and dao objects
		Gson gson = new Gson();
		VehicleDAO dao = new VehicleDAO();
		//use gson.fromJson to create vehicle object from Json string & class template
		Vehicle v = gson.fromJson(vehicleJSON, Vehicle.class);
		try {
			//Use dao to update existing vehicle object
			boolean inserted = false;
			inserted = dao.updateVehicle(v.getVehicle_id(), v);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Else if key invalid/null
	else 
	{
		//Write error to server
		PrintWriter writer;
		writer = resp.getWriter();
		writer.write("API_KEY INVALID OR NOT SENT");
		System.out.println("API_KEY RECEIVED INVALID OR MISSING");
		writer.close();
	}
	}

	@Override
	protected void doDelete(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException,
	IOException {
		//Use BufferedReader to read buffered input stream
		BufferedReader reader= req.getReader();
		//Use StringBuilder and BufferedReader to convert input stream to string 'params'
		StringBuilder builder= new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null ) {
			builder.append(line);
		}
		//params will contain apikey and vehicle id
		String params = builder.toString();
		//test params string
		System.out.println(params);
		//use substrng to get apikey from params string
		String apikey = params.substring(0, params.indexOf("="));
		//use DeveloperDAO class to verify apikey against database
		DeveloperDAO devdao = new DeveloperDAO();
		//If key is valid then:
		if (devdao.verifyDeveloperWithoutUser(apikey) == true)
		{
			//Get url encoded String from params string
			String vehicle_idEncoded = params.substring(params.indexOf("=") +1);
			//Decode encoded string and then convert to integer to get vehicle_id
			int vehicle_id= Integer.valueOf(java.net.URLDecoder.decode(vehicle_idEncoded, StandardCharsets.UTF_8.name()));
			//Print parameters (test)
			System.out.println("delparams" + params);
			System.out.println("delkey" + apikey);
			System.out.println("delid" + vehicle_id);
			//Use dao to run sql query
			VehicleDAO dao = new VehicleDAO();
			try {
				//Delete vehicle
				dao.deleteVehicle(vehicle_id); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Else if key invalid/null
		else 
		{
			//Write error to server
			PrintWriter writer;
			writer = resp.getWriter();
			writer.write("API_KEY INVALID OR NOT SENT");
			System.out.println("API_KEY RECEIVED INVALID OR MISSING");
			writer.close();
		}

	}
	//END DELETE
}
//END CLASS