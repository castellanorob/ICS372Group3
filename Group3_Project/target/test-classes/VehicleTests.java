import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import ics372group3.Vehicle;

public class VehicleTests {
   

    @Test
    public void vehicleTypeCorrect(){
        Vehicle vehicle = new Vehicle();
        vehicle.setType("sedan");
        assertEquals("sedan", vehicle.getType());
    }
    
    @Test
    public void vehicleTypeNotCorrect(){
        Vehicle vehicle = new Vehicle();
        vehicle.setType("Racing Car");
        assertEquals(null, vehicle.getType());
    }

    @Test
    public void vehicleGetSetTest(){
        Vehicle vehicle = new Vehicle( "1", "suv","benz", "gv123", "343", 50000, 3456721765l);
        assertEquals("1", vehicle.getDealerId());
        assertEquals("suv", vehicle.getType());
        assertEquals("benz", vehicle.getManufacturer());
        assertEquals("gv123", vehicle.getModel());
        assertEquals("343", vehicle.getId());
        assertEquals(50000, vehicle.getPrice());
    }
    
    @Test
    public void vehicleDateConversionCorrect(){
        Vehicle vehicle = new Vehicle();
        vehicle.setAcquisitionDate("1696797986635"); // random   Oct 2023 date
        Date acqDate = vehicle.getAcquisitionDate();
        assertTrue(new Date().before(acqDate)); // testing future
        
    }
    
    @Test
    public void vehicleLoanStatus(){
        Vehicle vehicle = new Vehicle();    
        assertFalse(vehicle.getLoanStatus()); // default 
        
    }

  

}
