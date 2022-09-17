package ics372group3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class UI {

	public static void main(String[] args) throws FileNotFoundException {
		
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
				
		// Allow userEntry to be used in the switch statement
		int option = userEntry;
				
		// Placeholder to confirm which value the user entered
		// System.out.println("You entered: " + option);
				
		/* Create a switch statement to select the correct system option based on 
		 * the value entered by the user
		 */
		switch(option) {
			case 1: 
				System.out.println("Enter the file path for your file");
				System.out.println(" ");
						
				String filePath = enteredValue.next();
						
				readJSON(filePath);
				break;
			case 2: 
				addVehicle();
				break;
			case 3: // enable dealer vehicle acquisition
				break;
			case 4: // disable dealer vehicle acquisition
				break;
				}
				
			}
			
		/* This method reads in a JSON file using the GSON library.
		 * Currently, we are only reading in the JSON file to a single String and then printing
		 * it to the console when the method is called. GSON functionality, parsing, and object
		 * creation is forthcoming.
		 */
		public static void readJSON(String filePath)  {
				
//			try {
//				 File newJSONFile = new File(filePath);
//				 Scanner myReader = new Scanner(newJSONFile);
//				 while (myReader.hasNextLine()) {
//				 String data = myReader.nextLine();
//				 System.out.println(data);
//				}
//				 myReader.close();
//				 } catch (FileNotFoundException e) {
//				   System.out.println("An error occurred.");
//				   e.printStackTrace();
//				 }
				 
				 
			// Create a reader
			try {
					
				// Create Gson instance
				Gson gson = new Gson();
					
				Reader reader = Files.newBufferedReader(Paths.get(filePath));
					
				List<Vehicle> vehicles = new Gson().fromJson(reader, new TypeToken<List<Vehicle>>() {}.getType());
					
				//Vehicle vehicle = gson.fromJson(reader, Vehicle.class);
					
				// print users
				vehicles.forEach(System.out::println);
					
				// Convert JSON file to map
//				Map<?, ?> map = gson.fromJson(reader, Map.class);
//					
//				// Print map entries
//				for (Map.Entry<?, ?> entry : map.entrySet()) {
//				System.out.println(entry.getKey() + " = " + entry.getValue());
//				}
					
				// Close the reader
				reader.close();
					
				} catch (Exception e) {
					System.out.println("Error: unable to read file from given file path.");
					e.printStackTrace();
				}
				 
				
				 
			}
				 
				 
					
		public static void addVehicle() {
				 
		}
			 
		public static void testMethod() {
			System.out.println("Testing 1 2 3");
		}
}