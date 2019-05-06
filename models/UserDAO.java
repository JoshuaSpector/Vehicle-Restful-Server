//UserDAO - Slightly different SQL functionality to VehicleDAO to support login/password hashing functions

package models;
import java.sql.*;

import javax.xml.bind.DatatypeConverter;

import java.security.*;


/**
 * The Class UserDAO.
 */
public class UserDAO {

	/** The db connection. */
	public static Connection dbConnection = null;
	
	/** The resultset. */
	public static ResultSet resultset = null;
	
	/** The statement. */
	public static Statement statement = null;

	/**
	 * Instantiates a new user DAO.
	 */
	public UserDAO()
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
	 * @param password 
	 * @return the boolean
	 */
	//Check if username/password pair exist in database - for login
	public Boolean verifyUser(String username, String password)
	{
		String query = "SELECT * FROM users WHERE username = " + "'" + username + "'" + " AND password = " + "'" + password + "'" + ";";
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
	 * Verify user without password.
	 *
	 * @param username 
	 * @return the boolean
	 */
	//Same as above but without password - for forgot password functionality
	public Boolean verifyUserWithoutPassword(String username)
	{
		String query = "SELECT * FROM users WHERE username = " + "'" + username + "'" + ";";
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
	public User getUser(String usernameInput)
	{
		
		String query = "SELECT * FROM users WHERE username = " + "'" + usernameInput + "'" + ";";
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println(query);
			// execute SQL query
			resultset = statement.executeQuery(query);
			int id = resultset.getInt("id");
			String username = resultset.getString("username");
			String password = resultset.getString("password");
			User u = new User(username, password, id);
			closeConnection();
			return u;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			

		} 
		return null;
	}
	
	/**
	 * Insert user.
	 *
	 * @param username 
	 * @param password 
	 * @param id 
	 * @return the boolean
	 */
	public Boolean insertUser(String username, String password, int id) //Insert user - not actually used in the project 
	{
		closeConnection(); 
		String query = "INSERT INTO users (username, password, id)"
				+ "\n VALUES (" + "'" + username + "'"  + ", " + "'" + password  + "'" + ", " + id + ")";
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
	 * @param password 
	 * the no such algorithm exception
	 */
	//Update user - note the use of the hash String method to hash the password as it enters the Dbase
	public void updateUser(String username, String password ) throws NoSuchAlgorithmException 
	{
		closeConnection(); 
		try {
			statement.close(); //avoid database locking
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String query = "Update users"
				+"\n SET username = '" +username + "'" + ", " +
				" password = '" + hash(password) + "'" +
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
	 * @param password 
	 * @return string
	 * @throws NoSuchAlgorithmException 
	 */
	public String hash(String password) throws NoSuchAlgorithmException //Hash - uses messagedigest and datatypeconverter imports
	{

		MessageDigest hash = MessageDigest.getInstance("MD5"); //Set messagedigest object to MD5 hash algorithm
		hash.update(password.getBytes()); //hash the password
		byte[] digest = hash.digest(); //declare object containing the hashed password
		return DatatypeConverter.printHexBinary(digest).toUpperCase(); //convert the hashed password to the correct format and return it as a string

	}

}