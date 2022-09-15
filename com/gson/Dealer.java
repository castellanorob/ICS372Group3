package com.gson;
import java.util.*;

public class Dealer {
    
    private List<Vehicle> inventory;
    private boolean acquisitionEnabled;
    private int dealerID;

    //think the acqEnabled parameter should be removed, json creation won't be providing this
    //all dealers should be enabled until disabled through user selection
    public Dealer(int dealerID, boolean acqEnabled){
        this.inventory = new ArrayList<Vehicle>();
        this.acquisitionEnabled = acqEnabled;
        this.dealerID = dealerID;
    }
    
    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
        return;
    }
    
    public void exportToFile() {
        
        //discuss implementation
    }
    
    public void printInventory() {
        Iterator<Vehicle> inventoryIterator = inventory.iterator();

        if(this.inventory.isEmpty()) {
            System.out.println("Dealer has no inventory\n");
            return;
        }
        
        while(inventoryIterator.hasNext()) {
            inventoryIterator.next().toString();
        }
        return;
    }
    
    public Dealer createDealer(int dealerID) {
        Dealer dealer = new Dealer(dealerID, true);
        return dealer;
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