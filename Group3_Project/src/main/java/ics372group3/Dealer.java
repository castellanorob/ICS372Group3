package ics372group3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import com.google.gson.*;

public class Dealer {

    static Gson dealerGson = new GsonBuilder().setPrettyPrinting().create();
    static PrintWriter output;

    private List<Vehicle> inventory;
    private boolean acquisitionEnabled;
    private int dealerId;

    public Dealer(int dealerId){
        this.inventory = new ArrayList<Vehicle>();
        this.acquisitionEnabled = true;
        this.dealerId = dealerId;
    }
    
    public void addVehicle(Vehicle vehicle) {
        vehicle.setDealerId(dealerId);
        inventory.add(vehicle);
    }
    
    public void exportToFile() throws FileNotFoundException {

        File exportedFile = new File(dealerId + ".json");
        output = new PrintWriter(exportedFile);
        System.out.println("... Exporting inventory as " + dealerId + ".json");
        output.println("{\n\"dealer_inventory\":[");
        for (Vehicle vehicle : inventory){
            String vString = dealerGson.toJson(vehicle);
            output.print(vString);
            if (!(inventory.indexOf(vehicle) == inventory.size()-1)){
                output.println(",");
            } else {
                output.println("");
            }
        }
        output.println("]\n}");
        output.close();
    }
    
    public void printInventory() {
        System.out.println("Dealer id: " + this.getDealerId());
        for (Vehicle vehicle : inventory){
            System.out.println(vehicle.toString());
        }
    }
    
    public boolean getAcquisitionEnabled() {
        return this.acquisitionEnabled;
    }
    
    public void setAcquisitionEnabled(boolean status) {
       this.acquisitionEnabled = status;
       return;
    }
    
    public int getDealerId() {
        return this.dealerId;
    }
    public void setDealerId(int dealerId) {
        this.dealerId = dealerId;
        return;
    }
    
    //work on formatting
    @Override
    public String toString() {
        return "Dealer [inventory=" + inventory + ", acquisitionEnabled=" + acquisitionEnabled + ", dealerId="
                + dealerId + "]";
    }

}
