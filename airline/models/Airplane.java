package com.airline.models;

public class Airplane {
    private int id;
    private final String model; //Edwin
    private final int capacity;
    private final int range;
    private final String serialNumber;
    // Add a constructor without 'id' parameter
    public Airplane(String model, int capacity, int range, String serialNumber) {
        this.model = model;
        this.capacity = capacity;
        this.range = range;
        this.serialNumber = serialNumber;
    }

    public Airplane(String model, int capacity) {
        this.model = model;
        this.capacity = capacity;
        this.range = 0;
        this.serialNumber = "";
    }



    //get model
    public String getModel(){
        return model;
    }

    //get capacity
    public int getCapacity(){
        return capacity;
    }

    //get range
    public int getRange(){
        return range;
    }

    //get serial number
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

