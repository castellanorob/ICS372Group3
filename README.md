# ICS-372 Group Project 
Group 3 <br/>
Group Members:
- Rob Castellano
- Kean Jaycox
- Faiza Mohamoud
- Sykong Yang

## Project Description
This is a java program that functions as a car company's inventory app. The user is assumed to be an Admin of the car company. In the app they can add dealerships
to their company as well as add vehicles to the dealerships. A description of each class:

- Start (Main)
  - Runs the GUI and imports the master save file.
- UIController
  - Contains logic for GUI
- DealerList
  - The list of dealers owned by this company.
- Dealer
  - A dealership that contains a list of vehicle objects.
- Vehicle
  - A vehicle object containing data about a vehicle such as; manufacturer, model, type, price, etc.
- Importer
  - The class that holds methods for importing vehicle data files into the system. (Currently supports: Json and XML)
- Exporter
  - The class that handles exporting a dealer(s) to a json file.
- fxml files
  - GUI pages

### Testing
Unit tests are included in target/test-classes

#### Files
There are three included data files: 
  - two default vehicle files (json and xml)
  - one master save file 


The project is using Apache Maven as a build tool.
