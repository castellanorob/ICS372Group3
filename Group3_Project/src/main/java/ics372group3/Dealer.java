package ics372group3;

import java.util.*;

public class Dealer {

    private List<Vehicle> inventory;
    private boolean acquisitionEnabled;
    private int dealerId;
    private String name;
    private Scanner scanner = UI.scanner;

    public Dealer(int dealerId) {
        this.inventory = new ArrayList<Vehicle>();
        this.acquisitionEnabled = true;
        this.dealerId = dealerId;
        this.name = "n/a";
    }

    public void addVehicle(Vehicle vehicle) {
        vehicle.setDealerId(dealerId);
        inventory.add(vehicle);
    }

    public void removeVehicle(String vehicleID) {
        for (Vehicle vehicle : inventory) {
            if (vehicle.getId().equalsIgnoreCase(vehicleID)) {
                inventory.remove(vehicle);
                return;
            }
        }
    }

    public boolean vehicleExists(String vehicleID) {
        for (Vehicle vehicle : inventory) {
            if (vehicleID.equalsIgnoreCase(vehicle.getId())) {
                return true;
            }
        }
        return false;
    }

    public String vehicleCheckLoop() {
        System.out.println("Enter a vehicle ID:");
        String vehicleID = scanner.nextLine();
        if (!vehicleExists(vehicleID)) {
            System.out.println("\n~~~ Error: Vehicle does not exist.");
            vehicleCheckLoop();
        }

        return vehicleID;
    }

    public Vehicle extractVehicle(String vehicleID) {
        Vehicle foundVehicle = null;
        if (!vehicleExists(vehicleID)){
            System.out.println("~~~ Error: Vehicle does not exist.");
            vehicleID = vehicleCheckLoop();
            extractVehicle(vehicleID);
        } else {
            for (Vehicle vehicle : inventory) {
                if (vehicle.getId().equalsIgnoreCase(vehicleID)) {
                    foundVehicle = vehicle;
                    break;
                }
            }
        }
        return foundVehicle;
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
        System.out.println("\nDealer name: " + this.getName());
        System.out.println("Dealer id: " + this.getDealerId());
        for (Vehicle vehicle : inventory) {
            System.out.println(vehicle.toString());
        }
    }
}
