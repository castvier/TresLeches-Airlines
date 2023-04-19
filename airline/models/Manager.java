package com.airline.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class Manager {
    private HashMap<String, Flight> flights; // HashMap of flights managed by this Manager instance
    private ArrayList<Flight> flights; // List of flights managed by this Manager instance

    public Manager(ArrayList<Flight> flights) {
        this.flights = flights; // Initialize the list of flights
    }

    public void addFlight(Flight flight) {
        flights.add(flight); // Add a new flight to the list
    }

    public void removeFlight(String number) {
        flights.removeIf(flight -> flight.getNumber().equals(number)); // Remove a flight from the list by flight number
    }

    public ArrayList<Flight> getFlights() {
        return flights; // Get the list of flights managed by this Manager instance
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights; // Set a new list of flights managed by this Manager instance
    }

    public Optional<Flight> searchFlightsByNumber(String number) {
        return flights.stream().filter(flight -> flight.getNumber().equals(number)).findFirst();
        // Search for a flight by flight number and return it as an Optional object
    }

    //set new departure and arrival times for flight
    public void updateFlight(Flight flight, String departureTime, String arrivalTime) {
        flight.setDepartureTime(departureTime); // Set a new departure time for the flight
        flight.setArrivalTime(arrivalTime); // Set a new arrival time for the flight
    }
}


