package com.airline.models;


public class Flight {

    private String flightNumber;
    private final String originAirport;
    private String destinationAirport;
    private String departureDate;
    private String departureTime;
    private String arrivalTime;
    private final Airplane airplane;
    private final double ticketPrice;
    private int availableSeats;

    // Constructor
    public Flight(String flightNumber, String originAirport, String destinationAirport, String departureDate, String departureTime, Airplane airplane, double ticketPrice, int availableSeats) {
        this.flightNumber = flightNumber;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.airplane = airplane;
        this.ticketPrice = ticketPrice;
        this.availableSeats = availableSeats;
    }

    // Update flight data
    public void updateFlight(String flightNumber, String destination, String date) {
        this.flightNumber = flightNumber;
        this.destinationAirport = destination;
        this.departureDate = date;
    }


    // Format flight details as a string
    public String getDetails() {
        return String.format("Flight Number: %s, Origin: %s, Destination: %s, Departure: %s, Arrival: %s, Airplane: %s, Ticket Price: %.2f, Available Seats: %d",
                flightNumber, originAirport, destinationAirport, departureTime, arrivalTime, airplane.getModel(), ticketPrice, availableSeats);
    }


    // Get available seats
    public int getAvailableSeats() {
        return availableSeats;
    }

    // Get flight details as a string
    public String viewFlight() {
        String flightInfo = "Flight Details:\n" + getDetails();
        return flightInfo;
    }

    // Reserve a seat
    public boolean reserveSeat() {
        if(this.availableSeats > 0){
            this.availableSeats--;
            return true;
        } else {
            return false;
        }
    }

    // Get flight number
    public String getNumber() {
        return this.flightNumber;
    }

    // Set departure time
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    // Set arrival time
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime= departureTime;
    }

    // Get origin airport
    public String getOriginAirport() {
        return originAirport;
    }

    public String getDepartureDate() {
        return departureDate;
    }


    // Get destination airport
    public String getDestinationAirport() {
        return destinationAirport;
    }

    // Get flight number
    public String getFlightNumber() {
        return flightNumber;
    }

    // Get origin
    public String getOrigin() {
        return originAirport;
    }

    // Get destination
    public String getDestination() {
        return destinationAirport;
    }

    // Get departure time
    public String getDepartureTime() {
        return departureTime;
    }

    // Get arrival time
    public String getArrivalTime() {
        return arrivalTime;
    }

    // Get airplane
    public Airplane getAirplane(){
        return airplane;
    }

    // Get price of ticket
    public double getTicketPrice() {
        return ticketPrice;
    }
}

