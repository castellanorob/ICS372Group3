import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ics372group3.DealerList;
import ics372group3.Importer;
import ics372group3.Start;
import ics372group3.UIController;
import ics372group3.Vehicle;

public class ImportTests {

    public static DealerList dealerList = UIController.dealerList;
    
    Vehicle vehicle = new Vehicle("123testID", "sedan", "suv", "4Runner", "234fd56", 33340, 0);

    @BeforeAll
    public static void importFileSetup(){
        Importer.importJSON(Start.SAVE_FILE);
    }

    @Test
    public void notEmptyAfterImport() {
        assertTrue(dealerList.getDealerList().size() > 0);
    }
  
}