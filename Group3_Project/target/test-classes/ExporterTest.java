

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import ics372group3.Dealer;
import ics372group3.DealerList;
import ics372group3.Exporter;
import ics372group3.Importer;
import ics372group3.Start;
import ics372group3.UIController;

class ExporterTest {
    
    private static DealerList dealerList = UIController.dealerList;

    @Test
    void test() {
        Importer.importJSON(Start.SAVE_FILE);
        try {
            dealerList.addDealer(new Dealer("123testID"));
            Exporter.exportDealerJson("123testID");
            File file = new File("123testID.json");
            assertTrue(file.exists());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
