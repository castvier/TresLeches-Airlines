package com.airline.management;

import com.airline.models.Airport;
import com.airline.models.Flight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class FlightManagement {
    private List<Flight> flights;
    private Airport airport;

    public FlightManagement(Airport airport) {
        this(airport, "airline/data/flights.csv");
    }

    public FlightManagement(Airport airport, String filePath) {
        this.flights = new ArrayList<>();
        this.airport = airport;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Skip the first line (header row)
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                String flightNumber = values[0];
                String originAirport = values[1];
                String destinationAirport = values[2];
                String departureDate = values[3];
                String departureTime = values[4];
                int availableSeats = Integer.parseInt(values[5]);
                double ticketPrice = 0.0;
                if (values.length == 7) {
                    ticketPrice = Double.parseDouble(values[6]);
                }
                Flight flight = new Flight(flightNumber, originAirport, destinationAirport, departureDate, departureTime, null, ticketPrice, availableSeats);
                addFlight(flight);
            }
        } catch (IOException e) {
            System.err.println("Error reading flights from file: " + e.getMessage());
        }
        generateArbitraryFlights();
    }

    public void generateArbitraryFlights() {
        String[] destinations = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix"};
        String[] dates = {"2023-06-01", "2023-06-02", "2023-06-03", "2023-06-04", "2023-06-05"};
        String[] times = {"06:00", "12:00", "18:00", "22:00"};

        for (int i = 1; i <= 20; i++) {
            String flightNumber = "FL" + String.format("%03d", i);
            String originAirport = airport.getName();
            String destinationAirport = destinations[i % destinations.length];
            String departureDate = dates[i % dates.length];
            String departureTime = times[i % times.length];
            int availableSeats = 150;
            double ticketPrice = 100.0;

            Flight flight = new Flight(flightNumber, originAirport, destinationAirport, departureDate, departureTime, null, ticketPrice, availableSeats);
            addFlight(flight);
        }
    }


    public List<Flight> searchFlights(String destination, String departureDate, String departureTime) {
        List<Flight> matchingFlights = new ArrayList<>();
        LocalDate searchDate = null;
        LocalTime searchTime = null;

        if (!departureDate.isEmpty()) {
            searchDate = LocalDate.parse(departureDate, DateTimeFormatter.ISO_LOCAL_DATE);
        }

        if (!departureTime.isEmpty()) {
            searchTime = LocalTime.parse(departureTime, DateTimeFormatter.ISO_LOCAL_TIME);
        }

        for (Flight flight : flights) {
            boolean match = true;

            LocalDate flightDepartureDate = LocalDate.parse(flight.getDepartureDate(), DateTimeFormatter.ISO_LOCAL_DATE);

            LocalTime flightDepartureTime = LocalTime.parse(flight.getDepartureTime(), DateTimeFormatter.ISO_LOCAL_TIME);

            if (searchDate != null && !flightDepartureDate.isEqual(searchDate)) {
                match = false;
            }

            if (searchTime != null && !flightDepartureTime.equals(searchTime)) {
                match = false;
            }

            if (!flight.getDestinationAirport().equalsIgnoreCase(destination)) {
                match = false;
            }

            if (match) {
                matchingFlights.add(flight);
            }
        }

        return matchingFlights;
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
    public List<Flight> getFlights() {
        return flights;
    }

}
