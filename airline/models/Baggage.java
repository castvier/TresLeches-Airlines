package com.airline.models;

public class Baggage {
    private String weight;
    private String size;

    public Baggage() {
        this.weight = "";
        this.size = "";
    }

    // Check in baggage
    public void checkIn(String weight, String size) {
        this.weight = weight;
        this.size = size;
    }

    // Modify baggage
    public void modify(String weight, String size) {
        this.weight = weight;
        this.size = size;
    }

    // Remove baggage
    public void remove() {
        this.weight = "";
        this.size = "";
    }

    // View baggage details
    public String viewBaggage() {
        if (this.weight.isEmpty() && this.size.isEmpty()) {
            return "No baggage checked in.";
        } else {
            return "Baggage Weight: " + this.weight + "kg, Baggage Size: " + this.size + "cm";
        }
    }
}
