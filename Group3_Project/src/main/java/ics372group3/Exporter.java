package ics372group3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import com.google.gson.*;

public class Exporter {

	private static DealerList dealerList = UIController.dealerList;
	private static Gson exportGson = new GsonBuilder().setPrettyPrinting().create();
	private static PrintWriter output;

	// For single dealer exporting
	public static void exportDealerJson(String dealerID) throws FileNotFoundException {

		for (Dealer dealer : dealerList.getDealerList()) {
			if (dealerID.equals(dealer.getDealerId())) {
				String dealerName = dealer.getName();
				File exportedFile = new File(dealerID + ".json");
				output = new PrintWriter(exportedFile);
				System.out.println("... Exporting inventory as " + dealerID + ".json\n");
				output.println("{\n\"dealer_inventory\":[");
				for (Vehicle vehicle : dealer.getInventory()) {
					String vString = exportGson.toJson(vehicle);
					vString = vString.substring(1, vString.length() - 1).trim() + ",\n\"dealership_name\": \""
							+ dealerName + "\"";
					output.print("{" + vString + "\n}");
					if (!(dealer.getInventory().indexOf(vehicle) == dealer.getInventory().size() - 1)) {
						output.println(",");
					}
				}
				output.println("]\n}");
				output.close();
				return;
			}
		}
	}

	//Exporting master save file including all dealers 
	public static void exportSaveFile() throws FileNotFoundException {
		File exportedFile = new File(Start.SAVE_FILE);
		output = new PrintWriter(exportedFile);
		output.println("{\n\"master_inventory\":[");
		for (Dealer dealer : dealerList.getDealerList()) {
			String dealerName = dealer.getName();
			for (Vehicle vehicle : dealer.getInventory()) {
				String vString = exportGson.toJson(vehicle);
				vString = vString.substring(1, vString.length() - 1).trim() + ",\n\"dealership_name\": \"" + dealerName
						+ "\"" + ",\n\"dealers_acquisition\": " + dealer.getAcquisitionEnabled();
				output.print("{" + vString + "\n}");
				if (!(dealer.getInventory().indexOf(vehicle) == dealer.getInventory().size() - 1)) {
					output.println(",");
				}
			}
			if (!(dealerList.getDealerList().indexOf(dealer) == dealerList.getDealerList().size() - 1)) {
				output.println(",");
			}
		}
		output.println("\n]\n}");
		output.close();
		return;
	}
}
