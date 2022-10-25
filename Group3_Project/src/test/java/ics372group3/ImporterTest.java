package ics372group3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ImporterTest {

    @Test
    void test() {
        Importer.importJSON("12513.json");
        DealerList dealerList = Importer.getDealerList();
        assertNotNull(dealerList);
        assertEquals(1, dealerList.getDealerList().size());
        Dealer dealer = dealerList.getDealerList().get(0);
        assertEquals("n/a", dealer.getName());
    }

}
