package ics372group3;

import java.util.*;

public class DealerList {

	private List<Dealer> dealerList;
	
	public DealerList(){
	    this.dealerList = new ArrayList<Dealer>();
    }
	
	
	// methods for automatic and manually entering vehicle. If dealerId entered does not exist, 
	// a new dealer is automatically created and vehicle is added to inventory
	
	public int addVehicle(int dealerID, String type, String manufacturer, String model, String id, int price, String acqusitionDate) {
	    
	    int dealerStatus[] = new int[2];

	    dealerStatus = dealerExist(dealerID);
	    
        /* if dealerList is empty OR dealerID is not in list
         * create dealer
         * add dealer to list
         * create vehicle
         * add vehicle to newly created dealer
         * print user feedback "new dealer created, vehicle added to inventory"
        */

        /* dealer exists
         * check acq status
         * acquisition enabled -> create vehicle, print feedback "vehicle added to dealer inventory"
         * acquisition enabled -> tell user acq disabled
         */
	    if(dealerStatus[0] == 0) {
            Dealer dealer = new Dealer(dealerID);
            dealerList.add(dealer);
            Vehicle vehicle = new Vehicle(dealerID,type,manufacturer,model,id,price,acqusitionDate);
            dealer.addVehicle(vehicle);
            return 2;
        } else if(dealerStatus[0] == 1) {
            if(dealerList.get(dealerStatus[1]).getAcquisitionEnabled()) {
                Vehicle vehicle = new Vehicle(dealerID,type,manufacturer,model,id,price,acqusitionDate);
                dealerList.get(dealerStatus[1]).addVehicle(vehicle);
                return 1;
            }else {
                return 0;
            }
        }
	    return 0;
    }

	public void addDealer(Dealer dealer) {
			dealerList.add(dealer);
			return;
	}
	
	public void addDealerVehicleAuto(int dealerId, Vehicle vehicle){
		for (Dealer dealer : dealerList){
			if (dealer.getDealerId() == dealerId){
				dealer.addVehicle(vehicle);
			}
		}
	}

    public boolean dealerExistAuto(int dealerId) {
        for (Dealer dealer : dealerList){
            if (dealerId == dealer.getDealerId()){
                return true;
            }
        }   
        return false;
    }
	
	private int[] dealerExist(int dealerID) {
		int[] dealerStatus = {0,0};

        if(dealerList.isEmpty()) {
            // if dealerList is empty return 0
            return dealerStatus;
        } else {
            // dealers exist, parse list
    		for (int i = 0; i < dealerList.size(); i++) {
    		    // dealer found, return 1 and position of dealer in list
    			if (dealerList.get(i).getDealerId() == dealerID) {
    				dealerStatus[0] = 1;
    				dealerStatus[1] = i;
    				return dealerStatus;
    			}
    		}
        }
		// return the result no matter what, default to dealer doesn't exist
        // change default?
		return dealerStatus;
	}
	
	public void setAcquisition(int dealerID, boolean acqStatus) {
        if (!dealerList.isEmpty()){
            for (Dealer dealer : dealerList) {
                if (dealer.getDealerId() == dealerID) {
                    dealer.setAcquisitionEnabled(acqStatus);				
                }
            }
            return;
        }
        System.out.println("~~~ Error: dealer " + dealerID + " not found.");
		return;
	}
	
	public void printFullInventory() {
	    if(dealerList.isEmpty()) {
            System.out.println("Dealer List is empty.\n");
            return;
	    }
		System.out.print("Full Inventory");
		for (int i = 0; i < dealerList.size(); i++) {
			dealerList.get(i).printInventory();
		}
	}
}