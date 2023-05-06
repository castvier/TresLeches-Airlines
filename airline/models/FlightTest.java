//package com.airline.models;
//
////import org.junit.jupiter.api.Assertions;
////import org.junit.jupiter.api.Test;
//
//public class FlightTest {
//
//    @Test
//    public void testGetDetails() {
//        Airplane airplane = new Airplane("Boeing 747", 400);
//        Flight flight = new Flight("AA123", "New York", "Los Angeles", "08:00", "12:00", airplane, 250.0, 300);
//        String expectedDetails = "Flight Number: AA123, Origin: New York, Destination: Los Angeles, Departure: 08:00, Arrival: 12:00, Airplane: Boeing 747, Ticket Price: 250.00, Available Seats: 300";
//        Assertions.assertEquals(expectedDetails, flight.getDetails());
//    }
//
//    @Test
//    public void testReserveSeat() {
//        Airplane airplane = new Airplane("Boeing 747", 400);
//        Flight flight = new Flight("AA123", "New York", "Los Angeles", "08:00", "12:00", airplane, 250.0, 2);
//        Assertions.assertTrue(flight.reserveSeat());
//        Assertions.assertTrue(flight.reserveSeat());
//        Assertions.assertFalse(flight.reserveSeat());
//    }
//
//    @Test
//    public void testUpdateFlight() {
//        Airplane airplane = new Airplane("Boeing 747", 400);
//        Flight flight = new Flight("AA123", "New York", "Los Angeles", "08:00", "12:00", airplane, 250.0, 300);
//        flight.updateFlight("AA456", "Chicago", "10:00");
//        Assertions.assertEquals("AA456", flight.getFlightNumber());
//        Assertions.assertEquals("Chicago", flight.getDestination());
//        Assertions.assertEquals("10:00", flight.getDepartureTime());
//    }
//}
