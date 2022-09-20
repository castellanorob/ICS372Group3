package ics372group3;

import java.util.*;

public class DealerList {

	private List<Dealer> dealerList;
	
	// constructor
	public DealerList(){
	    this.dealerList = new ArrayList<Dealer>();
    }
	
	// need a different addVehicle() for the json parsing
	
	
	// method for user manually entering vehicle. If dealerId entered does not exist, 
	// a new dealer is automatically created and vehicle is added to inventory
	public int addVehicle(int dealerID, String type, String manufacturer, String model, String id, int price, String acqusitionDate) {
	    
	    int dealerStatus[] = new int[2];

	    dealerStatus = dealerExist(dealerID);
	    
	    // dealerList is empty OR dealerID is not in list
	    if(dealerStatus[0] == 0) {
            // created dealer
            Dealer dealer = new Dealer(dealerID);
            // add dealer to list
            dealerList.add(dealer);
            // create vehicle
            Vehicle vehicle = new Vehicle(dealerID,type,manufacturer,model,id,price,acqusitionDate);
            // add vehicle to newly created dealer
            dealer.addVehicle(vehicle);
            // return 2 -> "new dealer created, vehicle added to inventory"
            return 2;
            
        // dealer exists
        } else if(dealerStatus[0] == 1) {
            // check acq status
            if(dealerList.get(dealerStatus[1]).getAcquisitionEnabled()) {
                // acquisition enabled
                // create vehicle
                Vehicle vehicle = new Vehicle(dealerID,type,manufacturer,model,id,price,acqusitionDate);
                dealerList.get(dealerStatus[1]).addVehicle(vehicle);
                // return 1 -> "vehicle added to dealer inventory"
                return 1;
            }else {
                // return 0 -> "dealer acq disabled"
                return 0;
            }
        }
	    return 0;
    }

	public void addDealer(Dealer dealer) {
			dealerList.add(dealer);
			return;
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
	
	public void setAcquistion(int dealerID, boolean acqStatus) {
	    Iterator<Dealer> dealerListIt = dealerList.iterator();

        if (dealerList.isEmpty()){
            System.out.println("Dealer does not exist.\n");
            return;
        }
        
        while(dealerListIt.hasNext()) {
            if(dealerListIt.next().getDealerId() == dealerID){
                dealerListIt.next().setAcquisitionEnabled(acqStatus);
                return;
            }
        }
        
        System.out.println("Dealer does not exist");
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

	public void addDealerVehicle(int dealerId, Vehicle vehicle){
		for (Dealer dealer : dealerList){
			if (dealer.getDealerId() == dealerId){
				dealer.addVehicle(vehicle);
			}
		}
	}

	public void setAcquisition(int inputId, boolean status){
		for (Dealer dealer : dealerList) {
			if (dealer.getDealerId() == inputId) {
				dealer.setAcquisitionEnabled(status);				
			}
		}
		System.out.println("~~~ Error: dealer " + inputId + " not found.");
		return;
	}
}