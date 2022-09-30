/* ICS 372 group 3 Project
 * Full description in README
 */ 

package ics372group3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import com.google.gson.*;

public class UI {

	private static Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.LAZILY_PARSED_NUMBER).create();
	public static DealerList dealerList = new DealerList();
	public static Scanner enteredValue = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {/*
		* Prompt the user to read in a JSON file, add a vehicle, etc.
		* Create a switch statement to select the correct system option based on
		* the value entered by the user
		*/

		readJSON();
		System.out.println("Welcome to the Dealership Tracking System");
		callUI();
		
	}

	public static void callUI() {
		String userEntry;
		do {
			printUIoptions();
			userEntry = enteredValue.next();
			switch(userEntry) {
				case "1": // add a vehicle
					addVehicle();
					break;

				case "2": // enable dealer vehicle acquisition
					System.out.println("Enter the ID of the dealer you would like to enable acquisition for: ");
					int enabledId = enteredValue.nextInt();
					dealerAcquisition(dealerList, enabledId, true);
					break;

				case "3": // disable dealer vehicle acquisition
					System.out.println("Enter the ID of the dealer you would like to disable acquisition for: ");
					int disabledId = enteredValue.nextInt();
					dealerAcquisition(dealerList, disabledId, false);
					break;

				case "4": // print full dealer list inventory
					System.out.println("\n");
					dealerList.printFullInventory();
					System.out.println("\n");
					break;

				case "5": // exports single dealer to json file
					try {
						dealerList.exportDealer();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					break;

				case "6": // terminates program
					System.out.println("Goodbye.");
					System.exit(0);

				default:
					System.out.println("\n~~~ Error: valid option not selected.");
					break;

			}
		} while(userEntry != "6");
		enteredValue.close();
	}

	private static void printUIoptions() {
		System.out.println("Please select from the following options: ");
		System.out.println("Enter " + '"' + 1 + '"' + " to add an incoming vehicle into the system.");
		System.out.println("Enter " + '"' + 2 + '"' + " to enable dealer vehicle acquisition.");
		System.out.println("Enter " + '"' + 3 + '"' + " to disable dealer vehicle acquisition.");
		System.out.println("Enter " + '"' + 4 + '"' + " to print the current inventory");
		System.out.println("Enter " + '"' + 5 + '"' + " to export a dealer to a file");
		System.out.println("Enter " + '"' + 6 + '"' + " to quit");
	}

	// Reads user selected file and parses into json objects.
	public static void readJSON() {

		// Opens file chooser for user, defaults to current directory.
		JButton opener = new JButton();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setSelectedFile(new File(System.getProperty("user.dir")));
		fileChooser.setDialogTitle("Choose car inventory json file to import");
		fileChooser.showOpenDialog(opener);
		File jsonFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
		String jsonFileName = jsonFile.getAbsolutePath();

		// Takes vehicle array and parses to Json objects. Calls import method.
		try {
			Reader reader = Files.newBufferedReader(Paths.get(jsonFileName));
			Map<?, ArrayList<?>> map = gson.fromJson(reader, Map.class);
			ArrayList<?> inventory = map.entrySet().iterator().next().getValue();
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
				System.out.println("Import successful.\n");
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
	}


	// DESCRIBE METHOD HERE 
	public static void addVehicle() {
	    
	    int dealerID, price;
	    String type, manufacturer, model, id, acquisitionDate;
		Scanner enteredValue = new Scanner(System.in);
		System.out.println("\n---------------------------------------------");
		System.out.println("Adding Vehicle");
		System.out.println("---------------------------------------------");

		System.out.println("Enter the dealership ID: ");

		dealerID = enteredValue.nextInt();
		enteredValue.nextLine();

		type = manualTypeCheck();

		System.out.println("Enter the vehicle manufacturer: ");

		manufacturer = enteredValue.nextLine();

		System.out.println("Enter the vehicle model: ");

		model = enteredValue.nextLine();

		System.out.println("Enter the vehicle ID: ");

		id = enteredValue.nextLine();

		System.out.println("Enter the vehicle price: ");

		price = enteredValue.nextInt();
		enteredValue.nextLine();

        System.out.println("Enter the vehicle acquisition date: ");

        acquisitionDate = enteredValue.nextLine();

		dealerList.addVehicle(dealerID,type,manufacturer,model,id,price,acquisitionDate);
		dealerList.printFullInventory();
		System.out.println("---------------------------------------------\n");
		// Set the acquisition_date using the Java Date class, based on milliseconds
		// long millis = System.currentTimeMillis();
		// Date date = new Date(millis);
		// userAddedVehicle.setAcquisitionDate(date);
	}

	private static String manualTypeCheck(){
		Scanner enteredValue = new Scanner(System.in);
		// namespaces
		List<String> types = Arrays.asList("suv", "sedan", "pickup", "sports car");
		System.out.println("Enter the vehicle type: ");
		System.out.println("Note: the type must be suv, sedan, pickup, or sports car.");
		//do{ enteredValue.nextLine() }while{type not in list <<create above>>}
		String type = enteredValue.nextLine();
		if (types.contains(type)){
			return type;
		}
		else {
			System.out.println("~~~ Error: invalid type, please choose a valid type.");
			type = manualTypeCheck();
		}
		return type;
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