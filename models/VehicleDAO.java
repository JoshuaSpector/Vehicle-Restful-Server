//VehicleDAO - CRUD and SQL Connection functionality - Note that UserDAO and SaleDAO are based around this class

package models;
import java.util.*;
import java.sql.*;


/**
 * The Class VehicleDAO.
 */
public class VehicleDAO {
	
	/** The db connection. */
	//declare SQL connection/query variables
	public static Connection dbConnection = null;
	
	/** The resultset. */
	public static ResultSet resultset = null;
	
	/** The statement. */
	public static Statement statement = null;

	/**
	 * Instantiates a new vehicle DAO.
	 */
	public VehicleDAO()
	{

	}

	/**
	 * Gets the DB connection.
	 *
	 * @return the DB connection
	 */
	//open connection method
	public static Connection getDBConnection() {
		Connection dbConnection = null;

		try {
			Class.forName("org.sqlite.JDBC");
		} 

		catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			String dbURL = "jdbc:sqlite:vehicles.sqlite"; //connecting to the vehicles sqlite dbase
			dbConnection = DriverManager.getConnection(dbURL);
			return dbConnection;
		} 

		catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return dbConnection;
	}

	/**
	 * Close connection.
	 */
	//Method to close the connection after the SQL query ends
	public void closeConnection() {
		try {
			if (resultset != null) {
				resultset.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Gets all vehicles.
	 *
	 * @return all vehicles
	 * @throws SQLException 
	 */
	//getAll method
	public ArrayList<Vehicle> getAllVehicles()  throws SQLException {
		String query = "SELECT * FROM vehicles;"; //sql query object
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>(); //vehicle arraylist to store the output of the SQL query

		try { 
			dbConnection = getDBConnection(); //get connection & create statement
			statement = dbConnection.createStatement();  
			System.out.println(query); //print the SQL query to console
			// execute SQL query
			resultset = statement.executeQuery(query); //Executes the query and stores the results in the resultset object
			while (resultset.next()) { //While the next item in the resultselt is not null:

				//Store variables from the resultselt
				int vehicle_id = resultset.getInt("vehicle_id");
				String vehicle_make = resultset.getString("make");
				String vehicle_model = resultset.getString("model");
				int registration_year = resultset.getInt("year");
				int price = resultset.getInt("Price");
				String license_number = resultset.getString("license_number");
				String colour = resultset.getString("colour");
				int number_of_doors = resultset.getInt("number_doors");
				String transmission = resultset.getString("transmission");
				int mileage = resultset.getInt("mileage");
				String fuel_type = resultset.getString("fuel_type");
				int engine_size = resultset.getInt("engine_size");
				String body_style = resultset.getString("body_style");
				String condition = resultset.getString("condition");
				String Notes = resultset.getString("Notes");

				Vehicle v = //new vehicle object
						new Vehicle(vehicle_id, vehicle_make, vehicle_model, registration_year,
								price, license_number, colour, number_of_doors, transmission, mileage,
								fuel_type, engine_size, body_style, condition, Notes);

				vehicles.add(v); //add new vehicle to arraylist

			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		closeConnection(); //end connection/query - this prevents database locking which interferes with the update function
		return vehicles;
	}

	//This is an extra get method to allow searching by variables other than vehicle_id (additional functionality) 
	/**
	 * Gets the vehicles by parameter.
	 *
	 * @param parameter 
	 * @param value 
	 * @return the vehicles by parameter
	 */
	//Same as getAllVehicles but different parameter inputs and a different SQL query
	public ArrayList<Vehicle> getVehiclesByParameter(String parameter, String value)  {
		String query = "SELECT * FROM vehicles WHERE " + parameter + " = " + value + ";"; //takes a parameter to search by and the exact value to search for 
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			// execute SQL query
			resultset = statement.executeQuery(query);
			while (resultset.next()) {


				int vehicle_id = resultset.getInt("vehicle_id");
				String vehicle_make = resultset.getString("make");
				String vehicle_model = resultset.getString("model");
				int registration_year = resultset.getInt("year");
				int price = resultset.getInt("Price");
				String license_number = resultset.getString("license_number");
				String colour = resultset.getString("colour");
				int number_of_doors = resultset.getInt("number_doors");
				String transmission = resultset.getString("transmission");
				int mileage = resultset.getInt("mileage");
				String fuel_type = resultset.getString("fuel_type");
				int engine_size = resultset.getInt("engine_size");
				String body_style = resultset.getString("body_style");
				String condition = resultset.getString("condition");
				String Notes = resultset.getString("Notes");

				Vehicle v =
						new Vehicle(vehicle_id, vehicle_make, vehicle_model, registration_year,
								price, license_number, colour, number_of_doors, transmission, mileage,
								fuel_type, engine_size, body_style, condition, Notes);

				vehicles.add(v);


			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		closeConnection();
		return vehicles;
	}
	
	/**
	 * Gets the vehicles by price range.
	 *
	 * @param minprice the minprice
	 * @param maxprice the maxprice
	 * @return the vehicles by price range
	 * @throws SQLException 
	 */
	//same as above but uses uses min/maxprice variables and a BETWEEN query to search for vehicles within a given price range
	public ArrayList<Vehicle> getVehiclesByPriceRange(int minprice, int maxprice)  throws SQLException {
		String query = "SELECT * FROM vehicles WHERE price BETWEEN " + minprice + " AND " + maxprice + ";";
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			// execute SQL query
			resultset = statement.executeQuery(query);
			while (resultset.next()) {


				int vehicle_id = resultset.getInt("vehicle_id");
				String vehicle_make = resultset.getString("make");
				String vehicle_model = resultset.getString("model");
				int registration_year = resultset.getInt("year");
				int price = resultset.getInt("Price");
				String license_number = resultset.getString("license_number");
				String colour = resultset.getString("colour");
				int number_of_doors = resultset.getInt("number_doors");
				String transmission = resultset.getString("transmission");
				int mileage = resultset.getInt("mileage");
				String fuel_type = resultset.getString("fuel_type");
				int engine_size = resultset.getInt("engine_size");
				String body_style = resultset.getString("body_style");
				String condition = resultset.getString("condition");
				String Notes = resultset.getString("Notes");

				Vehicle v =
						new Vehicle(vehicle_id, vehicle_make, vehicle_model, registration_year,
								price, license_number, colour, number_of_doors, transmission, mileage,
								fuel_type, engine_size, body_style, condition, Notes);

				vehicles.add(v);


			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		closeConnection();
		return vehicles;
	}

	/**
	 * Gets the vehicle.
	 *
	 * @param Vehicle_Id the vehicle id
	 * @return the vehicle
	 */
	//CRUD method - get vehicle by id - vehicle_id is a primary key so this method only has to return one instance of the vehicle class 
	public Vehicle getVehicle(int Vehicle_Id)
	{
		String query = "SELECT * FROM vehicles WHERE vehicle_id = " + Vehicle_Id + ";";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			// execute SQL query
			resultset = statement.executeQuery(query);

			int vehicle_id = resultset.getInt("vehicle_id");
			String vehicle_make = resultset.getString("make");
			String vehicle_model = resultset.getString("model");
			int registration_year = resultset.getInt("year");
			int price = resultset.getInt("Price");
			String license_number = resultset.getString("license_number");
			String colour = resultset.getString("colour");
			int number_of_doors = resultset.getInt("number_doors");
			String transmission = resultset.getString("transmission");
			int mileage = resultset.getInt("mileage");
			String fuel_type = resultset.getString("fuel_type");
			int engine_size = resultset.getInt("engine_size");
			String body_style = resultset.getString("body_style");
			String condition = resultset.getString("condition");
			String Notes = resultset.getString("Notes");

			Vehicle v =
					new Vehicle(vehicle_id, vehicle_make, vehicle_model, registration_year,
							price, license_number, colour, number_of_doors, transmission, mileage,
							fuel_type, engine_size, body_style, condition, Notes);
			statement.close(); 
			return v; //This method returns just the one vehicle instead of an arrayList of vehicles 



		} catch (SQLException e) {
			System.out.println(e.getMessage());
			} 
		return null;

	}

	/**
	 * Delete vehicle.
	 *
	 * @param Vehicle_Id the vehicle id
	 * @return the boolean
	 * @throws SQLException 
	 */
	//delete vehicle by id 
	public Boolean deleteVehicle(int Vehicle_Id) throws SQLException 
	{
		closeConnection(); 
		String query = "DELETE FROM vehicles WHERE vehicle_id = " + Vehicle_Id + ";"; //DELETE query

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			statement.executeQuery(query); //delete method does not need to return a resultset here
			closeConnection();
			return true; //if deleted return true
		} 


		catch (SQLException e) {
			System.out.println(e.getMessage());
			closeConnection();
			return false; //if not deleted return false
		} 

	}

	/**
	 * Insert vehicle.
	 *
	 * @param v vehicle
	 * @return the boolean
	 * @throws SQLException
	 */
	//Insert vehicle - takes a vehicle object and uses getters to access variables in an INSERT statement
	public Boolean InsertVehicle(Vehicle v) throws SQLException//vehicle var
	{
		closeConnection(); 
		String query = "INSERT INTO vehicles (vehicle_id, make, model, year," + 
				" price, license_number, colour, number_doors, transmission, mileage, " + 
				"fuel_type, engine_size, body_style, condition, Notes)"
				+"\nVALUES (" + v.getVehicle_id() +", " + "'" + v.getMake() + "'" +", " + "'"  + v.getModel() + "'"  +", " + v.getYear()
				+", " + v.getPrice() +", " + "'"  + v.getLicense_number() + "'"  +", " + "'"  + v.getColour() + "'"  +", " + v.getNumber_doors()
				+", " + "'"  + v.getTransmission() + "'"  +", " + v.getMileage() +", " + "'"  + v.getFuel_type() + "'"  +", " + v.getEngine_size()
				+", " + "'"  + v.getBody_style() + "'"  +", " + "'"  + v.getCondition() + "'"  +", " + "'"  + v.getNotes() + "'" +")";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			statement.executeQuery(query); //does not return a resultset
			closeConnection();
			return true; //if insert successful return true 
		} 


		catch (SQLException e) {
			System.out.println(e.getMessage());
			closeConnection();
			return false;
		} 

	}
	
	/**
	 * Insert vehicle.
	 *
	 * @param v vehicle
	 * @return the boolean
	 * @throws SQLException
	 */
	//Insert vehicle - takes a vehicle object and uses getters to access variables in an INSERT statement
	public Boolean InsertVehicleSansID(Vehicle v) throws SQLException//vehicle var
	{
		closeConnection(); 
		String query = "INSERT INTO vehicles (make, model, year," + 
				" price, license_number, colour, number_doors, transmission, mileage, " + 
				"fuel_type, engine_size, body_style, condition, Notes)"
				+"\nVALUES (" + "'" + v.getMake() + "'" +", " + "'"  + v.getModel() + "'"  +", " + v.getYear()
				+", " + v.getPrice() +", " + "'"  + v.getLicense_number() + "'"  +", " + "'"  + v.getColour() + "'"  +", " + v.getNumber_doors()
				+", " + "'"  + v.getTransmission() + "'"  +", " + v.getMileage() +", " + "'"  + v.getFuel_type() + "'"  +", " + v.getEngine_size()
				+", " + "'"  + v.getBody_style() + "'"  +", " + "'"  + v.getCondition() + "'"  +", " + "'"  + v.getNotes() + "'" +")";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			statement.executeQuery(query); //does not return a resultset
			closeConnection();
			return true; //if insert successful return true 
		} 


		catch (SQLException e) {
			System.out.println(e.getMessage());
			closeConnection();
			return false;
		} 

	}
	
	/**
	 * Update vehicle.
	 *
	 * @param Vehicle_Id the vehicle id
	 * @param v vehicle
	 * @return the boolean
	 * @throws SQLException 
	 */
	//update vehicle - similar to insert but takes a vehicle_id parameter to find the vehicle to be updated 
	public Boolean updateVehicle(int Vehicle_Id, Vehicle v) throws SQLException //vehicle var
	{
		closeConnection(); //this line ensures there is no database locking when trying to update on the web front-end by clearing any unclosed queries
		String query = "UPDATE vehicles" //use of UPDATE/SET statement
				+"\nSET vehicle_id = " + v.getVehicle_id() + ", " +
				"make = " + "'"  + v.getMake() + "'"  + ", " + 
				"model = " + "'"  + v.getModel() + "'"  + ", " + 
				"year = " + v.getYear() + ", " + 
				"price = " + v.getPrice() + ", " + 
				"license_number = " + "'"  + v.getLicense_number() + "'"  + ", " + 
				"colour = " + "'"  + v.getColour() + "'"  + ", " + 
				"number_doors = " + v.getNumber_doors() + ", " + 
				"transmission = " + "'"  + v.getTransmission() + "'"  + ", " + 
				"mileage = " + v.getMileage() + ", " + 
				"fuel_type = " + "'"  + v.getFuel_type() + "'"  + ", " + 
				"engine_size = " + v.getEngine_size() + ", " + 
				"body_style = " + "'"  + v.getBody_style() + "'"  + ", " + 
				"condition = " + "'"  + v.getCondition() + "'"  + ", " + 
				"notes = " + "'"  + v.getNotes() + "'" + 
				"\nWHERE vehicle_id = " + Vehicle_Id + ";";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			statement.executeQuery(query);
			closeConnection();
			return true; //if updated return true
		} 


		catch (SQLException e) {
			System.out.println(e.getMessage());
			closeConnection();
			return false; //if not updated return false
		} 
	}
}