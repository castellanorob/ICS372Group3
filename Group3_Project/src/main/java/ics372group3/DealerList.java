package ics372group3;

import java.util.*;

public class DealerList {

    private List<Dealer> dealerList;
    private static Scanner enteredValue = UI.enteredValue;

    public DealerList() {
        this.dealerList = new ArrayList<Dealer>();
    }

    public void addDealer(Dealer dealer) {
        dealerList.add(dealer);
        return;
    }

    public static void addVehicleManually() {
        DealerList dealerList = UI.dealerList;

        int dealerID, price;
        String type, manufacturer, model, id, acquisitionDate;
        System.out.println("\n---------------------------------------------");
        System.out.println("Adding Vehicle");
        System.out.println("---------------------------------------------");

        System.out.println("Enter the dealership ID: ");

        dealerID = enteredValue.nextInt();
        enteredValue.nextLine();

        type = manualTypeCheck();

        System.out.println("Enter the vehicle manufacturer: ");
        manufacturer = enteredValue.nextLine();

        System.out.println("Enter the vehicle model: ");
        model = enteredValue.nextLine();

        System.out.println("Enter the vehicle ID: ");
        id = enteredValue.nextLine();

        System.out.println("Enter the vehicle price: ");
        price = enteredValue.nextInt();
        enteredValue.nextLine();

        System.out.println("Enter the vehicle acquisition date: (13 digits)");
        acquisitionDate = enteredValue.nextLine();

        dealerList.addToDealer(dealerID, new Vehicle(dealerID, type, manufacturer, model, id, price, acquisitionDate));
        System.out.println("---------------------------------------------\n");
    }

    // allows for type checking of vehicle input during manual entry
    private static String manualTypeCheck() {
        // namespaces
        List<String> types = Arrays.asList("suv", "sedan", "pickup", "sports car");
        System.out.println("Enter the vehicle type: ");
        System.out.println("Note: the type must be suv, sedan, pickup, or sports car.");

        // loop until type is valid
        String type = enteredValue.nextLine();
        if (types.contains(type)) {
            return type;
        } else {
            System.out.println("~~~ Error: invalid type, please choose a valid type.");
            type = manualTypeCheck();
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
            System.out.println("~~~ Error: dealer " + id + " could not be found.");
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