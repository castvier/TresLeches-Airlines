package com.airline.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    private Manager manager;
    private Flight flight1, flight2, flight3;

    @BeforeEach
    void setUp() {
        // Initialize the Manager instance with some flights
        flight1 = new Flight("ABC123", "New York", "Los Angeles", "10:00", "14:00");
        flight2 = new Flight("DEF456", "Chicago", "Denver", "12:00", "15:00");
        flight3 = new Flight("GHI789", "San Francisco", "Seattle", "11:00", "13:00");

        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);

        manager = new Manager(flights);
    }

    @Test
    void addFlight() {
        // Add a new flight to the Manager instance
        Flight flight4 = new Flight("JKL012", "Miami", "Orlando", "9:00", "10:00");
        manager.addFlight(flight4);

        // Assert that the Manager instance now contains the new flight
        assertTrue(manager.getFlights().contains(flight4));
    }

    @Test
    void removeFlight() {
        // Remove an existing flight from the Manager instance
        manager.removeFlight(flight1.getNumber());

        // Assert that the Manager instance no longer contains the removed flight
        assertFalse(manager.getFlights().contains(flight1));
    }

    @Test
    void getFlights() {
        // Get the list of flights managed by the Manager instance
        ArrayList<Flight> flights = manager.getFlights();

        // Assert that the list contains all of the flights that were added in setUp()
        assertTrue(flights.contains(flight1));
        assertTrue(flights.contains(flight2));
        assertTrue(flights.contains(flight3));
    }

    @Test
    void setFlights() {
        // Set a new list of flights for the Manager instance
        ArrayList<Flight> newFlights = new ArrayList<>();
        newFlights.add(new Flight("MNO345", "Dallas", "Houston", "8:00", "9:00"));
        newFlights.add(new Flight("PQR678", "Boston", "Washington DC", "9:30", "11:00"));
        manager.setFlights(newFlights);

        // Assert that the Manager instance now contains only the new flights
        assertFalse(manager.getFlights().contains(flight1));
        assertFalse(manager.getFlights().contains(flight2));
        assertFalse(manager.getFlights().contains(flight3));

        assertTrue(manager.getFlights().contains(newFlights.get(0)));
        assertTrue(manager.getFlights().contains(newFlights.get(1)));
    }

    @Test
    void searchFlightsByNumber() {
        // Search for an existing flight by its number
        Optional<Flight> result = manager.searchFlightsByNumber(flight2.getNumber());

        // Assert that the search returns the correct flight as an Optional object
        assertTrue(result.isPresent());
        assertEquals(flight2, result.get());

        // Search for a non-existent flight by a fake number
        Optional<Flight> fakeResult = manager.searchFlightsByNumber("FAKE000");

        // Assert that the search returns an empty Optional object
        assertTrue(fakeResult.isEmpty());
    }

    @Test
    void updateFlight() {
        // Update an existing flight with new departure and
