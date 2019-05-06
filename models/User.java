//User class - Same as Vehicle with different variables - Used for login/password hashing functionality

package models;



/**
 * The Class User.
 */
public class User {

	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
	
	/** The id. */
	private int id;

	/**
	 * Instantiates a new user.
	 *
	 * @param username the username
	 * @param password the password
	 * @param id the id
	 */
	public User(String username, String password, int id)
	{
		this.username = username;
		this.password = password;
		this.id = id;
	}
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

}