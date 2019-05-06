//Menu controller - selects between different VehicleDAO crud functions

import java.util.Scanner;
import models.Vehicle;
import models.VehicleDAO;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *  console menuController.
 */
public class menuController {
	
	/** The sc. */
	Scanner sc = new Scanner(System.in);


	/**
	 * Menu select - take user input and choose between different CRUD operations
	 *
	 * @throws SQLException 
	 */
	void menuSelect() throws SQLException
	{
		int vehicle_id; String make; String model; int year;
		int price; String license_number; String colour; int number_doors;
		String transmission; int mileage; String fuel_type;
		int engine_size; String body_style; String condition; String notes;

		VehicleDAO dao = new VehicleDAO();
		String s = "Welcome user"
				+"\n Menu"
				+"\n Please Input A/B/C/D/E "
				+"\n A: View Vehicle"
				+"\n B: View All Vehicles"
				+"\n C: Insert Record"
				+"\n D: Delete Record"
				+"\n E: Update Record";
		System.out.println(s);
		String menu = sc.nextLine();


		switch(menu)
		{
		case "A": //get using vehicle id
			System.out.println("Please input a valid vehicle id");
			vehicle_id = sc.nextInt();
			Vehicle v = dao.getVehicle(vehicle_id);
			System.out.println(v.toString());
			break;

		case "B": //get all vehicles

			ArrayList<Vehicle> allV = dao.getAllVehicles();

			for(Vehicle v2 : allV) { //iterating over a collection
				System.out.println(v2.toString());
			}


			break;
		case "C": //insert - takes user input of different variables and inserts a vehicle

			System.out.println("To insert a new record, please input the following information:");		
			System.out.println("Vehicle Id:");
			vehicle_id = Integer.parseInt(sc.nextLine());
			System.out.println("Make:");
			make = sc.nextLine();
			System.out.println("Model:");
			model = sc.nextLine();
			System.out.println("Year:");
			year = Integer.parseInt(sc.nextLine());
			System.out.println("Price:");
			price = Integer.parseInt(sc.nextLine());
			System.out.println("License Number:");
			license_number = sc.nextLine();
			System.out.println("Colour:");
			colour = sc.nextLine();
			System.out.println("Number of Doors:");
			number_doors = Integer.parseInt(sc.nextLine());
			System.out.println("Transmission:");
			transmission = sc.nextLine();
			System.out.println("Mileage:");
			mileage = Integer.parseInt(sc.nextLine());
			System.out.println("Fuel Type:");
			fuel_type = sc.nextLine();
			System.out.println("Engine Size:");
			engine_size = Integer.parseInt(sc.nextLine());
			System.out.println("Body Style:");
			body_style = sc.nextLine();
			System.out.println("Condition:");
			condition = sc.nextLine();
			System.out.println("Notes:");
			notes = sc.nextLine();
			System.out.println("Thank you, your record has been created");

			Vehicle v3 = new Vehicle(vehicle_id, make, model,
					year, price, license_number, colour,
					number_doors, transmission, mileage, fuel_type,
					engine_size, body_style, condition, notes);
			try {
				dao.InsertVehicle(v3);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		case "D": //deletes based on id
			System.out.println("Please input a valid vehicle id");
			vehicle_id = sc.nextInt();
			try {
				dao.deleteVehicle(vehicle_id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "E": //update - similar as insert but uses a search_id variable to find the vehicle to update
			System.out.println("To update an existing record, please input the following information");		
			System.out.println("Id of the record you would like to update:");
			int search_id = Integer.parseInt(sc.nextLine());
			System.out.println("New Vehicle Id:");
			vehicle_id = Integer.parseInt(sc.nextLine());
			System.out.println("Make:");
			make = sc.nextLine();
			System.out.println("Model:");
			model = sc.nextLine();
			System.out.println("Year:");
			year = Integer.parseInt(sc.nextLine());
			System.out.println("Price:");
			price = Integer.parseInt(sc.nextLine());
			System.out.println("License Number:");
			license_number = sc.nextLine();
			System.out.println("Colour:");
			colour = sc.nextLine();
			System.out.println("Number of Doors:");
			number_doors = Integer.parseInt(sc.nextLine());
			System.out.println("Transmission:");
			transmission = sc.nextLine();
			System.out.println("Mileage:");
			mileage = Integer.parseInt(sc.nextLine());
			System.out.println("Fuel Type:");
			fuel_type = sc.nextLine();
			System.out.println("Engine Size:");
			engine_size = Integer.parseInt(sc.nextLine());
			System.out.println("Body Style:");
			body_style = sc.nextLine();
			System.out.println("Condition:");
			condition = sc.nextLine();
			System.out.println("Notes:");
			notes = sc.nextLine();
			System.out.println("Thank you, your update has been logged");
			Vehicle v4 = new Vehicle(vehicle_id, make, model,
					year, price, license_number, colour,
					number_doors, transmission, mileage, fuel_type,
					engine_size, body_style, condition, notes);
			try {
				dao.updateVehicle(search_id, v4);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}

	}
}
