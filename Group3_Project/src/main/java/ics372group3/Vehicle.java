package ics372group3;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Vehicle {

    // instance variables in order of JSON file
    private int dealership_id;
    private String vehicle_type;
    private String vehicle_manufacturer;
    private String vehicle_model;
    private String vehicle_id;
    private int price;
    private Date aquisition_date; 

    // namespaces
    static List<String> types = Arrays.asList("suv", "sedan", "pickup", "sports car");

    public Vehicle(int dealership_id, String vehicle_type, String vehicle_manufacturer, String vehicle_model, String vehicle_id, int price, Date aquisition_date) {
        this.dealership_id = dealership_id;
        this.vehicle_type = vehicle_type;
        this.vehicle_manufacturer = vehicle_manufacturer;
        this.vehicle_model = vehicle_model;
        this.vehicle_id = vehicle_id;
        this.price = price;
        this.aquisition_date = aquisition_date;
    }

    public String getType() {
        return vehicle_type;
    }

    public void setType(String type) {
        if (types.contains(type)){
            this.vehicle_type = type;
        } else {
            System.out.println("Error: the type does not exist. Options are: suv, sedan, pickup, sports car.");
        }
    }

    public String getId() {
        return vehicle_id;
    }

    public void setId(String id) {
        this.vehicle_id = id;
    }

    public String getManufacturer() {
        return vehicle_manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.vehicle_manufacturer = manufacturer;
    }

    public String getModel() {
        return vehicle_model;
    }

    public void setModel(String model) {
        this.vehicle_model = model;
    }

    public Date getAquisitionDate() {
        return aquisition_date;
    }

    public void setAquisitionDate(Date aquisitionDate) {
        this.aquisition_date = aquisitionDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDealerId() {
        return dealership_id;
    }

    public void setDealerId(int dealerId) {
        this.dealership_id = dealerId;
    }

    public String toString(){
        return "Vehicle Id: " + vehicle_id + " Manufacturer: " + vehicle_manufacturer + " Model: " + vehicle_model + " Type: " + vehicle_type + " Price " + price + " Aquisition Date: " + aquisition_date +"\n";
    }
}