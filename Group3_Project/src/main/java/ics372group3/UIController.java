package ics372group3;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UIController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private TextField dealerIDbox;
    
    @FXML
    private TextField vehicleTypeBox;
    
    @FXML
    private TextField manufacturerBox;
    
    @FXML
    private TextField modelBox;
    
    @FXML
    private TextField vehicleIDbox;
    
    @FXML
    private TextField priceBox;
    
    @FXML
    private TextField dateBox;
    
    
    @FXML
    private Button exitButton;
    
    
    
    
    // Need to add a checkbox for loaned
    
    
    public static FileChooser fileChooser = new FileChooser();
    
    public void importButton(ActionEvent e) {
        Importer.importFile();
    }
    
    public void addVehicleButton(ActionEvent event) throws IOException{
        
        // Switch to the Add Vehicle Scene
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AddVehicleScene.fxml"));
        root = (Parent) loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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
    
    public void dealerAcquisitionButton(ActionEvent event) throws IOException{
        
     // Switch to the Add Vehicle Scene
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("VehicleAcquisitionScene.fxml"));
        root = (Parent) loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
    public void exportInventoryButton(ActionEvent event) throws IOException{
        
        // Switch to the Export Inventory Scene
           FXMLLoader loader = new FXMLLoader(this.getClass().getResource("ExportInventoryScene.fxml"));
           root = (Parent) loader.load();
           stage = (Stage)((Node)event.getSource()).getScene().getWindow();
           scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
           
       }
    
    public void displayInventoryButton(ActionEvent event) throws IOException{
        
        // Switch to the Display Inventory Scene
           FXMLLoader loader = new FXMLLoader(this.getClass().getResource("DisplayInventoryScene.fxml"));
           root = (Parent) loader.load();
           stage = (Stage)((Node)event.getSource()).getScene().getWindow();
           scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
           
       }
    
    public void modifyStatusButton(ActionEvent event) throws IOException{
        
        // Switch to the Modify Rental Status Scene
           FXMLLoader loader = new FXMLLoader(this.getClass().getResource("ModifyRentalStatusScene.fxml"));
           root = (Parent) loader.load();
           stage = (Stage)((Node)event.getSource()).getScene().getWindow();
           scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
           
       }
    
    public void modifyDealerNameButton(ActionEvent event) throws IOException{
        
        // Switch to the Modify Dealer Name Scene
           FXMLLoader loader = new FXMLLoader(this.getClass().getResource("ModifyDealerNameScene.fxml"));
           root = (Parent) loader.load();
           stage = (Stage)((Node)event.getSource()).getScene().getWindow();
           scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
           
       }
    
    public void transferVehicleButton(ActionEvent event) throws IOException{
        
        // Switch to the Transfer Vehicle Scene
           FXMLLoader loader = new FXMLLoader(this.getClass().getResource("TransferVehicleScene.fxml"));
           root = (Parent) loader.load();
           stage = (Stage)((Node)event.getSource()).getScene().getWindow();
           scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
           
       }
    
    public void clearButton(ActionEvent event) {
        
        dealerIDbox.clear();
        vehicleTypeBox.clear();
        manufacturerBox.clear();
        modelBox.clear();
        vehicleIDbox.clear();
        priceBox.clear();
        dateBox.clear();
    }
    
    public void backButton(ActionEvent event) throws IOException {
        
     // Switch back to UI Scene
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/SceneBuilder.fxml"));
        Parent root = (Parent) loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    public void exitButton(ActionEvent event) throws IOException {
        
        stage = (Stage)exitButton.getScene().getWindow();
        stage.close();
        
    }
    
}
