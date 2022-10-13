/* ICS 372 group 3 Project
 * Full description in README
 */ 

package ics372group3;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UI {

	public static DealerList dealerList = new DealerList();
	public static Scanner enteredValue = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		
		/*
		* Prompt the user to read in a JSON file, add a vehicle, etc.
		* Create a switch statement to select the correct system option based on
		* the value entered by the user
		*/

		Importer.importJSON();
		System.out.println("Welcome to the Dealership Tracking System");
		callUI();
		
	}

	public static void callUI() throws FileNotFoundException {
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
					DealerList.dealerAcquisition(enabledId, true);
					break;

				case "3": // disable dealer vehicle acquisition
					System.out.println("Enter the ID of the dealer you would like to disable acquisition for: ");
					int disabledId = enteredValue.nextInt();
					DealerList.dealerAcquisition(disabledId, false);
					break;

				case "4": // print full dealer list inventory
					System.out.println("\n");
					dealerList.printFullInventory();
					System.out.println("\n");
					break;

				case "5": // exports single dealer to json file
					Exporter.exportJSON();
					break;

				case "6": // terminates program
					enteredValue.close();
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
		System.out.println("Enter " + '"' + 1 + '"' + " to add an incoming vehicle to a dealer");
		System.out.println("Enter " + '"' + 2 + '"' + " to enable dealer vehicle acquisition.");
		System.out.println("Enter " + '"' + 3 + '"' + " to disable dealer vehicle acquisition.");
		System.out.println("Enter " + '"' + 4 + '"' + " to display the current inventory");
		System.out.println("Enter " + '"' + 5 + '"' + " to export a dealer to a file");
		System.out.println("Enter " + '"' + 6 + '"' + " to quit");
	}

	// DESCRIBE METHOD HERE 
	public static void addVehicle() {
	    
	    int dealerID, price;
	    String type, manufacturer, model, id, acquisitionDate;
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

        System.out.println("Enter the vehicle acquisition date: (13 digits)");

        acquisitionDate = enteredValue.nextLine();

		dealerList.addVehicle(dealerID,type,manufacturer,model,id,price,acquisitionDate);
		dealerList.printFullInventory();
		System.out.println("---------------------------------------------\n");
	}

	private static String manualTypeCheck(){
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

}