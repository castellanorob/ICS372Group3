package ics372group3;

import java.util.ArrayList;
import java.util.List;

public class DealerList {
	
	private List<Dealer> dealerList = new ArrayList<Dealer>();
	
	public void addDealer(Dealer dealer) {
		if(dealerExist(dealer.getDealerId())) {
			System.out.println("~~~ Error: dealer already exists.");
		} else {
			dealerList.add(dealer);	
		}
	}

	public void addDealerVehicle(int dealerId, Vehicle vehicle){
		for (Dealer dealer : dealerList){
			if (dealer.getDealerId() == dealerId){
				dealer.addVehicle(vehicle);
			}
		}
	}

	public boolean dealerExist(int dealerId) {
		for (Dealer dealer : dealerList){
			if (dealerId == dealer.getDealerId()){
				return true;
			}
		}	
		return false;
	}

	public void setAcquisition(int inputId, boolean status){
		for (Dealer dealer : dealerList) {
			if (dealer.getDealerId() == inputId) {
				dealer.setAcquisitionEnabled(status);
			} else {
				System.out.println("~~~ Error: dealer " + inputId + " not found.");
			}
		}
	}
	
	public void printFullInventory() {
		System.out.print("Full Inventory\n");
		for (Dealer dealer : dealerList) {
			dealer.printInventory();
		}
		
	}
}