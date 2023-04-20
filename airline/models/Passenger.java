package com.airline.models;

// Passenger class to manage passenger personal data and seat assignments
public class Passenger {

    // comment update
    private String name; // Passenger's name
    private final int age; // Passenger's age, cannot be changed once set
    private String email; // Passenger's email address
    private String phoneNumber; // Passenger's phone number
    private String seatNumber; // Passenger's assigned seat number

    public Passenger(String name, int age, String email, String phoneNumber) {
        this.name = name; // Set the passenger's name
        this.age = age; // Set the passenger's age
        this.email = email; // Set the passenger's email address
        this.phoneNumber = phoneNumber; // Set the passenger's phone number
    }

    public void setName(String name) {
        this.name = name; // Set a new name for the passenger
    }

    public String getName() {
        return name; // Get the passenger's name
    }

    public String getPhoneNumber() {
        return phoneNumber; // Get the passenger's phone number
    }

    public String getEmail() {
        return email; // Get the passenger's email address
    }

    public String getSeatNumber() {
        return seatNumber; // Get the passenger's assigned seat number
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber; // Set a new seat number for the passenger
    }
}
