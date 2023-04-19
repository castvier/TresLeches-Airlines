package com.airline.management;
import com.airline.models.Flight;

import java.util.ArrayList;

// Rest of your code



public class FlightManagement {
    private ArrayList<Flight> flights;
    private Airport airport;

    public FlightManagement(Airport airport) {
        this.flights = new ArrayList<>();
        this.airport = airport;
    }

    // Add a flight
    public void addFlight(Flight flight) {
        flights.add(flight);
        if (flight.getOriginAirport().equals(airport)) {
            airport.addDepartingFlight(flight);
        } else if (flight.getDestinationAirport().equals(airport)) {
            airport.addArrivingFlight(flight);
        }
    }

    // Delete a flight
    public void deleteFlight(Flight flight) {
        flights.remove(flight);
        if (flight.getOriginAirport().equals(airport)) {
            airport.getDepartingFlights().remove(flight);
        } else if (flight.getDestinationAirport().equals(airport)) {
            airport.getArrivingFlights().remove(flight);
        }
    }

    // Update a flight
    public void updateFlight(Flight oldFlight, Flight newFlight) {
        int index = flights.indexOf(oldFlight);
        if (index != -1) {
            flights.set(index, newFlight);
            if (oldFlight.getOriginAirport().equals(airport)) {
                int departingIndex = airport.getDepartingFlights().indexOf(oldFlight);
                airport.getDepartingFlights().set(departingIndex, newFlight);
            } else if (oldFlight.getDestinationAirport().equals(airport)) {
                int arrivingIndex = airport.getArrivingFlights().indexOf(oldFlight);
                airport.getArrivingFlights().set(arrivingIndex, newFlight);
            }
        }
    }

    // Get all flights
    public ArrayList<Flight> getFlights() {
        return flights;
    }
}
