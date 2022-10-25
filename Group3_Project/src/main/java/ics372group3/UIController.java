package ics372group3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UIController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    String sendDealerID;
    String recipDealerID;
    String transferVehicleID;

    @FXML
    private TextField dealerIDbox, vehicleTypeBox, manufacturerBox, modelBox, vehicleIDbox, priceBox, dateBox,
            sendingDealerID, recipientDealerID;

    @FXML
    private TextArea textDisplay;

    @FXML
    private Button exitButton, transferVehicleSubmit;

    @FXML
    private Label transferVStatusLabel, acqResult;

    public static FileChooser fileChooser = new FileChooser();
    public static DealerList dealerList = new DealerList();
    public static Scanner scanner = new Scanner(System.in);

    public void importButton(ActionEvent e) {
        Importer.importFile();
    }

    // Switch to the Add Vehicle Scene
    public void addVehicleButton(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AddVehicleScene.fxml"));
        root = (Parent) loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void submitVehicleDetails(ActionEvent event) {

        String dealerID = dealerIDbox.getText();
        String vehicleType = vehicleTypeBox.getText();
        String manufacturer = manufacturerBox.getText();
        String model = modelBox.getText();
        String vehicleID = vehicleIDbox.getText();
        Long date = Long.parseLong(dateBox.getText());

    }

    // Switch to the Add Vehicle Scene
    public void dealerAcquisitionButton(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("VehicleAcquisitionScene.fxml"));
        root = (Parent) loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    // Switch to the Export Inventory Scene
    public void exportInventoryButton(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("ExportInventoryScene.fxml"));
        root = (Parent) loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    //Export the dealer to file 
    public void exportDealerToFile(String dealerID) throws FileNotFoundException {
        Exporter.exportDealerJson(dealerID);
    }

    // Switch to the Display Inventory Scene
    public void displayInventoryButton(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("DisplayInventoryScene.fxml"));
        root = (Parent) loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void displayInventoryTextButton(ActionEvent event) throws IOException {
        String inventoryText;

        inventoryText = dealerList.printFullInventory();

        textDisplay.setText(inventoryText);

    }

    // Switch to the Modify Rental Status Scene
    public void modifyStatusButton(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("ModifyRentalStatusScene.fxml"));
        root = (Parent) loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void enableVehicleAcquisition(ActionEvent event) {

        String dealer = null;

        try {
            dealer = dealerIDbox.getText();
        } catch (Exception e) {
            System.out.println(e);
            acqResult.setText("Error: Please enter data in the correct format");
        }

        if (!dealerList.dealerExist(dealer)) {
            acqResult.setText("Error: Dealer does not exist. Please enter a valid Dealer ID.");
        } else {
            dealerList.dealerAcquisition(dealer, true);
            acqResult.setText("Dealer Acquisition Enabled");
        }

    }

    public void disableVehicleAcquisition(ActionEvent event) {

        String dealer = null;

        try {
            dealer = dealerIDbox.getText();
        } catch (Exception e) {
            System.out.println(e);
            acqResult.setText("Error: Please enter data in the correct format");
        }

        if (!dealerList.dealerExist(dealer)) {
            acqResult.setText("Error: Dealer does not exist. Please enter a valid Dealer ID.");
        } else {
            dealerList.dealerAcquisition(dealer, false);
            acqResult.setText("Dealer Acquisition Disabled");
        }

    }

    // Switch to the Modify Dealer Name Scene
    public void modifyDealerNameButton(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("ModifyDealerNameScene.fxml"));
        root = (Parent) loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    // Switch to the Transfer Vehicle Scene
    public void transferVehicleButton(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("TransferVehicleScene.fxml"));
        root = (Parent) loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void transferVehicleSubmit(ActionEvent event) {

        Dealer sendingDealer = null;
        Dealer recipientDealer = null;
        Vehicle vehicle;

        try {
            sendDealerID = sendingDealerID.getText();
            recipDealerID = recipientDealerID.getText();
            transferVehicleID = vehicleIDbox.getText();
        } catch (Exception e) {
            System.out.println(e);
            transferVStatusLabel.setText("Error: Please enter data in the correct format");
        }

        if (!dealerList.dealerExist(sendDealerID)) {
            transferVStatusLabel
                    .setText("Error: Sending Dealer ID does not exist. Please enter a valid Sending Dealer ID.");
        } else if (!dealerList.dealerExist(recipDealerID)) {
            transferVStatusLabel
                    .setText("Error: Recipient Dealer ID does not exist. Please enter a valid Recipient Dealer ID.");

        } else {

            for (Dealer dealer : dealerList.dealerList) {
                if (dealer.getDealerId().equalsIgnoreCase(sendDealerID)) {
                    sendingDealer = dealer;
                } else if (dealer.getDealerId().equalsIgnoreCase(recipDealerID)) {
                    recipientDealer = dealer;
                }
            }
            if (!sendingDealer.vehicleExists(transferVehicleID)) {
                transferVStatusLabel.setText("Error: Vehicle ID does not exist. Please enter a valid Vehicle ID.");
            }
            transferVehicleID = sendingDealer.vehicleCheckLoop(transferVehicleID);
            vehicle = sendingDealer.extractVehicle(transferVehicleID);
            sendingDealer.removeVehicle(transferVehicleID);
            recipientDealer.addVehicle(vehicle);
            if (!sendingDealer.vehicleExists(transferVehicleID) && recipientDealer.vehicleExists(transferVehicleID)) {
                System.out.println("Transfer Successful.");
                transferVStatusLabel.setText("Transfer Successful.");

                sendingDealerID.clear();
                recipientDealerID.clear();
                vehicleIDbox.clear();
            }

        }

    }

    public void addVehicleClearButton(ActionEvent event) {

        dealerIDbox.clear();
        vehicleTypeBox.clear();
        manufacturerBox.clear();
        modelBox.clear();
        vehicleIDbox.clear();
        priceBox.clear();
        dateBox.clear();

    }

    // Switch back to UI Scene
    public void backButton(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/SceneBuilder.fxml"));
        Parent root = (Parent) loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void exitButton(ActionEvent event) throws IOException {

        stage = (Stage) exitButton.getScene().getWindow();
        Exporter.exportSaveFile();
        stage.close();
        System.exit(1);
    }

}
