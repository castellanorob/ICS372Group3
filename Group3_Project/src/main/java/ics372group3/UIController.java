package ics372group3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

public class UIController {

    @FXML
    
    public static FileChooser fileChooser = new FileChooser();
    
    public void importButton(ActionEvent e) {
        Importer.importFile();
    }
    
    public void addVehicleButton(ActionEvent e) {
        
        System.out.println("This button allows the user to manually add a vehicle.");
        
    }
    
    public void dealerAcquisitionButton(ActionEvent e) {
        
        System.out.println("This button allows the user to change Dealer Vehicle Acquisition status.");
        
    }
    
}
