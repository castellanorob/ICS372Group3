package ics372group3;

import java.util.*;

public class Dealer {

    private static List<Vehicle> inventory;
    private boolean acquisitionEnabled;
    private String dealerID;
    private String name;
    private Scanner scanner = UI.scanner;
    private List<Vehicle> loanedVehicles;

    public Dealer(String dealerID) {
        this.inventory = new ArrayList<Vehicle>();
        this.acquisitionEnabled = true;
        this.dealerID = dealerID;
        this.name = "n/a";
    }

    public void addVehicle(Vehicle vehicle) {
        vehicle.setDealerId(dealerID);
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

    public static boolean vehicleExists(String vehicleID) {
        for (Vehicle vehicle : inventory) {
            if (vehicleID.equalsIgnoreCase(vehicle.getId())) {
                return true;
            }
        }
        return false;
    }

    public String vehicleCheckLoop(String ID) {
        // System.out.println("Enter a vehicle ID:");
        String vehicleID = ID; //scanner.nextLine();
        if (!vehicleExists(vehicleID)) {
            System.out.println("\n~~~ Error: Vehicle does not exist.");
            // vehicleCheckLoop();
        }

        return vehicleID;
    }

    public Vehicle extractVehicle(String vehicleID) {
        Vehicle foundVehicle = null;
        if (!vehicleExists(vehicleID)) {
            System.out.println("~~~ Error: Vehicle does not exist.");
            vehicleID = vehicleCheckLoop(vehicleID);
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

    public void loanVehicle() {
        System.out.println("Enter a vehicle ID to loan (\"0\" to cancel): ");
        String vehicleID = scanner.nextLine();
        if (vehicleExists(vehicleID)) {
            for (Vehicle vehicle : inventory) {
                if (vehicle.getId().equalsIgnoreCase(vehicleID)) {
                    vehicle.loan();
                    loanedVehicles.add(vehicle);
                    return;
                }
            }
        }
        System.out.println("\n~~~ Error: Vehicle could not be found.");
        loanVehicle();
    }

    public void returnVehicle() {
        System.out.println("Enter a vehicle ID to return to the dealer (\"0\" to cancel): ");
        String vehicleID = scanner.nextLine();
        if (vehicleExists(vehicleID)) {
            for (Vehicle vehicle : inventory) {
                if (vehicle.getId().equalsIgnoreCase(vehicleID)) {
                    vehicle.unloan();
                    loanedVehicles.remove(vehicle);
                    return;
                }
            }
        }
        System.out.println("\n~~~ Error: Vehicle could not be found.");
        returnVehicle();
    }

    public boolean getAcquisitionEnabled() {
        return this.acquisitionEnabled;
    }

    public void setAcquisitionEnabled(boolean status) {
        this.acquisitionEnabled = status;
        return;
    }

    public String getDealerId() {
        return this.dealerID;
    }

    public void setDealerId(String dealerID) {
        this.dealerID = dealerID;
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

    public List<Vehicle> getLoanedVehicles(){
        return this.loanedVehicles;
    }

    public String printInventory() {
        String DealerInventory;
        DealerInventory = "\nDealer name: " + this.getName();
        DealerInventory += "\nDealer id: " + this.getDealerId();
        DealerInventory += "\nVehicle Acquisition Status: ";
        if(this.acquisitionEnabled) {
            DealerInventory += "enabled";
        } else {
            DealerInventory += "disabled";
        }
        for (Vehicle vehicle : inventory) {
            DealerInventory += vehicle.toString();
        }
        return DealerInventory;
    }
}
