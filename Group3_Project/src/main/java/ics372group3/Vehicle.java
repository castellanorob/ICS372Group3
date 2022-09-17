package ics372group3;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Vehicle {

    // instance variables in order of JSON file
    private int dealerId;
    private String type;
    private String manufacturer;
    private String model;
    private int id;
    private int price;
    private Date aquisitionDate; 

    // namespaces
    static List<String> types = Arrays.asList("suv", "sedan", "pickup", "sports car");

    public Vehicle(int dealerId, String type, String manufacturer, String model, int id, int price, long aquisitionDate) {
        this.dealerId = dealerId;
        this.type = type;
        this.manufacturer = manufacturer;
        this.model = model;
        this.id = id;
        this.price = price;
        this.aquisitionDate = new Date(aquisitionDate);
    }
    public Vehicle(String type, String manufacturer, String model, int id, int price, long aquisitionDate) {
        this.type = type;
        this.manufacturer = manufacturer;
        this.model = model;
        this.id = id;
        this.price = price;
        this.aquisitionDate = new Date(aquisitionDate);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (types.contains(type)){
            this.type = type;
        } else {
            System.out.println("Error: the type does not exist. Options are: suv, sedan, pickup, sports car.");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getAquisitionDate() {
        return aquisitionDate;
    }

    public void setAquisitionDate(Date aquisitionDate) {
        this.aquisitionDate = aquisitionDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDealerId() {
        return dealerId;
    }

    public void setDealerId(int dealerId) {
        this.dealerId = dealerId;
    }

    public String toString(){
        return "Id: " + id + " Manufacturer: " + manufacturer + " Model: " + model + " Type: " + type + " Price " + price + " Aquisition Date: " + aquisitionDate +"\n";
    }
}