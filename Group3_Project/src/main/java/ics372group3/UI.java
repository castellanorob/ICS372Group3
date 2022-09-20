package ics372group3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import com.google.gson.*;

public class UI {

	private static Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.LAZILY_PARSED_NUMBER).create();
	public static DealerList dealerList = new DealerList();

	public static void main(String[] args) throws FileNotFoundException {

		readJSON();

		/*
		 * Prompt the user to read in a JSON file, add a vehicle, etc.
		 * Create a switch statement to select the correct system option based on
		 * the value entered by the user
		 */

		// System.out.println("Welcome to the Dealership Tracking System");

		Scanner enteredValue = new Scanner(System.in);
		// String userEntry;
		// do {
		// 	printUI();
		// 	userEntry = enteredValue.next();
		// 	switch(userEntry) {
		// 		case "1": 
		// 			readJSON();
		// 			break;
		// 		case "2": // add a vehicle
		// 			addVehicle();
		// 			break;
		// 		case "3": // enable dealer vehicle acquisition
		// 			System.out.println("Enter the ID of the dealer you would like to enable acquisition for: ");

		// 			int enabledId = enteredValue.nextInt();

		// 			dealerAcquisition(dealerList, enabledId, true);

		// 			break;
		// 		case "4": // disable dealer vehicle acquisition
		// 			System.out.println("Enter the ID of the dealer you would like to disable acquisition for: ");

		// 			int disabledId = enteredValue.nextInt();

		// 			dealerAcquisition(dealerList, disabledId, false);
		// 			break;

		// 		case "5":
		// 			System.out.println("Goodbye.");
		// 			System.exit(0);
		// 		default:
		// 			System.out.println("\n~~~ Error: valid option not selected.");
		// 			break;

		// 	}
		// } while(userEntry != "5");

		// Closes scanner at end of main.
		enteredValue.close();
	}

	private static void printUI() {
		System.out.println("Please select from the following options: ");
		System.out.println("Enter " + '"' + 1 + '"' + " to read in a JSON file to add vehicle information to the system.");
		System.out.println("Enter " + '"' + 2 + '"' + " to add an incoming vehicle into the system.");
		System.out.println("Enter " + '"' + 3 + '"' + " to enable dealer vehicle acquisition.");
		System.out.println("Enter " + '"' + 4 + '"' + " to disable dealer vehicle acquisition.");
		System.out.println("Enter " + '"' + 5 + '"' + " to quit");
	}

	// Reads user selected file and parses into json objects.
	public static void readJSON() {

		// Opens file chooser for user, defaults to current directory.
		JButton opener = new JButton();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fileChooser.setDialogTitle("Choose car inventory json file to import");
		fileChooser.showOpenDialog(opener);
		File jsonFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
		String jsonFileName = jsonFile.getName();

		// Takes vehicle array and parses to Json objects. Calls import method.
		try {
			Reader reader = Files.newBufferedReader(Paths.get(jsonFileName));
			Map<?, ArrayList<?>> map = gson.fromJson(reader, Map.class);
			ArrayList inventory = map.entrySet().iterator().next().getValue();
			
			int check = 0;
			//imports json vehicles into Vehicle objects
			for (Object object : inventory) {
				String jsonObject = gson.toJson(inventory.get(inventory.indexOf(object)));
				jsonObject = jsonObject.replace(".", "").replace("E12", "");
				Vehicle vehicle = gson.fromJson(jsonObject, Vehicle.class);
				importVehicle(vehicle);
				check++;
			}

			// User feedback on import
			if (check == inventory.size()){
				System.out.println("Import successful.");
			} else {
				System.out.println("~~~ Error: Import may be missing information.");
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Imports json objects into Vehicle objects, created dealers as necessary. 
	public static void importVehicle(Vehicle vehicle) {
		if (!dealerList.dealerExistAuto(vehicle.getDealerId())){
			dealerList.addDealer(new Dealer(vehicle.getDealerId()));
		} 
		dealerList.addDealerVehicleAuto(vehicle.getDealerId(), vehicle);
		System.out.println("Acquisition Date: " + vehicle.getAcquisitionDate());
	}

	public static void addVehicle() {

		// Create a new vehicle
		Vehicle userAddedVehicle = new Vehicle(0, null, null, null, null, 0, null);

		// Create a scanner to take user input
		Scanner enteredValue = new Scanner(System.in);

		// Prompt user for dealership ID, add dealership ID from user input
		System.out.println("Enter the dealership ID: ");
		System.out.println(" ");

		// Check if dealer exists first

		userAddedVehicle.setDealerId(enteredValue.nextInt());
		enteredValue.nextLine();

		System.out.println("You entered: " + userAddedVehicle.getDealerId() + " for the dealership ID.");
		System.out.println(" ");

		// Prompt user for vehicle_type, add vehicle_type from user input
		System.out.println("Enter the vehicle type: ");
		System.out.println("Note: the type must be suv, sedan, pickup, or sports car.");
		System.out.println(" ");

		userAddedVehicle.setType(enteredValue.nextLine());

		// Prompt user for vehicle_manufacturer, add vehicle_manufacturer from user
		// input
		System.out.println("Enter the vehicle manufacturer: ");
		System.out.println(" ");

		userAddedVehicle.setManufacturer(enteredValue.next());

		// Prompt user for vehicle_model, add vehicle_model from user input
		System.out.println("Enter the vehicle model: ");
		System.out.println(" ");

		userAddedVehicle.setModel(enteredValue.next());

		// Prompt user for vehicle_id, add vehicle_id from user input
		System.out.println("Enter the vehicle ID: ");
		System.out.println(" ");

		userAddedVehicle.setId(enteredValue.next());

		// Prompt user for price, add price from user input
		System.out.println("Enter the vehicle price: ");
		System.out.println(" ");

		userAddedVehicle.setPrice(enteredValue.nextInt());

		// Close the scanner, since no more user entered data is needed
		enteredValue.close();

		// Set the acquisition_date using the Java Date class, based on milliseconds
		// long millis = System.currentTimeMillis();
		// Date date = new Date(millis);
		// userAddedVehicle.setAcquisitionDate(date);

		// Print the vehicle object to the console
		System.out.println("You entered the following vehicle information: ");
		System.out.println(" ");

		System.out.println(userAddedVehicle.toString());
	}

	public static void dealerAcquisition(DealerList dealerList, int id, boolean status) {

		// Check the id against ids that are already in the system to confirm if id
		// exists

		if (dealerList.dealerExistAuto(id)) {
			dealerList.setAcquisition(id, status);
		} else {
			System.out.println("~~~ Error: dealer " + id + " could not be found.");
		}

		// If id exists, call getAcquisition method

		/*
		 * If getAcquisition return value differs from status, call
		 * setAcquisitionEnabled method
		 * and pass the status variable to the method to set acquisition status
		 */

	}

}