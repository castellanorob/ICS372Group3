/* ICS 372 group 3 Project
 * Full description in README
 */ 

package ics372group3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class UI extends Application{

	public static DealerList dealerList = new DealerList();
	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		// Call the Launch method for JavaFX
	     launch(args);

		System.out.println("\nWelcome to the Dealership Tracking System!\n");
		callUI();
		
	}
	
	 @Override
     public void start(Stage stage) throws Exception{
	     try {
	         FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/SceneBuilder.fxml"));
	         Parent root = (Parent) loader.load();
	         Scene scene = new Scene(root, Color.DARKGRAY);
	     
	         stage.setTitle("Dealership Tracking System");
	         stage.setHeight(600);
	         stage.setWidth(800);
	         stage.setScene(scene);
	         stage.show();
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
     }

	public static void callUI() throws FileNotFoundException {
		String userEntry;
		do {
			printUIoptions();
			userEntry = scanner.nextLine();
			switch(userEntry) {
				case "1": // add a vehicle
					dealerList.addVehicleManually();
					break;

				case "2": // transfer a vehicle between two dealers
					dealerList.vehicleTransfer();
					break;

				case "3": // enable dealer vehicle acquisition
					System.out.println("Enter the ID of the dealer you would like to enable acquisition for: ");
					int enabledId = scanner.nextInt();
					DealerList.dealerAcquisition(enabledId, true);
					break;

				case "4": // disable dealer vehicle acquisition
					System.out.println("Enter the ID of the dealer you would like to disable acquisition for: ");
					int disabledId = scanner.nextInt();
					DealerList.dealerAcquisition(disabledId, false);
					break;

				case "5": // print full dealer list inventory
					System.out.println("\n");
					dealerList.printFullInventory();
					System.out.println("\n");
					break;

				case "6": // exports single dealer to json file
					Exporter.exportDealerJson();
					break;

				case "7": // terminates program
					Exporter.exportSaveFile();
					System.out.println("Goodbye.");
					System.exit(0);

				default:
					System.out.println("\n~~~ Error: valid option not selected.");
					break;

			}
		} while(userEntry != "7");
	}

	private static void printUIoptions() {
		System.out.println("Please select from the following options: ");
		System.out.println("Enter " + '"' + 1 + '"' + " to add a vehicle to a dealer");
		System.out.println("Enter " + '"' + 2 + '"' + " to transfer a vehicle between dealers.");
		System.out.println("Enter " + '"' + 3 + '"' + " to enable dealer vehicle acquisition.");
		System.out.println("Enter " + '"' + 4 + '"' + " to disable dealer vehicle acquisition.");
		System.out.println("Enter " + '"' + 5 + '"' + " to display the current inventory");
		System.out.println("Enter " + '"' + 6 + '"' + " to export a dealer to a file");
		System.out.println("Enter " + '"' + 7 + '"' + " to quit");
	}


}