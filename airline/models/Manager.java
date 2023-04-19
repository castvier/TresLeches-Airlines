package com.airline.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class Manager {
    private DatabaseManager dbManager;
//    private ArrayList<Flight> flights; // List of flights managed by this Manager instance
    private HashMap<String, Flight> flights;

    public Manager(ArrayList<Flight> flights) {
        this.dbManager = new DatabaseManager();
        setFlights(flights); // Initialize the HashMap of flights
//        this.flights = flights; // Initialize the list of flights
    }

    public void addFlight(Flight flight) {
          flights.put(flight.getNumber(), flight);
//        flights.add(flight); // Add a new flight to the list
    }

    public void removeFlight(String number) {
        dbManager.removeFlight(number);
//        flights.removeIf(flight -> flight.getNumber().equals(number)); // Remove a flight from the list by flight number
    }

    public ArrayList<Flight> getFlights() {
//        return flights; // Get the list of flights managed by this Manager instance
        return new ArrayList<>(flights.values()); // Return the list of flights managed by this Manager instance
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = new HashMap<>();
        for (Flight flight : flights) {
            this.flights.put(flight.getNumber(), flight);
        }
//        this.flights = flights; // Set a new list of flights managed by this Manager instance
    }

    public Optional<Flight> searchFlightsByNumber(String number) {
        return dbManager.searchFlightsByNumber(number);
//        return flights.stream().filter(flight -> flight.getNumber().equals(number)).findFirst();
        // Search for a flight by flight number and return it as an Optional object
    }

    //set new departure and arrival times for flight
    public void updateFlight(Flight flight, String departureTime, String arrivalTime) {
        dbManager.updateFlight(flight);
//        flight.setDepartureTime(departureTime); // Set a new departure time for the flight
//        flight.setArrivalTime(arrivalTime); // Set a new arrival time for the flight
    }
}


