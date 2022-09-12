package com.gson;
import com.google.gson.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Admin {
	// This method adds a car dealer to the Dealer list
		public int addDealer(int dealerID) {
			
			return 0;
		}
		
		// This method reads in a JSON file using the GSON library
		public static void main(String[] args) {
			try {
			      File myObj = new File("C:\\\\Users\\\\caste\\\\OneDrive\\\\Desktop\\Project1_input.json");
			      Scanner myReader = new Scanner(myObj);
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
			
		

		// This method prints the full inventory to JSON files
		public void printFullInventory() {
			
		}
		
}
