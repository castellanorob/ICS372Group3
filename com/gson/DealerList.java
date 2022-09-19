package com.gson;

import java.util.*;
import java.util.Iterator;
import java.util.Date;

public class DealerList {
	// Need to create a list for dealerList
	private List<Dealer> dealerList;
	
	// Add a printFullInventory method
	
	//constructor
	public DealerList(){
	    this.dealerList = new ArrayList<Dealer>();
    }
	
	//need to solve for Date type
	public int addVehicle(int dealerID, String type, String manufacturer, String model, String id, int price, Date acqusitionDate) {
	    
	    int dealerStatus[] = new int[2];
	    Iterator<Dealer> dealerListIt = dealerList.iterator();

	    dealerStatus = dealerExist(dealerID);
	    
	    //dealerList is null OR dealerID is not in list
	    if(dealerStatus[0] == 0) {
            //created dealer
            Dealer dealer = new Dealer(dealerID, true);
            //add dealer to list
            dealerList.add(dealer);
            //create vehilce
            Vehicle vehicle = new Vehicle(dealerID,type,manufacturer,model,id,price,acqusitionDate);
            //add vehicle to newly created dealer
            dealer.addVehicle(vehicle);
            //return 2 -> "new dealer created, vehicle added to inventory"
            return 2;
            
        //dealer exists
        } else if(dealerStatus[0] == 1) {
            //check acq status
            if(dealerList.get(dealerStatus[1]).getAcquisitionEnabled()) {
                //acquisition enabled
                //create vehicle
                Vehicle vehicle = new Vehicle(dealerID,type,manufacturer,model,id,price,acqusitionDate);
                dealerList.get(dealerStatus[1]).addVehicle(vehicle);
                //return 1 -> "vehicle added to dealer inventory"
                return 1;
            }else {
                //return 0 -> "dealer acq disabled"
                return 0;
            }
        }
    }

	//this needs to be modified to fit new dealerExist
	//modify to just add no matter what?
	public void addDealer(Dealer dealer) {
	    
		if(dealerExist(dealer.getDealerID())) {
			// already in dealers list. Do nothing.	
		} else {
			// dealer does not exist in the list. Add Now.
			dealerList.add(dealer);	
		}
	}

	private int[] dealerExist(int dealerID) {

		int[] dealerStatus = {0,0};

        if(this.dealerList.isEmpty()) {
            //if dealerList is empty return 0
            return dealerStatus;
        } else {
            //dealers exist, parse list
    		for (int i = 0; i < dealerList.size(); i++) {
    		    //dealer found, return 1 and position
    			if (dealerList.get(i).getDealerID() == dealerID) {
    				dealerStatus[0] = 1;
    				dealerStatus[1] = i;
    				return dealerStatus;
    			}
    		}
        }
		// return the result no matter what, default to dealer doesn't exist
		return dealerStatus;
	}
	
	//could iterate over this instead, not important
	public void printFullInventory() {
		System.out.print("Full Inventory");
		for (int i = 0; i < dealerList.size(); i++) {
			// Take each dealer and print its inventory.
			// That makes the full inventory
			dealerList.get(i).printInventory();
		}
	}
}