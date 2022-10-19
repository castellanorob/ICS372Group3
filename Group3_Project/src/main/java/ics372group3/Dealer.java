package ics372group3;

import java.util.*;

public class Dealer {

    private List<Vehicle> inventory;
    private boolean acquisitionEnabled;
    private int dealerId;
    private String name;

    public Dealer(int dealerId){
        this.inventory = new ArrayList<Vehicle>();
        this.acquisitionEnabled = true;
        this.dealerId = dealerId;
        this.name = "N/A";
    }
    
    public void addVehicle(Vehicle vehicle) {
        vehicle.setDealerId(dealerId);
        inventory.add(vehicle);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vehicle> getInventory() {
        return this.inventory;
    }

    public void printInventory() {
        System.out.println("Dealer name: " + this.getName());
        System.out.println("Dealer id: " + this.getDealerId());
        for (Vehicle vehicle : inventory){
            System.out.println(vehicle.toString());
        }
    }
}
