package ics372group3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.net.Proxy.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class UI {

	public static void main(String[] args) throws FileNotFoundException {
		
		// Dealer Id Enabled/Disabled
		boolean dealerIdStatus = false;
		
		// Prompt the user to read in a JSON file, add a vehicle, etc.
		System.out.println("Welcome to the Dealership Tracking System");
		System.out.println("Please select from the following options: ");
		System.out.println("Enter " + '"' + 1 + '"' + " to read in a JSON file to add vehicle information to the system.");
		System.out.println("Enter " + '"' + 2 + '"' + " to add an incoming vehicle into the system.");
		System.out.println("Enter " + '"' + 3 + '"' + " to enable dealer vehicle acquisition.");
		System.out.println("Enter " + '"' + 4 + '"' + " to disable dealer vehicle acquisition.");
		
		// Create a Scanner object to capture the user's input
		Scanner enteredValue = new Scanner (System.in);
						
		// Read in the user's selection
		int userEntry = enteredValue.nextInt();
				
		/* Create a switch statement to select the correct system option based on 
		 * the value entered by the user
		 */
		switch(userEntry) {
			case 1: // read in a JSON file
				System.out.println("Enter the file path for your file");
				System.out.println(" ");
						
				String filePath = enteredValue.next();
						
				readJSON(filePath);
				break;
			case 2: // add a vehicle
				addVehicle();
				break;
			case 3: // enable dealer vehicle acquisition
				System.out.println("Enter the number of the dealer you would like to enable acquisition for: ");
				
				int enabledId = enteredValue.nextInt();
				dealerIdStatus = true;
				
				dealerAcquisition(enabledId, dealerIdStatus);
				
				break;
			case 4: // disable dealer vehicle acquisition
				System.out.println("Enter the number of the dealer you would like to disable acquisition for: ");
				
				int disabledId = enteredValue.nextInt();
				dealerIdStatus = false;
				
				dealerAcquisition(disabledId, dealerIdStatus);
				break;
				}
				
			}
			
		/* This method reads in a JSON file using the GSON library.
		 * Currently, we are only reading in the JSON file to a single String and then printing
		 * it to the console when the method is called. GSON functionality, parsing, and object
		 * creation is forthcoming.
		 */
		public static void readJSON(String filePath)  {
				
			try {
				 File newJSONFile = new File(filePath);
				 Scanner myReader = new Scanner(newJSONFile);
				 while (myReader.hasNextLine()) {
				 String data = myReader.nextLine();

				 
				 // Vehicle[] vehicles = new Gson().fromJson(data, Vehicle[].class);
				 
				 // Type listType = new TypeToken<ArrayList<Vehicle>>() {}.getType();
				 
				  Inventory inventory = new Gson().fromJson(data, Inventory.class);
				 
				 // System.out.println(data);
				}
				 
				 myReader.close();
				 
				} catch (FileNotFoundException e) {
				   System.out.println("An error occurred.");
				   e.printStackTrace();
				}
				
			}
				 
				 	
		public static void addVehicle() {
			
			// Create a new vehicle
			Vehicle userAddedVehicle = new Vehicle(0, null, null, null, null, 0, 0);
			
			// Create a scanner to take user input
			Scanner enteredValue = new Scanner (System.in);
			
			// Prompt user for dealership ID, add dealership ID from user input
			System.out.println("Enter the dealership ID: ");
			System.out.println(" ");
			
			userAddedVehicle.setDealerId(enteredValue.nextInt());
			enteredValue.nextLine();
			
			System.out.println("You entered: " + userAddedVehicle.getDealerId() + " for the dealership ID.");
			System.out.println(" ");
			
			// Prompt user for vehicle_type, add vehicle_type from user input
			System.out.println("Enter the vehicle type: ");
			System.out.println("Note: the type must be suv, sedan, pickup, or sports car.");
			System.out.println(" ");
			
			userAddedVehicle.setType(enteredValue.nextLine());
			
			// Prompt user for vehicle_manufacturer, add vehicle_manufacturer from user input
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
			long millis = System.currentTimeMillis();
			Date date = new Date(millis);
			userAddedVehicle.setAquisitionDate(date);
			
			// Print the vehicle object to the console
			System.out.println("You entered the following vehicle information: ");
			System.out.println(" ");
			
			System.out.println(userAddedVehicle.toString());
		}
		
		public static void dealerAcquisition(int id, boolean status) {
			
			DealerList dealerList = new DealerList();
			
			// Check the id against ids that are already in the system to confirm if id exists
			dealerList.dealerExist(id);
			
			// If id exists, call getAcquisition method
			
			/* If getAcquisition return value differs from status, call setAcquisitionEnabled method
			 * and pass the status variable to the method to set acquisition status
			 */
			
			
		}
			 
}