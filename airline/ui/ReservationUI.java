package com.airline.ui;

import com.airline.models.Reservation;

import javafx.scene.Parent;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReservationUI extends Application {
    private Reservation reservation;
    private TextField passengerNameField;
    private TextField passengerEmailField;
    private TextField flightNumberField;
    private TextField departureAirportField;
    private TextField arrivalAirportField;
    private TextField departureDateField;
    private TextField departureTimeField;
    private Label reservationLabel;
    private Button createReservationButton;
    private Button modifyReservationButton;
    private Button cancelReservationButton;
    private BorderPane borderPane; // declare borderPane as an instance variable

    @Override
    public void start(Stage primaryStage) {
        // Initialize instance variables
        reservation = new Reservation("", "", "", "", "", "", "");
        borderPane = new BorderPane(); // assign a new instance to borderPane
        VBox vBox = new VBox();
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        HBox hBox3 = new HBox();
        GridPane gridPane = new GridPane();

        // Create labels and text fields for input
        Label headerLabel = new Label("Reservation Console");
        Label passengerNameLabel = new Label("Passenger Name:");
        Label passengerEmailLabel = new Label("Passenger Email:");
        Label flightNumberLabel = new Label("Flight Number:");
        Label departureAirportLabel = new Label("Departure Airport:");
        Label arrivalAirportLabel = new Label("Arrival Airport:");
        Label departureDateLabel = new Label("Departure Date:");
        Label departureTimeLabel = new Label("Departure Time:");
        passengerNameField = new TextField();
        passengerEmailField = new TextField();
        flightNumberField = new TextField();
        departureAirportField = new TextField();
        arrivalAirportField = new TextField();
        departureDateField = new TextField();
        departureTimeField = new TextField();

        // Create buttons for creating, modifying, and cancelling reservations
        createReservationButton = new Button("Create Reservation");
        modifyReservationButton = new Button("Modify Reservation");
        cancelReservationButton = new Button("Cancel Reservation");
        reservationLabel = new Label();

        // Set up UI layout
        hBox1.getChildren().add(headerLabel);
        hBox2.getChildren().addAll(passengerNameLabel, passengerNameField, passengerEmailLabel, passengerEmailField, flightNumberLabel, flightNumberField);
        hBox3.getChildren().addAll(departureAirportLabel, departureAirportField, arrivalAirportLabel, arrivalAirportField, departureDateLabel, departureDateField, departureTimeLabel, departureTimeField);
        vBox.getChildren().addAll(hBox1, hBox2, hBox3, gridPane, createReservationButton, modifyReservationButton, cancelReservationButton, reservationLabel);
        VBox.setMargin(hBox2, new Insets(10, 0, 10, 0));
        VBox.setMargin(hBox3, new Insets(10, 0, 10, 0));
        VBox.setMargin(gridPane, new Insets(10, 0, 10, 0));
        hBox1.setAlignment(Pos.CENTER);
        hBox2.setAlignment(Pos.CENTER);
        hBox3.setAlignment(Pos.CENTER);
        createReservationButton.setOnAction(e -> handleCreateReservationButton());
        modifyReservationButton.setOnAction(e -> handleModifyReservationButton());
        cancelReservationButton.setOnAction(e -> handleCancelReservationButton());
        Scene scene = new Scene(borderPane, 800, 450);
        borderPane.setCenter(vBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Reservation Console");
        primaryStage.show();
    }
    public void handleCreateReservationButton() {
        // Get text from the input fields
        String passengerName = passengerNameField.getText();
        String passengerEmail = passengerEmailField.getText();
        String flightNumber = flightNumberField.getText();
        String departureAirport = departureAirportField.getText();
        String arrivalAirport = arrivalAirportField.getText();
        String departureDate = departureDateField.getText();
        String departureTime = departureTimeField.getText();

        // Create a new reservation object with the input data
        reservation = new Reservation(passengerName, passengerEmail, flightNumber, departureAirport, arrivalAirport, departureDate, departureTime);

        // Set the reservation label text to display the created reservation
        reservationLabel.setText("Reservation created: " + reservation.getPassengerName() + " - " + reservation.getFlightNumber());
    }

    public void handleModifyReservationButton() {
        // Get text from the input fields
        String passengerName = passengerNameField.getText();
        String passengerEmail = passengerEmailField.getText();
        String flightNumber = flightNumberField.getText();
        String departureAirport = departureAirportField.getText();
        String arrivalAirport = arrivalAirportField.getText();
        String departureDate = departureDateField.getText();
        String departureTime = departureTimeField.getText();

        // Modify the existing reservation object with the new input data
        reservation.setPassengerName(passengerName);
        reservation.setPassengerEmail(passengerEmail);
        reservation.setFlightNumber(flightNumber);
        reservation.setDepartureAirport(departureAirport);
        reservation.setArrivalAirport(arrivalAirport);
        reservation.setDepartureDate(departureDate);
        reservation.setDepartureTime(departureTime);

        // Set the reservation label text to display the modified reservation
        reservationLabel.setText("Reservation modified: " + reservation.getPassengerName() + " - " + reservation.getFlightNumber());
    }

    public void handleCancelReservationButton() {
        // Clear the input fields and set the reservation object to a new instance with empty fields
        reservation = new Reservation("", "", "", "", "", "", "");
        passengerNameField.clear();
        passengerEmailField.clear();
        flightNumberField.clear();
        departureAirportField.clear();
        arrivalAirportField.clear();
        departureDateField.clear();
        departureTimeField.clear();

        // Set the reservation label text to display the cancellation message
        reservationLabel.setText("Reservation canceled.");
    }

    public Parent getRoot() {
        return borderPane; // Return the instance variable borderPane as the root of the UI
    }

}
       
