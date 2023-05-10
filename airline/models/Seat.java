package com.airline.models;

public class Seat {
    private String number;
    private boolean isAvailable;
    private SeatClass seatClass;

    public Seat(String number, SeatClass seatClass) {
        this.number = number;
        this.isAvailable = true;
        this.seatClass = seatClass;
    }


    public boolean isAvailable() {
        return isAvailable;
    }

    public SeatClass getSeatClass() {
        return seatClass;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getSeatNumber() {
        return number;
    }
}
