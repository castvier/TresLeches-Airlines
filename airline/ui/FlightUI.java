package com.airline.ui;
import java.util.List;
import javafx.scene.control.ListView;
import java.util.ArrayList;


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
    private ListView<String> flightListView;
    private Label timeLabel;
    private TextField timeField;

    private BorderPane borderPane; // declare borderPane as an instance variable

    @Override
    public void start(Stage primaryStage) {
        // Initialize airport, flightManagement, and flight
        databaseManager = new DatabaseManager();
        airport = new Airport("AirportName");
        flightManagement = new FlightManagement(airport);
        flight = new Flight("", airport.getName(), "", "", "", "", 0.0, 0, 0, new Airplane("Model", 150, 3000, "SerialNumber"));

        timeLabel = new Label("Time:");
        timeField = new TextField();
        flightListView = new ListView<>(); // Initialize flightListView before updating it
        flightListView.setPrefHeight(200);

        // Initialize UI components and set up layout
        borderPane = new BorderPane();
        VBox vBox = new VBox();
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        HBox hBox3 = new HBox();
        Label headerLabel = new Label("Flight Console");
        Label flightNumberLabel = new Label("Flight Number:");
        Label dateLabel = new Label("Date:");
        Label destinationLabel = new Label("Destination:");
        Button addFlightButton = new Button("Add Flight");
        Button updateFlightButton = new Button("Update Flight");
        Button viewFlightButton = new Button("View Flight");
        Button deleteFlightButton = new Button("Delete Flight");
        Button searchFlightButton = new Button("Search Flights");
        flightNumberField = new TextField();
        dateField = new TextField();
        destinationField = new TextField();
        searchResultLabel = new Label();

        vBox.getChildren().addAll(hBox1, hBox2, hBox3, flightListView);

        hBox1.getChildren().add(headerLabel);
        hBox2.getChildren().addAll(flightNumberLabel, flightNumberField, dateLabel, dateField,
                timeLabel, timeField, destinationLabel, destinationField, addFlightButton);
        hBox3.getChildren().addAll(updateFlightButton, viewFlightButton, deleteFlightButton);
        hBox3.getChildren().add(searchFlightButton);
        VBox.setMargin(hBox2, new Insets(10, 0, 10, 0));
        VBox.setMargin(hBox3, new Insets(10, 0, 10, 0));

        // Set up event handlers
        addFlightButton.setOnAction(e -> handleAddFlightButton());
        updateFlightButton.setOnAction(e -> handleUpdateFlightButton());
        viewFlightButton.setOnAction(e -> handleViewFlightButton());
        deleteFlightButton.setOnAction(e -> handleDeleteFlightButton());
        searchFlightButton.setOnAction(e -> handleSearchFlightButton());
        flightListView.setOnMouseClicked(e -> handleFlightListViewClick());


        // Set up the scene and stage
        Scene scene = new Scene(borderPane, 600, 450);
        scene.getStylesheets().add(getClass().getResource("flightUIStyles.css").toExternalForm());
        borderPane.setCenter(vBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Flight Console");
        primaryStage.show();
        flightListView.setVisible(false);

//        flightManagement.generateArbitraryFlights(); // Moved the generation of arbitrary flights after initializing the ListView
        updateFlightListView(flightManagement.getFlights());

        MainUI mainUI = new MainUI();
        Button backButton = mainUI.createBackButton(primaryStage);
        vBox.getChildren().add(backButton); // Add the backButton to the vBox
    }


    private void handleFlightListViewClick() {
        Flight selectedFlight = flightManagement.getFlightByNumber(flightListView.getSelectionModel().getSelectedItem().split(" ")[0]);

        if (selectedFlight != null) {
            // Create a new Stage (window) for the popup
            Stage popupStage = new Stage();
            VBox vbox = new VBox();
            vbox.setPadding(new Insets(10, 10, 10, 10));
            vbox.setSpacing(10);

            Label titleLabel = new Label("You selected this Flight:");
            Label flightNumberLabel = new Label("Flight Number: " + selectedFlight.getFlightNumber());
            Label originLabel = new Label("Origin: " + selectedFlight.getOrigin());
            Label destinationLabel = new Label("Destination: " + selectedFlight.getDestination());
            Label departureDateLabel = new Label("Departure Date: " + selectedFlight.getDepartureDate());
            Label arrivalDateLabel = new Label("Arrival Date: " + selectedFlight.getArrivalDate());

            vbox.getChildren().addAll(titleLabel, flightNumberLabel, originLabel, destinationLabel, departureDateLabel, arrivalDateLabel);

            Scene popupScene = new Scene(vbox, 300, 200);
            popupStage.setScene(popupScene);
            popupStage.setTitle("Flight Information");
            popupStage.show();
        }
    }



    // Handle add flight button event
// Handle add flight button event
    public void handleAddFlightButton() {
        String flightNumber = flightNumberField.getText();
        String date = dateField.getText();
        String destination = destinationField.getText();

        // Create and add a time field for departure time
        Label timeLabel = new Label("Time:");
        TextField timeField = new TextField();
        // Add the timeField to the scene or layout as needed

        // Get the departure time from the timeField
        String departureTime = timeField.getText();

        // Set the arrival date value as needed, for now, it's initialized as an empty string
        String arrivalDate = "";

        // Create a new Flight object based on the input fields
        // Make sure you have an appropriate Airplane object
        Airplane airplane = new Airplane("Model", 150, 3000, "SerialNumber");
        Flight newFlight = new Flight(flightNumber, airport.getName(), destination, date, departureTime, arrivalDate, 0.0, 0, 0, airplane);

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

//        // Re-assign the flight variable with an appropriate Airplane object
//        Airplane airplane = new Airplane("Model", 150, 3000, "SerialNumber");
//        flight = new Flight(flightNumber, airport.getName(), destination, date, date, airplane, 0, 0);

        searchResultLabel.setText("Flight deleted.");
    }


    public void handleSearchFlightButton() {
        String destinationAirport = destinationField.getText();
        String departureDate = dateField.getText();
        String departureTime = timeField.getText();

        // Generate arbitrary flights only when the "Search Flights" button is clicked
        flightManagement.generateArbitraryFlights();

        List<Flight> matchingFlights = flightManagement.searchFlights(destinationAirport, departureDate, departureTime);

        // Create a new list to hold all flights (arbitrary and matching)
        List<Flight> allFlights = new ArrayList<>(flightManagement.getFlights());
        allFlights.addAll(matchingFlights);

        // Update the flightListView with all flights (arbitrary and matching)
        flightListView.setVisible(true);
        updateFlightListView(allFlights);
    }




    public void updateFlightListView(List<Flight> flights) {
        flightListView.getItems().clear();
        for (Flight flight : flights) {
            String airplaneModel = "";
            if (flight.getAirplane() != null) {
                airplaneModel = flight.getAirplane().getModel();
            }
            flightListView.getItems().add(flight.getFlightNumber() + " | " + airplaneModel + " | " + flight.getDestination() + " | " + flight.getDepartureDate());
        }
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
