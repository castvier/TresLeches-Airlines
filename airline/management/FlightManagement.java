package com.airline.management;

import com.airline.models.Airport;
import com.airline.models.Flight;
import com.airline.models.Airplane;
import com.airline.models.FlightStatus;
import com.airline.models.Seat;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JTextArea;
import com.airline.models.SeatClass;
import com.airline.models.SeatStatus;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

        // Create an arbitrary Airplane object
        Airplane airplane = new Airplane("Boeing 737", 150);

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
                int defaultDuration = 0;
                // Include the airplane object as the last parameter
                Flight flight = new Flight(flightNumber, originAirport, destinationAirport, departureDate, departureTime, null, ticketPrice, availableSeats, defaultDuration, airplane, FlightStatus.ONTIME);

                addFlight(flight);
            }
        } catch (IOException e) {
            System.err.println("Error reading flights from file: " + e.getMessage());
        }
        generateArbitraryFlights();
    }

    public Flight getFlightByNumber(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight;
            }
        }
        return null;
    }

    public void generateArbitraryFlights() {
        String[] destinations = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix"};
        String[] dates = {"2023-06-01", "2023-06-02", "2023-06-03", "2023-06-04", "2023-06-05"};
        String[] times = {"06:00", "12:00", "18:00", "22:00"};
        int[] durations = {5, 6, 4, 3, 4}; // Example durations in hours

        // Create an arbitrary Airplane object
        Airplane airplane = new Airplane("Boeing 737", 150);

        for (int i = 1; i <= 20; i++) {
            String flightNumber = "FL" + String.format("%03d", i);
            String originAirport = airport.getName();
            String destinationAirport = destinations[i % destinations.length];
            String departureDate = dates[i % dates.length];
            String departureTime = times[i % times.length];
            int availableSeats = 150;
            double ticketPrice = 100.0;
            int duration = durations[i % durations.length];

            DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
            DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;

            LocalDate depDate = LocalDate.parse(departureDate, dateFormatter);
            LocalTime depTime = LocalTime.parse(departureTime, timeFormatter);

            LocalDateTime depDateTime = LocalDateTime.of(depDate, depTime);
            LocalDateTime arrDateTime = depDateTime.plusHours(duration);

            String arrivalDate = arrDateTime.format(dateFormatter);

            // Include the airplane object as the last parameter
            Flight flight = new Flight(flightNumber, originAirport, destinationAirport, departureDate, departureTime, arrivalDate, ticketPrice, availableSeats, duration, airplane, FlightStatus.ONTIME);

            addFlight(flight);
        }
    }

    public void generateArbitrarySeats(JList flightList, JTextArea flightInfoDisplay, JTextArea seatSelectionDisplay) {
        // Get the selected flight
        Flight selectedFlight = (Flight) flightList.getSelectedValue();

        if (selectedFlight != null) {
            // Generate arbitrary seats for the selected flight
            List<Seat> availableSeats = selectedFlight.getAvailableSeats();
            StringBuilder sb = new StringBuilder("Available Seats:\n");
            for (Seat seat : availableSeats) {
                sb.append(seat.getSeatNumber()).append("\n");
            }

            // Update the selected flight with the generated seats
            selectedFlight.setSeats(availableSeats);

            // Update the flight information display
            flightInfoDisplay.setText(selectedFlight.toString());

            // Update the seat selection display
            seatSelectionDisplay.setText(sb.toString());
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

            if (destination != null && !destination.isEmpty() && !flight.getDestinationAirport().equalsIgnoreCase(destination)) {
                match = false;
            }

            if (match) {
                matchingFlights.add(flight);
            }
        }

        return matchingFlights;
    }

    public List<Flight> searchFlightsByDestination(String destination) {
        List<Flight> matchingFlights = new ArrayList<>();

        for (Flight flight : flights) {
            if (flight.getDestination().equalsIgnoreCase(destination)) {
                matchingFlights.add(flight);
            }
        }
        return matchingFlights;
    }




    public List<Flight> searchFlightsByLocation(String destinationAirport, String departureDate, String departureTime, String location) {
        List<Flight> matchingFlights = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getDestination().equalsIgnoreCase(destinationAirport) && flight.getDepartureDate().equalsIgnoreCase(departureDate) && flight.getDepartureTime().equalsIgnoreCase(departureTime) && flight.getLocation().equalsIgnoreCase(location)) {
                matchingFlights.add(flight);
            }
        }
        return matchingFlights;
    }

    // Add a flight
    public void addFlight(Flight flight) {
        flights.add(flight); // Changed from addFlight(flight) to flights.add(flight)
        if (flight.getOriginAirport().equals(airport.getName())) { // Changed from airport to airport.getName()
            airport.addDepartingFlight(flight);
        } else if (flight.getDestinationAirport().equals(airport.getName())) { // Changed from airport to airport.getName()
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
