//UserDAO - Slightly different SQL functionality to VehicleDAO to support login/apikey hashing functions

package models;
import java.sql.*;

import javax.xml.bind.DatatypeConverter;

import java.security.*;


/**
 * The Class UserDAO.
 */
public class DeveloperDAO {

	/** The db connection. */
	public static Connection dbConnection = null;
	
	/** The resultset. */
	public static ResultSet resultset = null;
	
	/** The statement. */
	public static Statement statement = null;

	/**
	 * Instantiates a new user DAO.
	 */
	public DeveloperDAO()
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
	 * Verify user.
	 *
	 * @param username 
	 * @param apikey 
	 * @return the boolean
	 */
	//Check if username/apikey pair exist in database - for login
	public Boolean verifyUser(String username, String apikey)
	{
		String query = "SELECT * FROM developers WHERE username = " + "'" + username + "'" + " AND apikey = " + "'" + apikey + "'" + ";";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			// execute SQL query
			resultset = statement.executeQuery(query);
			
			if (resultset.isClosed() == false )
			{closeConnection(); 
			return true;}
			else {closeConnection(); 
			return false;}


		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} 
	}
	
	/**
	 * Verify user without apikey.
	 *
	 * @param username 
	 * @return the boolean
	 */
	public Boolean verifyDeveloperWithoutUser(String apikey)
	{
		String query = "SELECT * FROM developers WHERE apikey = " + "'" + apikey + "'" + ";";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			// execute SQL query
			resultset = statement.executeQuery(query);
			//statement.close(); 
			
			if (resultset.isClosed() == false )
			{closeConnection(); 
			return true;}
			else {closeConnection(); 
			return false;}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			closeConnection();
			return false;
		} 

	}
	//Same as above but without apikey - for forgot apikey functionality
	public Boolean verifyDeveloperWithoutKey(String username)
	{
		String query = "SELECT * FROM developers WHERE username = " + "'" + username + "'" + ";";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			// execute SQL query
			resultset = statement.executeQuery(query);
			//statement.close(); 
			
			if (resultset.isClosed() == false )
			{closeConnection(); 
			return true;}
			else {closeConnection(); 
			return false;}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			closeConnection();
			return false;
		} 

	}

	/**
	 * Gets the user.
	 *
	 * @param usernameInput 
	 * @return the user
	 */
	//get user functionality
	public Developer getDeveloper(String usernameInput)
	{
		
		String query = "SELECT * FROM developers WHERE username = " + "'" + usernameInput + "'" + ";";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			// execute SQL query
			resultset = statement.executeQuery(query);
			String username = resultset.getString("username");
			String apikey = resultset.getString("apikey");
			Developer d = new Developer(username, apikey);
			closeConnection();
			return d;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			

		} 
		return null;
	}
	
	/**
	 * Insert user.
	 *
	 * @param username 
	 * @param apikey 
	 * @return the boolean
	 */
	public Boolean insertDeveloper(String username, String apikey) //Insert user - not actually used in the project 
	{
		closeConnection(); 
		String query = "INSERT INTO developers (username, apikey)"
				+ "\n VALUES (" + "'" + username + "'"  + ", " + "'" + apikey  + "'" + ")";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			// execute SQL query
			statement.executeQuery(query);
			closeConnection();
			return true;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			closeConnection();
			return false;
		} 

	}

	/**
	 * Update user.
	 *
	 * @param username 
	 * @param apikey 
	 * the no such algorithm exception
	 */
	//Update user - note the use of the hash String method to hash the apikey as it enters the Dbase
	public void updateDeveloper(String username, String apikey ) throws NoSuchAlgorithmException 
	{
		closeConnection(); 
		try {
			statement.close(); //avoid database locking
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String query = "Update developers"
				+"\n SET username = '" +username + "'" + ", " +
				" apikey = '" + hash(apikey) + "'" +
				"\nWHERE username = " + "'" + username + "'" + ";";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			// execute SQL query
			statement.executeQuery(query);
			closeConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 

	}
	
	/**
	 * Hash.
	 *
	 * @param apikey 
	 * @return string
	 * @throws NoSuchAlgorithmException 
	 */
	public String hash(String username) throws NoSuchAlgorithmException //Hash - uses messagedigest and datatypeconverter imports
	{

		MessageDigest hash = MessageDigest.getInstance("MD5"); //Set messagedigest object to MD5 hash algorithm
		hash.update(username.getBytes()); //hash the username
		byte[] digest = hash.digest(); //declare object containing the hashed username
		return DatatypeConverter.printHexBinary(digest).toUpperCase(); //convert the hashed username to the correct format and return it as a string

	}

}