//SaleDAO - Same CRUD functionality as VehicleDAO - Same implementation with different variables

package models; 
import java.util.*;
import java.sql.*;


/**
 * The Class SaleDAO.
 */
public class SaleDAO {

	/** The db connection. */
	public static Connection dbConnection = null;
	
	/** The resultset. */
	public static ResultSet resultset = null;
	
	/** The statement. */
	public static Statement statement = null;

	/**
	 * Instantiates a new sale DAO.
	 */
	public SaleDAO()
	{

	}


	/**
	 * Gets the DB connection.
	 *
	 * @return the DB connection
	 */
	public static Connection getDBConnection() {
		Connection dbConnection = null;

		try {
			Class.forName("org.sqlite.JDBC");
		} 

		catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			String dbURL = "jdbc:sqlite:vehicles.sqlite";
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
	 * Gets all sales.
	 *
	 * @return the array list
	 */
	public ArrayList<Sale> GetAllSales()  {
		String query = "SELECT * FROM sales;";
		ArrayList<Sale> sales = new ArrayList<Sale>();

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			// execute SQL query
			resultset = statement.executeQuery(query);
			while (resultset.next()) {


				int vehicle_id = resultset.getInt("vehicle_id");
				String sold_date = resultset.getString("sold_date");
				int sold_price = resultset.getInt("sold_price");
				String status = resultset.getString("status");
				Sale s = new Sale(vehicle_id, sold_date, sold_price, status);

				sales.add(s);


			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		return sales;
	}

	/**
	 * Gets  sale.
	 *
	 * @param Vehicle_Id the vehicle id
	 * @return the sale
	 */
	public Sale GetSale(int Vehicle_Id)
	{
		String query = "SELECT * FROM sales WHERE vehicle_id = " + Vehicle_Id + ";";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			// execute SQL query
			resultset = statement.executeQuery(query);
			int vehicle_id = resultset.getInt("vehicle_id");
			String sold_date = resultset.getString("sold_date");
			int sold_price = resultset.getInt("sold_price");
			String status = resultset.getString("status");
			Sale s = new Sale(vehicle_id, sold_date, sold_price, status);
			statement.close(); //Prevents database locking conflicting with the update functionality in the web front-end 
			return s;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		return null;

	}

	/**
	 * Delete sale.
	 *
	 * @param Vehicle_Id 
	 * @return the boolean
	 * @throws SQLException 
	 */
	public Boolean DeleteSale(int Vehicle_Id) throws SQLException
	{
		closeConnection(); 
		String query = "DELETE FROM sales WHERE vehicle_id = " + Vehicle_Id + ";";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			statement.executeQuery(query);
			return true;
		} 


		catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} 

	}

	/**
	 * Insert sale.
	 *
	 * @param s 
	 * @return the boolean
	 * @throws SQLException 
	 */
	public Boolean InsertSale(Sale s) throws SQLException//vehicle var
	{
		statement.close();
		String query = "INSERT INTO sales (vehicle_id, sold_date, sold_price, status)"
				+"\nVALUES (" + s.getVehicle_id() +", " + "'" + s.getSold_date() + "'" +", " + s.getSold_price() +", "+ "'" + s.getStatus() + "'" +")";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			statement.executeQuery(query);
			return true;
		} 


		catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} 

	}

	/**
	 * Update sale.
	 *
	 * @param Vehicle_Id 
	 * @param s 
	 * @return the boolean
	 * @throws SQLException 
	 */
	public Boolean UpdateSale(int Vehicle_Id, Sale s) throws SQLException 
	{
		closeConnection(); 
		String query = "UPDATE sales"
				+"\nSET vehicle_id = " + s.getVehicle_id() + ", " +
				"sold_date = " + "'"  + s.getSold_date() + "'"  + ", " + 
				"sold_price = "  + s.getSold_price()+ ", " + 
				"status = "+ "'"  + s.getStatus() + "'" + 
				"\nWHERE vehicle_id = " + Vehicle_Id + ";";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			statement.executeQuery(query);
			return true;
		} 


		catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} 
	}
}