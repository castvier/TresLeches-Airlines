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
    private String arrivalDate;
    private int duration;

    private FlightStatus flightStatus; // Changed the type from String to FlightStatus

    public Flight(String flightNumber, String originAirport, String destinationAirport, String departureDate, String departureTime, String arrivalDate, double ticketPrice, int availableSeats, int duration, Airplane airplane, FlightStatus flightStatus) {
        this.flightNumber = flightNumber;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.ticketPrice = ticketPrice;
        this.availableSeats = availableSeats;
        this.duration = duration;
        this.airplane = airplane;
        this.flightStatus = flightStatus; // This should work now
    }







    public FlightStatus getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(FlightStatus flightStatus) {
        this.flightStatus = flightStatus;
    }


    // Add a getter for duration
    public int getDuration() {
        return duration;
    }


    // Update flight data
    public void updateFlight(String flightNumber, String destination, String date) {
        this.flightNumber = flightNumber;
        this.destinationAirport = destination;
        this.departureDate = date;
    }
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    // Format flight details as a string
    // Format flight details as a string
    public String getDetails() {
        return String.format("Flight Number: %s, Origin: %s, Destination: %s, Departure Date: %s, Departure Time: %s, Arrival Date: %s, Arrival Time: %s, Airplane: %s, Ticket Price: %.2f, Available Seats: %d",
                flightNumber, originAirport, destinationAirport, departureDate, departureTime, arrivalDate, arrivalTime, airplane.getModel(), ticketPrice, availableSeats);
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

    public String getDateAsString() {
        return this.departureDate;
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

