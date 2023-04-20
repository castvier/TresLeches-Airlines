package com.airline.models;

// Passenger class manages personal data along with their seat assignments.
public class Passenger {

    private String name; // name of passenger
    private final int age; // age final because can't be changed afterwards
    private String email; // email address
    private String phoneNumber; // phone number
    private String seatNumber; // assigned seat number

    public Passenger(String name, int age, String email, String phoneNumber) {
        this.name = name; // setting passenger's name
        this.age = age; // age
        this.email = email; // email address
        this.phoneNumber = phoneNumber; // phone number
    }

    public void setName(String name) {
        this.name = name; // new name for the passenger
    }

    public String getName() {
        return name; // getting the passenger's name
    }

    public String getPhoneNumber() {
        return phoneNumber; // passenger's phone number
    }

    public String getEmail() {
        return email; // passenger's email
    }

    public String getSeatNumber() {
        return seatNumber; // passenger's assigned seat
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber; // seat number for the passenger
    }
}
