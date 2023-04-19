package com.airline.database;

import com.airline.models.Flight;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

public class DatabaseManager {
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

    public void addFlight(Flight flight) {
        String sql = "INSERT INTO flights (number, departure_time, arrival_time, origin, destination, airplane_id, price, capacity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, flight.getNumber());
            pstmt.setString(2, flight.getDepartureTime());
            pstmt.setString(3, flight.getArrivalTime());
            pstmt.setString(4, flight.getOrigin());
            pstmt.setString(5, flight.getDestination());
            pstmt.setInt(6, flight.getAirplane().getId());
            pstmt.setDouble(7, flight.getPrice());
            pstmt.setInt(8, flight.getCapacity());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // Implement other methods like removeFlight, updateFlight, searchFlightsByNumber, etc.
}
