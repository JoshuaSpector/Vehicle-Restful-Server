//Main Controller - Controls menuController and ServerController
//joshua spector 17089600
import java.sql.SQLException;
import java.util.Scanner;


/**
 * Main Controller.
 */
public class Controller {
	
	/** Scanner */
	static Scanner sc = new Scanner(System.in);


	/**
	 * The main method.
	 *
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			selectInterface();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}
	
	/**
	 * Select interface (console/front end)
	 *
	 * @throws SQLException 
	 */
	static void selectInterface() throws SQLException //Switch statement selecting between console or web front-ends
	{
		System.out.println("Hello User! Welcome to Joshua Spector's Advanced Programming Vehicle Assignment."
				+ "\nPlease press A to access the system via the console menu, or B to run the server and access the web front-end"
				+ "\n(Web homepage: http://localhost:8005/vehicles/home");
		String menu = sc.nextLine();


		switch(menu)
		{
		case "A": 
			//console menu system: 
			menuController myMenu = new menuController();
			myMenu.menuSelect();
			break;
		case "B":
			//run server:
			serverController myServer = new serverController();

			try {
				myServer.serverMain();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

}