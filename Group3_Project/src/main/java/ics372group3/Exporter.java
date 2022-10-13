package ics372group3;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import com.google.gson.*;



public class Exporter {

    private static Gson exportGson = new GsonBuilder().setPrettyPrinting().create();

    
    public static void exportJSON(DealerList dealerList) throws FileNotFoundException {
        Scanner scanner = UI.enteredValue;
		PrintWriter output;
        System.out.println("Enter ID of dealer to be exported (type \"0\" to cancel): ");
        int inputDealerID = scanner.nextInt();
		if (inputDealerID == 0){
            System.out.println("");
            return;
        }
        for (Dealer dealer : dealerList.getDealerList()){
            if (inputDealerID == dealer.getDealerId()){
                File exportedFile = new File(inputDealerID + ".json");
				output = new PrintWriter(exportedFile);
				System.out.println("... Exporting inventory as " + inputDealerID + ".json\n");
				output.println("{\n\"dealer_inventory\":[");
				for (Vehicle vehicle : dealer.getInventory()){
					String vString = exportGson.toJson(vehicle);
					output.print(vString);
					if (!(dealer.getInventory().indexOf(vehicle) == dealer.getInventory().size()-1)){
						output.println(",");
					} else {
						output.println("");
					}
				}
				output.println("]\n}");
				output.close();
                return;
            }
        }
        System.out.println("\n~~~ Error: Dealer not found. Please re-enter a dealer ID.\n");
		exportJSON(dealerList);
	}
}
