package ics372group3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import com.google.gson.*;

public class Exporter {

	private static DealerList dealerList = UI.dealerList;
	private static Gson exportGson = new GsonBuilder().setPrettyPrinting().create();
	private static PrintWriter output;
	private static Scanner scanner = UI.scanner;

	public static void exportDealerJson() throws FileNotFoundException {
		System.out.println("Enter ID of dealer to be exported (type \"0\" to cancel): ");
		int inputDealerID = scanner.nextInt();
		if (inputDealerID == 0) {
			System.out.println("");
			return;
		}
		for (Dealer dealer : dealerList.getDealerList()) {
			if (inputDealerID == dealer.getDealerId()) {
				String dealerName = dealer.getName();
				File exportedFile = new File(inputDealerID + ".json");
				output = new PrintWriter(exportedFile);
				System.out.println("... Exporting inventory as " + inputDealerID + ".json\n");
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
				scanner.nextLine();
				return;
			}
		}
		System.out.println("\n~~~ Error: Dealer not found. Please re-enter a dealer ID.\n");
		exportDealerJson();
	}

	public static void exportSaveFile() throws FileNotFoundException {
		File exportedFile = new File("MASTER_SAVE_FILE.json");
		output = new PrintWriter(exportedFile);
		output.println("{\n\"master_inventory\":[");
		for (Dealer dealer : dealerList.getDealerList()) {
			String dealerName = dealer.getName();
			for (Vehicle vehicle : dealer.getInventory()) {
				String vString = exportGson.toJson(vehicle);
				vString = vString.substring(1, vString.length() - 1).trim() + ",\"dealership_name\": \"" + dealerName
						+ "\"";
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
