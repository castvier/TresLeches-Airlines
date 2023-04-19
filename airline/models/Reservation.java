package com.airline.models;

public class Reservation {
    // Declare fields to store passenger and flight data
    private String passengerName; // passenger name
    private String passengerEmail; // passenger email
    private String flightNumber; // flight number
    private String departureAirport; // departure airport code
    private String arrivalAirport; // arrival airport code
    private String departureDate; // departure date
    private String departureTime; // departure time
    private Baggage baggage; // baggage information //asd

    // Constructor
    public Reservation(String passengerName, String passengerEmail, String flightNumber, String departureAirport, String arrivalAirport, String departureDate, String departureTime) {
        this.passengerName = passengerName;
        this.passengerEmail = passengerEmail;
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.baggage = new Baggage(); // initialize baggage object
    }

    // Getters
    public String getPassengerName() {
        return passengerName;
    }

    public String getPassengerEmail() {
        return passengerEmail;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public Baggage getBaggage() {
        return baggage;
    }

    // Setters
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setBaggage(Baggage baggage) {
        this.baggage = baggage;
    }
}
