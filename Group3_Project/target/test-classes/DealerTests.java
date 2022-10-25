import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ics372group3.Dealer;
import ics372group3.DealerList;
import ics372group3.Importer;
import ics372group3.Start;
import ics372group3.UIController;
import ics372group3.Vehicle;

public class DealerTests {
    
    private static DealerList dealerList = UIController.dealerList;
    private static List<Dealer> list = dealerList.getDealerList();
    private static Dealer dealer;
    private static Vehicle vehicle = new Vehicle(null, "sedan", "suv", "4Runner", "234fd56", 33340, 0);

    @BeforeAll
    public static void setup(){
        Importer.importJSON(Start.SAVE_FILE);
    }

    @Test
    public void vehicleExistsAfterAdding(){
        if (!dealerList.getDealerList().isEmpty()){
            dealer = list.get(0);
            vehicle.setDealerId(dealer.getDealerId());
            dealer.addVehicle(vehicle);
        }
        assertTrue(dealer.vehicleExists(vehicle.getId()));
    }

    @Test
    public void vehicleGoneAfterRemoval() {
        if (!dealerList.getDealerList().isEmpty()){
            dealer = list.get(0);
            dealer.addVehicle(vehicle);
        }
        if(dealer.vehicleExists(vehicle.getId())){
            dealer.removeVehicle(vehicle.getId());
        }
        assertFalse(dealer.vehicleExists(vehicle.getId()));       

    }

    @Test
    public void settingLoanTrue(){
        if (!dealerList.getDealerList().isEmpty()){
            dealer = list.get(0);
            dealer.addVehicle(vehicle);
        }
        assertTrue(dealer.vehicleExists(vehicle.getId()));
        assertFalse(vehicle.getLoanStatus());
        dealer.loanVehicle(vehicle.getId());
        assertTrue(vehicle.getLoanStatus());
    }

    @Test
    public void loanedVehiclesListChanges(){
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
