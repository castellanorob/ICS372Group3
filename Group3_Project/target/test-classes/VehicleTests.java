import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import ics372group3.Vehicle;

public class VehicleTests {
    private Vehicle vehicle = new Vehicle(null, null, null, null, null, 0, 0);

    @Test
    public void vehicleTypeCorrect(){
        vehicle.setType("sedan");
        assertEquals("sedan", vehicle.getType());
    }

    @Test
    public void vehicleManufacturerCorrect(){
        vehicle.setManufacturer("Chevy");
        String model = "cHeVy";
        assertTrue(model.equalsIgnoreCase(vehicle.getManufacturer()));
    }

    @Test
    public void vehicleModelCorrect(){
        vehicle.setModel("Corvette");
        assertEquals("Corvette", vehicle.getModel());
    }

    @Test
    public void vehicleIDCorrect(){
        vehicle.setId("192837");
        assertEquals("192837", vehicle.getId());
    }

    @Test
    public void vehiclePriceCorrect(){
        vehicle.setPrice(50000);
        assertEquals(50000, vehicle.getPrice());
    }

    @Test
    public void vehicleAcquisitionDateCorrect(){
        vehicle.setAcquisitionDate("1234567654321");
        assertEquals(new Date(1234567654321l), vehicle.getAcquisitionDate());
    }

}
