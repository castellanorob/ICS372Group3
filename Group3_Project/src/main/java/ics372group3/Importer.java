package ics372group3;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.apache.commons.io.FilenameUtils;

import com.google.gson.*;

import javafx.stage.FileChooser;

public class Importer {

	private static DealerList dealerList = UI.dealerList;
	private static Gson gson = new GsonBuilder().setNumberToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();
	private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	// user chooses file type to import, calls respective import method for file type
	public static void importFile(){
		
	    String filePath = UIController.fileChooser.showOpenDialog(null).getAbsolutePath();
		String fileType = FilenameUtils.getExtension(filePath);

		if (fileType.equalsIgnoreCase("json")){
			importJSON(filePath);
		} else if (fileType.equalsIgnoreCase("xml")) {
			importXML(filePath);
		} else {
			System.out.println("~~~ Error: File type not supported, must be json or xml.");
		}
		return;
	}

	// Reads user selected json file and parses into json objects.
	public static void importJSON(String filePath) {

		// Takes vehicle array and parses to Json objects. Calls importVehicle method on each object.
		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			Map<?, ArrayList<?>> map = gson.fromJson(reader, Map.class);
			ArrayList<?> inventory = map.entrySet().iterator().next().getValue();
			int check = 0;

			// Imports json vehicles into Vehicle objects
			for (Object object : inventory) {
				String jsonObject = gson.toJson(inventory.get(inventory.indexOf(object)));
				jsonObject = jsonObject.replace(".", "").replace("E12", "");
				Vehicle vehicle = gson.fromJson(jsonObject, Vehicle.class);
				vehicle.setPrice(vehicle.getPrice() / 10);
				importVehicle(vehicle);
				check++;
			}

			// User feedback on import
			if (check == inventory.size()) {
				System.out.println("\nImport successful.\n");
			} else {
				System.out.println("~~~ Error: Import may be missing information.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Reads user selected XML file and parses elements into lists for iterative object creation.
	public static void importXML(String filePath) {

		try {
			// Creates and formats the document element for parsing.
			Date date = new Date();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(filePath));
			document.getDocumentElement().normalize();
			int check = 0;

			// Parse "Dealer" elements
			NodeList xmlDealerList = document.getElementsByTagName("Dealer");
			for (int i = 0; i < xmlDealerList.getLength(); i++) {
				Node dealerNode = xmlDealerList.item(i);
				int dealerID = Integer.parseInt(dealerNode.getAttributes().getNamedItem("id").getNodeValue());
				if (!dealerList.dealerExist(dealerID)){
					dealerList.addDealer(new Dealer(dealerID));
				}
				NodeList vehicleList = dealerNode.getChildNodes();
				// Parse Vehicle elements in each dealer
				for (int j = 0; j < vehicleList.getLength(); j++) {
					Node vehicleNode = vehicleList.item(j);
					if (vehicleNode.getNodeName().equalsIgnoreCase("name")) {
						dealerList.changeDealerName(dealerID,  vehicleNode.getTextContent().replace("’", "'"));
					} else if (vehicleNode.getNodeName().equalsIgnoreCase("vehicle")) {
						String vType = vehicleNode.getAttributes().getNamedItem("type").getNodeValue();
						String vID = vehicleNode.getAttributes().getNamedItem("id").getNodeValue();
						int vPrice = 0;
						String vManu = "";
						String vModel = "";

						// Parse variable elements in vehicle node
						NodeList vehicleProperyList = vehicleNode.getChildNodes();
						for (int k = 0; k < vehicleProperyList.getLength(); k++) {
							Node propertyNode = vehicleProperyList.item(k);
							if (propertyNode.getNodeName().equalsIgnoreCase("price")) {
								vPrice = Integer.parseInt(propertyNode.getTextContent());
							} else if (propertyNode.getNodeName().equalsIgnoreCase("make")) {
								vManu = propertyNode.getTextContent();
							} else if (propertyNode.getNodeName().equalsIgnoreCase("model")) {
								vModel = propertyNode.getTextContent();
							}
						}

						// create vehicle and add to dealer
						Vehicle vehicle = new Vehicle(dealerID, vType, vManu, vModel, vID, vPrice, date.getTime());
						importVehicle(vehicle);
					}
				}
				check++;
			}

			// User feedback on import
			if (check == xmlDealerList.getLength()) {
				System.out.println("\nImport successful.\n");
			} else {
				System.out.println("~~~ Error: Import may be missing information.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Imports vehicle into dealers, creates dealers as necessary.
	private static void importVehicle(Vehicle vehicle) {
		if (!dealerList.dealerExist(vehicle.getDealerId())) {
			dealerList.addDealer(new Dealer(vehicle.getDealerId()));
		}
		dealerList.addToDealer(vehicle.getDealerId(), vehicle);
	}
}
