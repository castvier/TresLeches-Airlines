package com.airline.models;

import java.util.ArrayList;
import java.util.HashMap;

// Airport class containing properties and methods for managing the airport
public class Airport {
   private String name;
   private String APIKey;
   private ArrayList<Flight> departingFlights;
   private ArrayList<Flight> arrivingFlights;
   private HashMap<String, Airplane> airplanesByID;
   private HashMap<String, Airport> airportsByID;

   // Constructor
   public Airport(String name) {
      this.name = name;
      APIKey = randomString(8); // Generate a random 8-character string as the API key
      departingFlights = new ArrayList<>();
      arrivingFlights = new ArrayList<>();
      airplanesByID = new HashMap<>();
      airportsByID = new HashMap<>();
   }

   // Getters and setters
   public String getName() {
      return name;
   }

   public String getAPIKey() {
      return APIKey;
   }

   public ArrayList<Flight> getDepartingFlights() {
      return departingFlights;
   }

   public ArrayList<Flight> getArrivingFlights() {
      return arrivingFlights;
   }

   public void addAirplaneByID(String ID, Airplane airplane) {
      airplanesByID.put(ID, airplane); // Add an airplane to the hashmap of airplanes by ID
   }

   public Airplane getAirplaneByID(String ID) {
      if (airplanesByID.containsKey(ID)) {
         return airplanesByID.get(ID); // Return the airplane associated with the given ID
      }
      return null;
   }

   public void addAirportByID(String ID, Airport airport) {
      airportsByID.put(ID, airport); // Add an airport to the hashmap of airports by ID
   }

   public Airport getAirportByID(String ID) {
      if (airportsByID.containsKey(ID)) {
         return airportsByID.get(ID); // Return the airport associated with the given ID
      }
      return null;
   }

   // Other methods
   public void addDepartingFlight(Flight flight) {
      departingFlights.add(flight); // Add a departing flight to the list of departing flights
   }

   public void addArrivingFlight(Flight flight) {
      arrivingFlights.add(flight); // Add an arriving flight to the list of arriving flights
   }

   public void updateAirplane(Airplane airplane, String ID) {
      airplanesByID.replace(ID, airplane); // Replace the airplane associated with the given ID with the given airplane
   }

   // Generate a random string of length n
   public String randomString(int n) {
      String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
              + "0123456789"
              + "abcdefghijklmnopqrstuvxyz";
      StringBuilder sb = new StringBuilder(n);
      for (int i = 0; i < n; i++) {
         int index = (int) (AlphaNumericString.length() * Math.random());
         sb.append(AlphaNumericString.charAt(index));
      }
      return sb.toString();
   }
}
