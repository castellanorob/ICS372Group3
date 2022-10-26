package ics372group3;

import java.util.*;

public class DealerList {

    public List<Dealer> dealerList;
    private Scanner scanner = UIController.scanner;

    public DealerList() {
        this.dealerList = new ArrayList<Dealer>();
    }

    public void addDealer(Dealer dealer) {
        dealerList.add(dealer);
        return;
    }

    public void addVehicleManually() {

        String dealerID;
        int price;
        String type, manufacturer, model, id;
        long acquisitionDate;
        System.out.println("\n---------------------------------------------");
        System.out.println("Adding Vehicle");
        System.out.println("---------------------------------------------");

        dealerID = dealerCheckLoop();

        type = typeCheckLoop();

        System.out.println("Enter the vehicle manufacturer: ");
        manufacturer = scanner.nextLine();

        System.out.println("Enter the vehicle model: ");
        model = scanner.nextLine();

        System.out.println("Enter the vehicle ID: ");
        id = scanner.nextLine();

        System.out.println("Enter the vehicle price: ");
        price = scanner.nextInt();

        System.out.println("Enter the vehicle acquisition date: (13 digits)");
        acquisitionDate = scanner.nextLong();

        addToDealer(dealerID, new Vehicle(dealerID, type, manufacturer, model, id, price, acquisitionDate));
        System.out.println("---------------------------------------------\n");
    }

    // checks for existing dealer during manual entry
    public String dealerCheckLoop() {
        System.out.println("Enter the dealership ID: ");
        String dealerID = "";
        try {
            dealerID = scanner.nextLine();
            if (!dealerExist(dealerID)){
                System.out.println("\n~~~ Error: Dealer id doesn't exist.");
                dealerCheckLoop();
            }
        } catch (InputMismatchException e) {
            System.out.println("\n~~~ Error: Dealer id doesn't exist.");
            scanner.nextLine();
            dealerCheckLoop();
        }
        return dealerID;
    }
    
    // allows for type checking of vehicle input during manual entry
    public String typeCheckLoop() {
        // namespaces
        List<String> types = Arrays.asList("suv", "sedan", "pickup", "sports car");
        System.out.println("Enter the vehicle type: ");
        System.out.println("Note: the type must be suv, sedan, pickup, or sports car.");

        // loop until type is valid
        String type = scanner.nextLine();
        if (types.contains(type)) {
            return type;
        } else {
            System.out.println("\n~~~ Error: invalid type, please choose a valid type.");
            type = typeCheckLoop();
        }
        return type;
    }

    public void addToDealer(String dealerID, Vehicle vehicle) {
        for (Dealer dealer : dealerList) {
            if (dealer.getDealerId().equalsIgnoreCase(dealerID) && !dealer.vehicleExists(vehicle.getId())) {
                dealer.addVehicle(vehicle);
            }
        }
    }

    public void changeDealerName(String dealerID, String newName){

        for(Dealer dealer : dealerList) {
            if (dealer.getDealerId().equalsIgnoreCase(dealerID)){
                dealer.setName(newName);
                return;
            }
        }
    }

    public boolean dealerExist(String dealerID) {
        for (Dealer dealer : dealerList) {
            if (dealerID.equalsIgnoreCase(dealer.getDealerId())) {
                return true;
            }
        }
        return false;
    }
    
    public void removeDealer(String dealerID) {
        for (int i = 0; i < dealerList.size(); i++) {
            if (dealerID.equalsIgnoreCase(dealerList.get(i).getDealerId())) {
                dealerList.remove(i);
            }
        }
        return;
    }

    public void dealerAcquisition(String id, boolean status) {
        DealerList dealerList = this;

        if (dealerList.dealerExist(id)) {
            for (Dealer dealer : dealerList.getDealerList()) {
                if (dealer.getDealerId().equalsIgnoreCase(id)) {
                    dealer.setAcquisitionEnabled(status);
                }
            }
        } else {
            System.out.println("~~~ Acquisition Error: dealer " + id + " could not be found.");
        }
    }

    public Dealer getDealer(String dealerID) {
        Dealer extractedDealer = null;
        if (dealerExist(dealerID)){
            for (Dealer dealer : dealerList) {
                if (dealer.getDealerId().equalsIgnoreCase(dealerID)){
                    extractedDealer = dealer;
                    break;
                }
            }
        } else {
            System.out.println("~~~ Error: dealer " + dealerID + " could not be found.");
        }
        return extractedDealer;
    }

    public List<Dealer> getDealerList() {
        return this.dealerList;
    }

    public String printFullInventory() {
        String inventoryResults;
        if (dealerList.isEmpty()) {
            inventoryResults = "Dealer List is empty.\n";
            return inventoryResults;
        }
        inventoryResults = "Full Inventory\n";
        for (int i = 0; i < dealerList.size(); i++) {
            inventoryResults += dealerList.get(i).printInventory() + "\n";
        }
        return inventoryResults;
    }
    
    public boolean modifyRentalStatus(String dealerID,String vehicleID,boolean loanedStatus) {
        for (int i = 0; i < dealerList.size(); i++) {
            if (dealerID.equalsIgnoreCase(dealerList.get(i).getDealerId())) {
                for (int j = 0; j < dealerList.get(i).getInventory().size(); j++) {
                    if (vehicleID.equalsIgnoreCase(dealerList.get(i).getInventory().get(j).getId())){
                        dealerList.get(i).getInventory().get(j).setLoaned(loanedStatus);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}