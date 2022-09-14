package com.gson;
import java.util.*;

public class Dealer {
    
    private List<Vehicle> inventory;
    private boolean acquisitionEnabled;
    private int dealerID;

    public Dealer(int dealerID, boolean acqEnabled){
        this.inventory = new ArrayList<Vehicle>();
        this.acquisitionEnabled = acqEnabled;
        this.dealerID = dealerID;
    }
    
    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }
    
    public void exportToFile() {
        
        //discuss implementation
    }
    
    public void printInventory() {
        Iterator<Vehicle> inventoryIterator = inventory.iterator();

        //handle an empty inventory?
        
        while(inventoryIterator.hasNext()) {
            inventoryIterator.next().toString();
        }
        
    }
    
    public void createDealer(int dealerID) {
        //need to figure out how to use value of dealerID as object name, 'temp' currently
        Dealer temp = new Dealer(dealerID, true);
        
        //turn on once DealerList is implemented
        //dealerList.add(temp);
        
        return;
    }
    
    public boolean getAcquisitionEnabled() {
        return this.acquisitionEnabled;
    }
    
    public void setAcquisitionEnabled(boolean status) {
       this.acquisitionEnabled = status;
       return;
    }
    
    public int getDealerID() {
        return this.dealerID;
    }
    public void setDealerID(int dealerID) {
        this.dealerID = dealerID;
        return;
    }
    
    //work on formatting
    @Override
    public String toString() {
        return "Dealer [inventory=" + inventory + ", acquisitionEnabled=" + acquisitionEnabled + ", dealerID="
                + dealerID + "]";
    }

}