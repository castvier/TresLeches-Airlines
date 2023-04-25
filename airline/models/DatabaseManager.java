package com.airline.models;

import com.airline.models.Flight;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

//JJAS

public class DatabaseManager {
    //JJAS
    private static final String DB_URL = "jdbc:sqlite:tresLechesAirlines.db";


    public DatabaseManager() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            if (conn != null) {
                createFlightTable();
                createPaymentTable();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }



    private void createFlightTable() {
        String sql = "CREATE TABLE IF NOT EXISTS flights (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "number TEXT NOT NULL," +
                "departure_time TEXT NOT NULL," +
                "arrival_time TEXT NOT NULL," +
                "origin TEXT NOT NULL," +
                "destination TEXT NOT NULL," +
                "airplane_id INTEGER NOT NULL," +
                "price REAL NOT NULL," +
                "capacity INTEGER NOT NULL" +
                ");";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void addFlight(Flight flight) { // This method takes a Flight object as an argument and adds it to the flights table in the database
        String sql = "INSERT INTO flights (number, departure_time, arrival_time, origin, destination, airplane_id, price, capacity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        // The SQL query to insert a new flight record into the flights table, using placeholders (?) for the values to be inserted

        try (Connection conn = DriverManager.getConnection(DB_URL); // Establish a connection to the database using the DB_URL constant
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Prepare the SQL statement for execution, replacing the placeholders with actual values

            pstmt.setString(1, flight.getNumber()); // Set the flight number in the prepared statement
            pstmt.setString(2, flight.getDepartureTime()); // Set the flight departure time in the prepared statement
            pstmt.setString(3, flight.getArrivalTime()); // Set the flight arrival time in the prepared statement
            pstmt.setString(4, flight.getOrigin()); // Set the flight origin in the prepared statement
            pstmt.setString(5, flight.getDestination()); // Set the flight destination in the prepared statement
            pstmt.setInt(6, flight.getAirplane().getId()); // Set the airplane_id in the prepared statement, retrieved from the Airplane object associated with the Flight
            pstmt.setDouble(7, flight.getTicketPrice()); // Set the ticket price in the prepared statement
            pstmt.setInt(8, flight.getAirplane().getCapacity()); // Set the airplane capacity in the prepared statement, retrieved from the Airplane object associated with the Flight

            pstmt.executeUpdate(); // Execute the prepared statement to insert the new flight record into the flights table
        } catch (SQLException e) { // Catch any SQLException that might occur during the execution of the SQL query
            System.err.println(e.getMessage()); // Print the error message from the SQLException
        }
    }


    public void removeFlight(String number) {
        String sql = "DELETE FROM flights WHERE number = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, number);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public Optional<Flight> searchFlightsByNumber(String number) {
        String sql = "SELECT * FROM flights WHERE number = ?";
        Flight flight = null;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, number);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Create a Flight object from the result set
                // You need to create a method to convert the result set to a Flight object
                flight = resultSetToFlight(rs);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return Optional.ofNullable(flight);
    }

    public void updateFlight(Flight flight) {
        String sql = "UPDATE flights SET departure_time = ?, arrival_time = ? WHERE number = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, flight.getDepartureTime());
            pstmt.setString(2, flight.getArrivalTime());
            pstmt.setString(3, flight.getNumber());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // Add the resultSetToFlight method to convert the result set to a Flight object
    private Flight resultSetToFlight(ResultSet rs) throws SQLException {
        // Implement the conversion logic
        // You may need to handle how to retrieve the associated Airplane object
        return null; // Replace with the actual Flight object
    }


    private void createPaymentTable() {
        String sql = "CREATE TABLE IF NOT EXISTS payments (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "payment_processor TEXT NOT NULL," +
                "payment_method TEXT NOT NULL," +
                "card_number TEXT NOT NULL," +
                "card_holder_name TEXT NOT NULL," +
                "card_expiry TEXT NOT NULL," +
                "card_cvc TEXT NOT NULL" +
                ");";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void addPayment(Payment payment) {
        String sql = "INSERT INTO payments (payment_processor, payment_method, card_number, card_holder_name, card_expiry, card_cvc) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, payment.getPaymentProcessor());
            pstmt.setString(2, payment.getPaymentMethod());
            pstmt.setString(3, payment.getCardNumber());
            pstmt.setString(4, payment.getCardHolderName());
            pstmt.setString(5, payment.getCardExpiry());
            pstmt.setString(6, payment.getCardCVC());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private void createReservationTable() {
        String sql = "CREATE TABLE IF NOT EXISTS reservations (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "passenger_name TEXT NOT NULL," +
                "passenger_email TEXT NOT NULL," +
                "flight_number TEXT NOT NULL," +
                "departure_airport TEXT NOT NULL," +
                "arrival_airport TEXT NOT NULL," +
                "departure_date TEXT NOT NULL," +
                "departure_time TEXT NOT NULL," +
                "baggage_weight REAL NOT NULL" +
                ");";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void addReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (passenger_name, passenger_email, flight_number, departure_airport, arrival_airport, departure_date, departure_time, baggage_weight) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, reservation.getPassengerName());
            pstmt.setString(2, reservation.getPassengerEmail());
            pstmt.setString(3, reservation.getFlightNumber());
            pstmt.setString(4, reservation.getDepartureAirport());
            pstmt.setString(5, reservation.getArrivalAirport());
            pstmt.setString(6, reservation.getDepartureDate());
            pstmt.setString(7, reservation.getDepartureTime());
//            pstmt.setDouble(8, reservation.getBaggage().getWeight());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void updateReservation(int reservationId, Reservation updatedReservation) {
        String sql = "UPDATE reservations SET passenger_name = ?, passenger_email = ?, flight_number = ?, departure_airport = ?, arrival_airport = ?, departure_date = ?, departure_time = ?, baggage_weight = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, updatedReservation.getPassengerName());
            pstmt.setString(2, updatedReservation.getPassengerEmail());
            pstmt.setString(3, updatedReservation.getFlightNumber());
            pstmt.setString(4, updatedReservation.getDepartureAirport());
            pstmt.setString(5, updatedReservation.getArrivalAirport());
            pstmt.setString(6, updatedReservation.getDepartureDate());
            pstmt.setString(7, updatedReservation.getDepartureTime());
            // pstmt.setDouble(8, updatedReservation.getBaggage().getWeight()); // Uncomment this line if the `getWeight()` method is available in the Baggage class
            pstmt.setInt(9, reservationId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    public void deleteReservation(int reservationId) {
        String sql = "DELETE FROM reservations WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, reservationId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }
}