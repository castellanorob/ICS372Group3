import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ics372group3.Dealer;
import ics372group3.DealerList;
import ics372group3.Importer;
import ics372group3.Start;
import ics372group3.UIController;
import ics372group3.Vehicle;

public class DealerListTests {
    private static DealerList dealerList = UIController.dealerList;
    private static String testDealerID = "123testID";
    private static Vehicle vehicle = new Vehicle(testDealerID, "sedan", "suv", "4Runner", "234fd56", 33340, 0);

    @BeforeAll
    public static void importFileSetup(){
        Importer.importJSON(Start.SAVE_FILE);
    }

    @Test
    public void dealerAdded(){
        assertFalse(dealerList.dealerExist("123testID"));
        dealerList.addDealer(new Dealer(testDealerID));
        assertTrue(dealerList.dealerExist("123testID"));
    }
    
    @Test
    public void vehicleAddedToDealer(){
        dealerList.addDealer(new Dealer(testDealerID));
        assertTrue(dealerList.dealerExist(testDealerID));
        dealerList.addToDealer(testDealerID, vehicle);
        for (Dealer dealer : dealerList.getDealerList()) {
            if (dealer.getDealerId().equalsIgnoreCase(testDealerID)){
                assertTrue(dealer.vehicleExists(vehicle.getId()));
                assertEquals(testDealerID, vehicle.getDealerId());
            }
        }
    }

}
