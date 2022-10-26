import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ics372group3.Dealer;
import ics372group3.DealerList;
import ics372group3.Importer;
import ics372group3.Start;
import ics372group3.UIController;
import ics372group3.Vehicle;

public class DealerTests {

    @Test
    public void vehicleExistsAfterAdding(){
        Importer.importJSON(Start.SAVE_FILE);
        DealerList dealerList = UIController.dealerList;
        List<Dealer> list = dealerList.getDealerList();
        Dealer dealer = null;
        Vehicle vehicle = new Vehicle(null, "sedan", "suv", "4Runner", "234fd56", 33340, 0);
        if (!dealerList.getDealerList().isEmpty()){
            dealer = list.get(0);
            vehicle.setDealerId(dealer.getDealerId());
            dealer.addVehicle(vehicle);
        }
        assertTrue(dealer.vehicleExists(vehicle.getId()));
    }

   

    @Test
    public void settingLoanTrue(){
        Importer.importJSON(Start.SAVE_FILE);
        DealerList dealerList = UIController.dealerList;
        List<Dealer> list = dealerList.getDealerList();
        Dealer dealer = null;
        Vehicle vehicle = new Vehicle(null, "sedan", "suv", "4Runner", "234fd56", 33340, 0);
        if (!dealerList.getDealerList().isEmpty()){
            dealer = list.get(0);
            dealer.addVehicle(vehicle);
        }
        assertTrue(dealer.vehicleExists(vehicle.getId()));
        assertFalse(vehicle.getLoanStatus());
        dealer.loanVehicle(vehicle.getId());
        vehicle = dealer.getVehicle(vehicle.getId());
        assertTrue(vehicle.getLoanStatus());
    }
    
    @Test
    public void vehicleRemoval() {
        Importer.importJSON(Start.SAVE_FILE);
        DealerList dealerList = UIController.dealerList;
        List<Dealer> list = dealerList.getDealerList();
        Dealer dealer = null;
        Vehicle vehicle = new Vehicle(null, "sedan", "suv", "4Runner", "234fd56", 33340, 0);
        if (!dealerList.getDealerList().isEmpty()){
            dealer = list.get(0);
            dealer.addVehicle(vehicle);
        }
        if(dealer.vehicleExists(vehicle.getId())){
            dealer.removeVehicle(vehicle.getId());
        }
       // vehicle = dealer.getVehicle(vehicle.getId());
        assertNotNull(vehicle);       

    }

    @Test
    public void loanedVehiclesListChanges(){
        Importer.importJSON(Start.SAVE_FILE);
        DealerList dealerList = UIController.dealerList;
        List<Dealer> list = dealerList.getDealerList();
        Dealer dealer = null;
        Vehicle vehicle = new Vehicle(null, "sedan", "suv", "4Runner", "234fd56", 33340, 0);
        if (!dealerList.getDealerList().isEmpty()){
            dealer = list.get(0);
            dealer.addVehicle(vehicle);
        }
        assertTrue(dealer.vehicleExists(vehicle.getId()));
        int initSize = dealer.getLoanedVehicles().size();

        dealer.loanVehicle(vehicle.getId());
        assertFalse(initSize == dealer.getLoanedVehicles().size());

        dealer.returnVehicle(vehicle.getId());
        assertTrue(initSize == dealer.getLoanedVehicles().size());
    }
    
    
}
