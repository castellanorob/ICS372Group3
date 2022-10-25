package ics372group3;

import java.util.*;

public class DealerList {

    public static List<Dealer> dealerList;
    private static Scanner scanner = new Scanner(System.in);

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
            if (dealer.getDealerId().equalsIgnoreCase(dealerID)) {
                dealer.addVehicle(vehicle);
            }
        }
    }

    public void changeDealerName(String dealerID, String newName){
        if(dealerExist(dealerID)){
            for(Dealer dealer : dealerList) {
                if (dealer.getDealerId().equalsIgnoreCase(dealerID)){
                    dealer.setName(newName);
                    return;
                }
            }
        } else {
            System.out.println("~~~ Name Change Error: dealer " + dealerID + " could not be found.");
        }
    }

    public void vehicleTransfer(){
        System.out.println("\n[Dealer that will be SENDING vehicle]");
        String sendingDealerID = dealerCheckLoop();
        System.out.println("\n[Dealer that will be RECEIVING vehicle]");
        String recipientDealerID =  dealerCheckLoop();
        String vehicleID;
        Dealer sendingDealer = null;
        Dealer recipientDealer = null;
        Vehicle vehicle;
        for (Dealer dealer : dealerList){
            if (dealer.getDealerId().equalsIgnoreCase(sendingDealerID)){
                sendingDealer = dealer;
            } else if (dealer.getDealerId().equalsIgnoreCase(recipientDealerID)){
                recipientDealer = dealer;
            }
        }
        System.out.println("\n[Vehicle to be transferred]");
//        vehicleID = sendingDealer.vehicleCheckLoop();
//        vehicle = sendingDealer.extractVehicle(vehicleID);
//        sendingDealer.removeVehicle(vehicleID);
//        recipientDealer.addVehicle(vehicle);
//        if (!sendingDealer.vehicleExists(vehicleID) && recipientDealer.vehicleExists(vehicleID)){
//            System.out.println("Transfer Successful.");
//        }
    }

    public static boolean dealerExist(String dealerID) {
        for (Dealer dealer : dealerList) {
            if (dealerID.equalsIgnoreCase(dealer.getDealerId())) {
                return true;
            }
        }
        return false;
    }

    public static void dealerAcquisition(String id, boolean status) {
        DealerList dealerList = UI.dealerList;

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
}