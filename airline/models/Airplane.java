package com.airline.models;

public class Airplane {
    private final String model;
    private final int capacity;
    private final int range;
    private final String serialNumber;
    public Airplane(String model, int capacity, int range, String serialNumber){
        this.model = model;
        this.capacity = capacity;
        this.range = range;
        this.serialNumber = serialNumber;
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
}
