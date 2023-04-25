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

    public void addFlight(Flight flight) { //added this
        String sql = "INSERT INTO flights (number, departure_time, arrival_time, origin, destination, airplane_id, price, capacity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, flight.getNumber());
            pstmt.setString(2, flight.getDepartureTime());
            pstmt.setString(3, flight.getArrivalTime());
            pstmt.setString(4, flight.getOrigin());
            pstmt.setString(5, flight.getDestination());
            pstmt.setInt(6, flight.getAirplane().getId());
            pstmt.setDouble(7, flight.getTicketPrice());
            pstmt.setInt(8, flight.getAirplane().getCapacity()); // Change this line
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
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
}
