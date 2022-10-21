package ics372group3;

import java.util.*;

public class DealerList {

    private List<Dealer> dealerList;
    private static Scanner scanner = new Scanner(System.in);

    public DealerList() {
        this.dealerList = new ArrayList<Dealer>();
    }

    public void addDealer(Dealer dealer) {
        dealerList.add(dealer);
        return;
    }

    public void addVehicleManually() {

        int dealerID, price;
        String type, manufacturer, model, id;
        long acquisitionDate;
        System.out.println("\n---------------------------------------------");
        System.out.println("Adding Vehicle");
        System.out.println("---------------------------------------------");

        dealerID = dealerCheckLoop();
        scanner.nextLine();

        type = typeCheckLoop();

        System.out.println("Enter the vehicle manufacturer: ");
        manufacturer = scanner.nextLine();

        System.out.println("Enter the vehicle model: ");
        model = scanner.nextLine();

        System.out.println("Enter the vehicle ID: ");
        id = scanner.nextLine();

        System.out.println("Enter the vehicle price: ");
        price = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the vehicle acquisition date: (13 digits)");
        acquisitionDate = scanner.nextLong();

        addToDealer(dealerID, new Vehicle(dealerID, type, manufacturer, model, id, price, acquisitionDate));
        System.out.println("---------------------------------------------\n");
    }

    // checks for existing dealer during manual entry
    public int dealerCheckLoop() {
        System.out.println("Enter the dealership ID: ");
        int dealerId = 0;
        try {
            dealerId = scanner.nextInt();
            if (!dealerExist(dealerId)){
                System.out.println("\n~~~ Error: Dealer id doesn't exist.");
                dealerCheckLoop();
            }
        } catch (InputMismatchException e) {
            System.out.println("\n~~~ Error: Dealer id doesn't exist.");
            scanner.nextLine();
            dealerCheckLoop();
        }
        return dealerId;
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

    public void addToDealer(int dealerId, Vehicle vehicle) {
        for (Dealer dealer : dealerList) {
            if (dealer.getDealerId() == dealerId) {
                dealer.addVehicle(vehicle);
            }
        }
    }

    public void changeDealerName(int dealerID, String newName){
        if(dealerExist(dealerID)){
            for(Dealer dealer : dealerList) {
                if (dealer.getDealerId() == dealerID){
                    dealer.setName(newName);
                }
                return;
            }
        } else {
            System.out.println("~~~ Name Change Error: dealer " + dealerID + " could not be found.");
        }
    }

    public void vehicleTransfer(){
        System.out.println("\n[Dealer that will be SENDING vehicle]");
        int sendingDealerID = dealerCheckLoop();
        System.out.println("\n[Dealer that will be RECEIVING vehicle]");
        int recipientDealerID =  dealerCheckLoop();
        String vehicleID;
        Dealer sendingDealer = null;
        Dealer recipientDealer = null;
        Vehicle vehicle;
        for (Dealer dealer : dealerList){
            if (dealer.getDealerId() == sendingDealerID){
                sendingDealer = dealer;
            } else if (dealer.getDealerId() == recipientDealerID){
                recipientDealer = dealer;
            }
        }
        System.out.println("\n[Vehicle to be transferred]");
        vehicleID = sendingDealer.vehicleCheckLoop();
        vehicle = sendingDealer.extractVehicle(vehicleID);
        sendingDealer.removeVehicle(vehicleID);
        recipientDealer.addVehicle(vehicle);
        if (!sendingDealer.vehicleExists(vehicleID) && recipientDealer.vehicleExists(vehicleID)){
            System.out.println("Transfer Successful.");
        }
    }

    public boolean dealerExist(int dealerId) {
        for (Dealer dealer : dealerList) {
            if (dealerId == dealer.getDealerId()) {
                return true;
            }
        }
        return false;
    }

    public static void dealerAcquisition(int id, boolean status) {
        DealerList dealerList = UI.dealerList;

        if (dealerList.dealerExist(id)) {
            for (Dealer dealer : dealerList.getDealerList()) {
                if (dealer.getDealerId() == id) {
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

    public void printFullInventory() {
        if (dealerList.isEmpty()) {
            System.out.println("Dealer List is empty.\n");
            return;
        }
        System.out.print("Full Inventory\n");
        for (int i = 0; i < dealerList.size(); i++) {
            dealerList.get(i).printInventory();
        }
    }
}