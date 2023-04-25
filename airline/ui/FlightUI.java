package com.airline.ui;

import com.airline.models.Airplane;
import com.airline.models.Flight;
import com.airline.models.Airport;
import com.airline.management.FlightManagement;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import com.airline.models.DatabaseManager;

;
;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FlightUI extends Application {
    private FlightManagement flightManagement;
    private Airport airport;
    private Flight flight; // declare the flight variable
    private TextField flightNumberField;
    private TextField dateField;
    private TextField destinationField;
    private Label searchResultLabel;
    private DatabaseManager databaseManager;

    private BorderPane borderPane; // declare borderPane as an instance variable

    @Override
    public void start(Stage primaryStage) {
        // Initialize airport, flightManagement, and flight
        databaseManager = new DatabaseManager();
        airport = new Airport("AirportName");
        flightManagement = new FlightManagement(airport);
        flight = new Flight("", "", "", "", "", new Airplane("", 0, 0, ""), 0.0, 0);

        // Initialize UI components and set up layout
        BorderPane borderPane = new BorderPane();
        VBox vBox = new VBox();
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        HBox hBox3 = new HBox();
        GridPane gridPane = new GridPane();
        Label headerLabel = new Label("Flight Console");
        Label flightNumberLabel = new Label("Flight Number:");
        Label dateLabel = new Label("Date:");
        Label destinationLabel = new Label("Destination:");
        Button addFlightButton = new Button("Add Flight");
        Button updateFlightButton = new Button("Update Flight");
        Button viewFlightButton = new Button("View Flight");
        Button deleteFlightButton = new Button("Delete Flight");
        flightNumberField = new TextField();
        dateField = new TextField();
        destinationField = new TextField();
        searchResultLabel = new Label();
        vBox.getChildren().addAll(hBox1, hBox2, hBox3, gridPane);
        hBox1.getChildren().add(headerLabel);
        hBox2.getChildren().addAll(flightNumberLabel, flightNumberField, dateLabel, dateField,
                destinationLabel, destinationField, addFlightButton);
        hBox3.getChildren().addAll(updateFlightButton, viewFlightButton, deleteFlightButton);
        gridPane.add(searchResultLabel, 0, 0);
        VBox.setMargin(hBox2, new Insets(10, 0, 10, 0));
        VBox.setMargin(hBox3, new Insets(10, 0, 10, 0));
        VBox.setMargin(gridPane, new Insets(10, 0, 10, 0));
        gridPane.setAlignment(Pos.CENTER);
        hBox1.setAlignment(Pos.CENTER);
        hBox2.setAlignment(Pos.CENTER);
        hBox3.setAlignment(Pos.CENTER);

        // Set up event handlers
        addFlightButton.setOnAction(e -> handleAddFlightButton());
        updateFlightButton.setOnAction(e -> handleUpdateFlightButton());
        viewFlightButton.setOnAction(e -> handleViewFlightButton());
        deleteFlightButton.setOnAction(e -> handleDeleteFlightButton());

        // Set up the scene and stage
        Scene scene = new Scene(borderPane, 600, 450);
        borderPane.setCenter(vBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Flight Console");
        primaryStage.show();
    }

    // Handle add flight button event
// Handle add flight button event
    public void handleAddFlightButton() {
        String flightNumber = flightNumberField.getText();
        String date = dateField.getText();
        String destination = destinationField.getText();

        // Create a new Flight object based on the input fields
        // Make sure you have an appropriate Airplane object
        Airplane airplane = new Airplane("Model", 150, 3000, "SerialNumber");
        Flight newFlight = new Flight(flightNumber, airport.getName(), destination, date, date, airplane, 0, 0);

        // Add the flight using the flight management object
        flightManagement.addFlight(newFlight);

        // Add the flight to the database using the databaseManager object
        databaseManager.addFlight(newFlight);

        // Assign the newly created flight to the "flight" variable
        flight = newFlight;
    }

    // Handle delete flight button event
    public void handleDeleteFlightButton() {
        String flightNumber = flightNumberField.getText();
        String date = dateField.getText();
        String destination = destinationField.getText();
        flightManagement.deleteFlight(flight);
        flight = new Flight(flightNumber, airport.getName(), destination, date, date, null, 0, 0);
        searchResultLabel.setText("Flight deleted.");
    }

    // Handle update flight button event
    public void handleUpdateFlightButton() {
        String flightNumber = flightNumberField.getText();
        String date = dateField.getText();
        String destination = destinationField.getText();
        flight.updateFlight(flightNumber, destination, date);
    }

    // Handle view flight button event
    public void handleViewFlightButton() {
        String flightInfo = flight.viewFlight();
        searchResultLabel.setText(flightInfo);
    }

    // Get the root of the UI
    public Parent getRoot5() {
        return borderPane; // Return the instance variable borderPane as the root of the UI
    }
}
