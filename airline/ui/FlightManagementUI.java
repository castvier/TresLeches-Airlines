package com.airline.ui;

import com.airline.models.Airport;
import com.airline.models.Flight; // Add this import
import com.airline.management.FlightManagement;
import com.airline.models.Airplane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FlightManagementUI extends Application {
    private FlightManagement flightManagement;
    private ListView<String> flightListView;
    private TextField originAirportField;
    private TextField destinationAirportField;
    private TextField departureTimeField;
    private TextField arrivalTimeField;
    private Button addButton;
    private Button deleteButton;
    private Button updateButton;

    private BorderPane borderPane; // declare borderPane as an instance variable

    @Override
    public void start(Stage primaryStage) {
        Airport airport = new Airport("Sample Airport");
        flightManagement = new FlightManagement(airport);

        BorderPane borderPane = new BorderPane();
        VBox vBox = new VBox();
        HBox headerHBox = new HBox();
        HBox inputFieldsHBox = new HBox();
        HBox buttonsHBox = new HBox();

        Label headerLabel = new Label("Flight Management Console");
        originAirportField = new TextField();
        destinationAirportField = new TextField();
        departureTimeField = new TextField();
        arrivalTimeField = new TextField();
        flightListView = new ListView<>();

        addButton = new Button("Add Flight");
        deleteButton = new Button("Delete Flight");
        updateButton = new Button("Update Flight");

        headerHBox.getChildren().add(headerLabel);
        inputFieldsHBox.getChildren().addAll(new Label("Origin Airport:"), originAirportField, new Label("Destination Airport:"), destinationAirportField, new Label("Departure Time:"), departureTimeField, new Label("Arrival Time:"), arrivalTimeField);
        buttonsHBox.getChildren().addAll(addButton, deleteButton, updateButton);

        vBox.getChildren().addAll(headerHBox, inputFieldsHBox, buttonsHBox, flightListView);
        VBox.setMargin(inputFieldsHBox, new Insets(10, 0, 10, 0));
        VBox.setMargin(buttonsHBox, new Insets(10, 0, 10, 0));

        headerHBox.setAlignment(Pos.CENTER);
        inputFieldsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setAlignment(Pos.CENTER);

        addButton.setOnAction(e -> handleAddButton());
        deleteButton.setOnAction(e -> handleDeleteButton());
        updateButton.setOnAction(e -> handleUpdateButton());

        Scene scene = new Scene(borderPane, 800, 450);
        borderPane.setCenter(vBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Flight Management Console");
        primaryStage.show();
    }

    // Handle add button event
    private void handleAddButton() {
        String originAirport = originAirportField.getText();
        String destinationAirport = destinationAirportField.getText();
        String departureTime = departureTimeField.getText();
        String arrivalTime = arrivalTimeField.getText();

        // Add these lines to provide the missing parameters
        String flightNumber = "AB123"; // Replace with a suitable flight number
        Airplane airplane = new Airplane("Boeing 737", 180, 30, "SomeString"); // Replace with the actual values required by your Airplane constructor
        double ticketPrice = 100.0;
        int availableSeats = 180;

        Flight flight = new Flight(flightNumber, originAirport, destinationAirport, departureTime, arrivalTime, airplane, ticketPrice, availableSeats);
        flightManagement.addFlight(flight);
        flightListView.getItems().add(flight.getDetails());
    }

    // Handle delete button event
    private void handleDeleteButton() {
        int selectedIndex = flightListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Flight selectedFlight = flightManagement.getFlights().get(selectedIndex);
            flightManagement.deleteFlight(selectedFlight);
            flightListView.getItems().remove(selectedIndex);
        }
    }

    // Handle update button event
    private void handleUpdateButton() {
        int selectedIndex = flightListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Flight oldFlight = flightManagement.getFlights().get(selectedIndex);
            String flightNumber = oldFlight.getFlightNumber(); // Assuming flight number does not change
            String originAirport = originAirportField.getText();
            String destinationAirport = destinationAirportField.getText();
            String departureTime = departureTimeField.getText();
            String arrivalTime = arrivalTimeField.getText();
            Airplane airplane = oldFlight.getAirplane(); // Assuming airplane does not change
            double ticketPrice = oldFlight.getTicketPrice(); // Assuming ticket price does not change
            int availableSeats = oldFlight.getAvailableSeats(); // Assuming available seats do not change

            Flight updatedFlight = new Flight(flightNumber, originAirport, destinationAirport, departureTime, arrivalTime, airplane, ticketPrice, availableSeats);
            flightManagement.updateFlight(oldFlight, updatedFlight);
            flightListView.getItems().set(selectedIndex, updatedFlight.getDetails());
        }
    }

    public Parent getRoot6() {
        return borderPane; // Return the instance variable borderPane as the root of the UI
    }

}

