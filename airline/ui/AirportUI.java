package com.airline.ui;

import com.airline.models.Airport;
import com.airline.models.Airplane; // Add this import statement
import com.airline.models.Flight; // Add this import statement if not already imported

import com.airline.models.FlightStatus;

import com.airline.models.Airport;

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AirportUI extends Application {
    private Airport airport;
    private TextField airportNameField;
    private ListView<String> flightListView;
    private Button addDepartingFlightButton;
    private Button addArrivingFlightButton;

    private BorderPane borderPane;

    @Override
    public void start(Stage primaryStage) {
        // Initialize layout containers
        borderPane = new BorderPane();
        VBox vBox = new VBox();
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        GridPane gridPane = new GridPane();

        // Initialize UI controls
        Label headerLabel = new Label("Airport Console");
        Label airportNameLabel = new Label("Airport Name:");
        airportNameField = new TextField();
        flightListView = new ListView<>();
        addDepartingFlightButton = new Button("Add Departing Flight");
        addArrivingFlightButton = new Button("Add Arriving Flight");

        // Add UI controls to layout containers
        hBox1.getChildren().add(headerLabel);
        hBox2.getChildren().addAll(airportNameLabel, airportNameField, addDepartingFlightButton, addArrivingFlightButton);
        vBox.getChildren().addAll(hBox1, hBox2, gridPane, flightListView);

        // Set layout properties
        VBox.setMargin(hBox2, new Insets(10, 0, 10, 0));
        VBox.setMargin(gridPane, new Insets(10, 0, 10, 0));
        hBox1.setAlignment(Pos.CENTER);
        hBox2.setAlignment(Pos.CENTER);

        // Set event handlers for buttons
        addDepartingFlightButton.setOnAction(e -> handleAddDepartingFlightButton());
        addArrivingFlightButton.setOnAction(e -> handleAddArrivingFlightButton());

        // Set up the scene and show the primary stage
        Scene scene = new Scene(borderPane, 600, 450);
        scene.getStylesheets().add(getClass().getResource("airportUIStyles.css").toExternalForm());
        borderPane.setCenter(vBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Airport Console");
        primaryStage.show();

        MainUI mainUI = new MainUI();
        Button backButton = mainUI.createBackButton(primaryStage);
        vBox.getChildren().add(backButton); // Add the backButton to the vBox
    }

    // Handle addDepartingFlightButton action
    public void handleAddDepartingFlightButton() {
        String flightNumber = "AB123";
        String destinationAirport = "DestinationAirport";
        String departureDate = "2023-06-01"; // Add departure date
        String departureTime = "10:00";
        String arrivalDate = "2023-06-01"; // Add arrival date
        String arrivalTime = "13:00";
        Airplane airplane = new Airplane("Boeing 737", 180, 30, "SomeString");
        double ticketPrice = 100.0;
        int availableSeats = 100;
        int duration = 3; // Add duration

        Flight flight = new Flight(flightNumber, airport.getName(), destinationAirport, departureDate, departureTime, arrivalDate, ticketPrice, availableSeats, duration, airplane, FlightStatus.ONTIME);


        airport.addDepartingFlight(flight);
        flightListView.getItems().add("Departing Flight: " + flight.getDetails());
    }


    // Handle addArrivingFlightButton action

    public void handleAddArrivingFlightButton() {
        String originAirportName = "OriginAirport";
        String departureDate = "2023-06-01"; // Add departure date
        String departureTime = "12:00";
        String arrivalDate = "2023-06-01"; // Add arrival date
        String arrivalTime = "15:00";
        String flightNumber = "AA1234";
        double ticketPrice = 100.0;
        int availableSeats = 150;
        int duration = 3; // Add duration

        Airplane airplane = new Airplane("Boeing 737", 180, 30, "SomeString");

        Flight flight = new Flight(flightNumber, originAirportName, airport.getName(), departureDate, departureTime, arrivalDate, ticketPrice, availableSeats, duration, airplane, FlightStatus.ONTIME);

        airport.addArrivingFlight(flight);
        flightListView.getItems().add("Arriving Flight: " + flight.getDetails());
    }


    public Parent getRoot8() {
        return borderPane; // Return the instance variable borderPane as the root of the UI
    }

}

