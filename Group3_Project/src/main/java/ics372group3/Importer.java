package ics372group3;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import com.google.gson.*;

public class Importer {

    private static DealerList dealerList = UI.dealerList;
	private static Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();


    // Reads user selected file and parses into json objects.
	public static void importJSON() {

		String fileName = openFileChooser();

		// Takes vehicle array and parses to Json objects. Calls importVehicle method on each object.
		try {
			Reader reader = Files.newBufferedReader(Paths.get(fileName));
			Map<?, ArrayList<?>> map = gson.fromJson(reader, Map.class);
			ArrayList<?> inventory = map.entrySet().iterator().next().getValue();
			int check = 0;

			//imports json vehicles into Vehicle objects
			for (Object object : inventory) {
				String jsonObject = gson.toJson(inventory.get(inventory.indexOf(object)));
				jsonObject = jsonObject.replace(".", "").replace("E12", "");
				Vehicle vehicle = gson.fromJson(jsonObject, Vehicle.class);
				vehicle.setPrice(vehicle.getPrice() / 10);
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

	public static void importXML() {
		
		String fileName = openFileChooser();
	}


	// Opens file chooser for user, defaults to current directory.
	private static String openFileChooser() {
		JButton opener = new JButton();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setSelectedFile(new File(System.getProperty("user.dir")));
		fileChooser.setDialogTitle("Choose car inventory json file to import");
		fileChooser.showOpenDialog(opener);
		File jsonFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
		String jsonFileName = jsonFile.getAbsolutePath();
		return jsonFileName;
	}

	// Imports json objects into Vehicle objects, created dealers as necessary. 
	private static void importVehicle(Vehicle vehicle) {
		if (!dealerList.dealerExist(vehicle.getDealerId())){
			dealerList.addDealer(new Dealer(vehicle.getDealerId()));
		} 
		dealerList.addToDealer(vehicle.getDealerId(), vehicle);
	}
}
