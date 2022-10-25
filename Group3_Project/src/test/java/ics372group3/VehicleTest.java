package ics372group3;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ics372group3.Vehicle;

class VehicleTest {

    @Test
    void doesntSetTypeIfNotFoundInList() {
        Vehicle vehicle = new Vehicle(123, null,"any", "ford", "111222", 20000, 454354L);
        vehicle.setType("bad");
        assertNull(vehicle.getType());
    }
    
    @Test
    void setsTypeIfFoundInList() {
        Vehicle vehicle = new Vehicle(123, null,"any", "ford", "111222", 20000, 454354L);
        vehicle.setType("suv");
        assertEquals("suv", vehicle.getType());
    }
    

}
