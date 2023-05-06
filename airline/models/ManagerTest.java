package com.airline.test;

import com.airline.models.Flight;
import com.airline.models.Manager;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ManagerTest {

    private Manager manager;

    @BeforeEach
    void setUp() {
        // Set up a new Manager instance with a list of flights
        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(new Flight("ABC123", "New York", "London", "10:00", "18:00"));
        flights.add(new Flight("DEF456", "London", "Paris", "12:00", "14:00"));
        manager = new Manager(flights);
    }

    @Test
    void testAddFlight() {
        // Test adding a new flight to the Manager's list of flights
        Flight newFlight = new Flight("GHI789", "Paris", "Madrid", "15:00", "17:00");
        manager.addFlight(newFlight);
        Assertions.assertTrue(manager.getFlights().contains(newFlight));
    }

    @Test
    void testRemoveFlight() {
        // Test removing a flight from the Manager's list of flights
        String flightNumber = "ABC123";
        manager.removeFlight(flightNumber);
        Assertions.assertTrue(manager.getFlights().stream().noneMatch(flight -> flight.getNumber().equals(flightNumber)));
    }

    @Test
    void testSearchFlightsByNumber() {
        // Test searching for a flight by flight number
        String flightNumber = "DEF456";
        Flight expectedFlight = manager.getFlights().stream().filter(flight -> flight.getNumber().equals(flightNumber)).findFirst().orElse(null);
        Assertions.assertEquals(expectedFlight, manager.searchFlightsByNumber(flightNumber).orElse(null));
    }

    @Test
    void testUpdateFlight() {
        // Test updating the departure and arrival times for a flight
        String flightNumber = "ABC123";
        Flight flight = manager.getFlights().stream().filter(f -> f.getNumber().equals(flightNumber)).findFirst().orElse(null);
        if (flight != null) {
            String newDepartureTime = "11:00";
            String newArrivalTime = "19:00";
            manager.updateFlight(flight, newDepartureTime, newArrivalTime);
            Assertions.assertEquals(newDepartureTime, flight.getDepartureTime());
            Assertions.assertEquals(newArrivalTime, flight.getArrivalTime());
        } else {
            Assertions.fail("Flight not found");
        }
    }
}
