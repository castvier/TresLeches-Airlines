package com.airline.models;

import java.util.List;
import java.util.ArrayList;

public class Airplane {
    private int id;
    private final String model;
    private final int capacity;
    private final int range;
    private final String serialNumber;
    private List<Seat> seats;
    private int availableSeats;
    public Airplane(String model, int capacity) {
        this(model, capacity, 0, "");
    }

    public Airplane(String model, int capacity, int range, String serialNumber) {
        this.model = model;
        this.capacity = capacity;
        this.range = range;
        this.serialNumber = serialNumber;
        this.seats = new ArrayList<>();
    }

    public int getAvailableSeats() {
        return availableSeats;
    }
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
    public List<Seat> getSeats() {
        return seats;
    }

    public String getModel(){
        return model;
    }

    public int getCapacity(){
        return capacity;
    }

    public int getRange(){
        return range;
    }

    public String getSerialNumber(){
        return serialNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
