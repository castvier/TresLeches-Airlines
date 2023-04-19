//manager ui
package com.airline.ui;

import com.airline.models.Airplane;
import com.airline.models.Flight;
import com.airline.models.Manager;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;

public class ManagerUI extends Application {
    private Manager manager;
    private TextField flightNumberField;
    private TextField departureTimeField;
    private TextField arrivalTimeField;
    private Label searchResultLabel;

    private BorderPane borderPane; // Declare borderPane as an instance variable

    @Override
    public void start(Stage primaryStage) {
        // Initialize manager, UI components, and set up layout
        manager = new Manager(new ArrayList<Flight>());
        BorderPane borderPane = new BorderPane();
        VBox vBox = new VBox();
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        GridPane gridPane = new GridPane();
        Label headerLabel = new Label("Manager Console");
        Label flightNumberLabel = new Label("Flight Number:");
        Label departureTimeLabel = new Label("Departure Time:");
        Label arrivalTimeLabel = new Label("Arrival Time:");
        Button addButton = new Button("Add");
        Button removeButton = new Button("Remove");
        Button updateButton = new Button("Update");
        Button searchButton = new Button("Search");
        flightNumberField = new TextField();
        departureTimeField = new TextField();
        arrivalTimeField = new TextField();
        searchResultLabel = new Label();
        vBox.getChildren().addAll(hBox1, hBox2, gridPane);
        hBox1.getChildren().add(headerLabel);
        hBox2.getChildren().addAll(flightNumberLabel, flightNumberField, searchButton);
        gridPane.add(departureTimeLabel, 0, 0);
        gridPane.add(departureTimeField, 1, 0);
        gridPane.add(arrivalTimeLabel, 0, 1);
        gridPane.add(arrivalTimeField, 1, 1);
        gridPane.add(addButton, 0, 2);
        gridPane.add(removeButton, 1, 2);
        gridPane.add(updateButton, 0, 3);
        gridPane.add(searchResultLabel, 1, 3);
        VBox.setMargin(hBox2, new Insets(10, 0, 10, 0));
        VBox.setMargin(gridPane, new Insets(10, 0, 10, 0));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        hBox1.setAlignment(Pos.CENTER);
        hBox2.setAlignment(Pos.CENTER_LEFT);
        gridPane.setAlignment(Pos.CENTER);

        // Set event handlers for buttons
        addButton.setOnAction(e -> handleAddButton());
        removeButton.setOnAction(e -> handleRemoveButton());
        updateButton.setOnAction(e -> handleUpdateButton());
        searchButton.setOnAction(e -> handleSearchButton());

        // Set up the scene and show the stage
        Scene scene = new Scene(borderPane, 450, 300);
        borderPane.setCenter(vBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Manager Console");
        primaryStage.show();
    }

    public void handleAddButton() {
        // Gather input values and create a Flight object
        String flightNumber = flightNumberField.getText();
        String departureTime = departureTimeField.getText();
        String arrivalTime = arrivalTimeField.getText();

        // Provide the missing arguments, e.g., flightCode, airplane, price, and capacity
        String flightCode = "SampleFlightCode";
        Airplane airplane = new Airplane("Boeing 737", 160, 26000, "N12345");

        double price = 150.0;
        int capacity = 100;
        String extraArg = "G1"; // Replace "G1" with the appropriate value for your use case.

        // Create a Flight object and add it to the manager
        Flight flight = new Flight(flightNumber, departureTime, arrivalTime, flightCode, extraArg, airplane, price, capacity);
        manager.addFlight(flight);
    }

    public void handleRemoveButton() {
        // Remove a flight from the manager based on the flight number
        String flightNumber = flightNumberField.getText();
        manager.removeFlight(flightNumber);
    }

    public void handleUpdateButton() {
        // Update departure and arrival times for a flight in the manager
        String flightNumber = flightNumberField.getText();
        String departureTime = departureTimeField.getText();
        String arrivalTime = arrivalTimeField.getText();
        Optional<Flight> flightOptional = manager.searchFlightsByNumber(flightNumber);
        if (flightOptional.isPresent()) {
            Flight flight = flightOptional.get();
            manager.updateFlight(flight, departureTime, arrivalTime);
        }
    }

    public void handleSearchButton() {
        // Search for a flight by flight number and display the result
        String flightNumber = flightNumberField.getText();
        Optional<Flight> flightOptional = manager.searchFlightsByNumber(flightNumber);

        if (flightOptional.isPresent()) {
            Flight flight = flightOptional.get();
            searchResultLabel.setText("Flight found: " + flight.toString());
        } else {
            searchResultLabel.setText("Flight not found.");
        }
    }

    public Parent getRoot4() {
        return borderPane; // Return the instance variable borderPane as the root of the UI
    }
}


