package com.gson;
import com.google.gson.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UI {

	public static void main(String[] args) {
		
		// Create a Scanner object to capture the user's input
		Scanner enteredValue = new Scanner (System.in);
		
		// Prompt the user to read in a JSON file, add a vehicle, etc.
		System.out.println("Welcome to the Dealership Tracking System");
		System.out.println("Please select from the following options: ");
		System.out.println("Enter " + '"' + 1 + '"' + " to read in a JSON file to add vehicle information to the system.");
		System.out.println("Enter " + '"' + 2 + '"' + " to add an incoming vehicle into the system.");
		System.out.println("Enter " + '"' + 3 + '"' + " to enable dealer vehicle acquisition.");
		System.out.println("Enter " + '"' + 4 + '"' + " to disable dealer vehicle acquisition.");
		
		// Read in the user's selection
		int userEntry = enteredValue.nextInt();
		
		// Placeholder to confirm which value the user entered
		System.out.println("You entered: " + userEntry);
		
		/* Create a switch statement to select the correct system option based on 
		 * the value entered by the user
		 */
		
		
		// readJSON();
	}
	
	/* This method reads in a JSON file using the GSON library.
	 * Currently, we are only reading in the JSON file to a single String and then printing
	 * it to the console when the method is called. GSON functionality, parsing, and object
	 * creation is forthcoming.
	 */
			public static void readJSON()  {
				try {
				      File newJSONFile = new File("C:\\\\Users\\\\caste\\\\OneDrive\\\\Desktop\\Project1_input.json");
				      Scanner myReader = new Scanner(newJSONFile);
				      while (myReader.hasNextLine()) {
				        String data = myReader.nextLine();
				        System.out.println(data);
				      }
				      myReader.close();
				    } catch (FileNotFoundException e) {
				      System.out.println("An error occurred.");
				      e.printStackTrace();
				    }
			}

}
