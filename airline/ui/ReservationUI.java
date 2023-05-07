package com.airline.ui;

import com.airline.models.Reservation;

import javafx.scene.Parent;
import java.util.List;
import java.util.Map;
import com.airline.models.Flight;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.io.IOException;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;


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
    private List<String[]> reservationData;
    private Stage primaryStage;

    private Map<Button, String[]> buttonDataMap;


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

        // Declare primaryStage here
        this.primaryStage = primaryStage;

        // Read data from CSV file and create buttons for each row of data
        FlowPane flowPane = new FlowPane();
        reservationData = readDataFromCSV();
        for (String[] row : reservationData) {
            String flightNumber = row[0];
            String departureAirport = row[1];
            String arrivalAirport = row[2];
            String departureDate = row[3];
            String departureTime = row[4];
            Button button = new Button(flightNumber + " - " + departureAirport + " to " + arrivalAirport + " on " + departureDate + " at " + departureTime);
            button.setOnAction(e -> handleFlightSelection(button, row));


            flowPane.getChildren().add(button);
        }


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
        createReservationButton.setOnAction(e -> handleCreateReservationButton(primaryStage));
        modifyReservationButton.setOnAction(e -> handleModifyReservationButton());
        cancelReservationButton.setOnAction(e -> handleCancelReservationButton());
        Scene scene = new Scene(borderPane, 800, 450);
        scene.getStylesheets().add(getClass().getResource("reservationUIStyles.css").toExternalForm());
        borderPane.setCenter(vBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Reservation Console");
        primaryStage.show();

        int numReservations = 5;
        for (int i = 0; i < numReservations; i++) {
            int reservationIndex = i;
            String[] reservationFields = reservationData.get(reservationIndex);
            String reservationButtonText = reservationFields[0] + " - " + reservationFields[1] + " to " + reservationFields[2] + " on " + reservationFields[3] + " at " + reservationFields[4];
            Button reservationButton = new Button(reservationButtonText);
            reservationButton.setOnAction(e -> {
                handleReservationButton(reservationFields);
            });
            vBox.getChildren().add(reservationButton);
        }



        MainUI mainUI = new MainUI();
        Button backButton = mainUI.createBackButton(primaryStage);
        vBox.getChildren().add(backButton); // Add the backButton to the vBox
        Button proceedToPaymentButton = new Button("Proceed to Payment");
        proceedToPaymentButton.setOnAction(e -> {
            try {
                new PaymentUI(reservation).start(primaryStage);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });




    }

    private List<String[]> readDataFromCSV() {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("airline/data/flights.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }


    private Button selectedButton;

    public void handleFlightSelection(Button button, String[] row) {
        // Set the text fields based on the selected row
        flightNumberField.setText(row[0]);
        departureAirportField.setText(row[1]);
        arrivalAirportField.setText(row[2]);
        departureDateField.setText(row[3]);
        departureTimeField.setText(row[4]);

        selectedButton = button;

        // Show an alert with the selected row of the CSV
        Alert selectedFlightAlert = new Alert(AlertType.INFORMATION);
        selectedFlightAlert.setTitle("Selected Flight");
        selectedFlightAlert.setHeaderText("Flight Information");
        selectedFlightAlert.setContentText("Flight Number: " + row[0] + "\n" +
                "Departure Airport: " + row[1] + "\n" +
                "Arrival Airport: " + row[2] + "\n" +
                "Departure Date: " + row[3] + "\n" +
                "Departure Time: " + row[4]);
        selectedFlightAlert.showAndWait();
    }



    public void handleReservationButton(String[] row) {
        String passengerName = passengerNameField.getText();
        String passengerEmail = passengerEmailField.getText();
        String flightNumber = row[0];
        String departureAirport = row[1];
        String arrivalAirport = row[2];
        String departureDate = row[3];
        String departureTime = row[4];

        reservation = new Reservation(passengerName, passengerEmail, flightNumber, departureAirport, arrivalAirport, departureDate, departureTime);

        reservationLabel.setText("Reservation created: " + reservation.getPassengerName() + " - " + reservation.getFlightNumber());

        Alert paymentAlert = new Alert(AlertType.CONFIRMATION);
        paymentAlert.setTitle("Payment Confirmation");
        paymentAlert.setHeaderText("Proceed to Payment?");
        paymentAlert.setContentText("Click 'OK' to proceed to payment, or click 'Cancel' to cancel the reservation.");

        paymentAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    new PaymentUI(reservation).start(primaryStage);

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } else if (response == ButtonType.CANCEL) {
                handleCancelReservationButton();
            }
        });
    }





    public void handleCreateReservationButton(Stage primaryStage) {
        // Get text from the input fields
        String passengerName = passengerNameField.getText();
        String passengerEmail = passengerEmailField.getText();
        String flightNumber = flightNumberField.getText();
        String departureAirport = departureAirportField.getText();
        String arrivalAirport = arrivalAirportField.getText();
        String departureDate = departureDateField.getText();
        String departureTime = departureTimeField.getText();

        // Create a new Reservation object with the input data
        reservation = new Reservation(passengerName, passengerEmail, flightNumber, departureAirport, arrivalAirport, departureDate, departureTime);

        // Set the reservation label text to display the created reservation
        reservationLabel.setText("Reservation created: " + reservation.getPassengerName() + " - " + reservation.getFlightNumber());




        // Create an alert for displaying the selected flight information
        Alert flightInfoAlert = new Alert(AlertType.INFORMATION);
        flightInfoAlert.setTitle("Selected Flight Information");
        flightInfoAlert.setHeaderText("You have selected the following flight:");
        flightInfoAlert.setContentText("Flight Number: " + flightNumber + "\n" +
                "Departure Airport: " + departureAirport + "\n" +
                "Arrival Airport: " + arrivalAirport + "\n" +
                "Departure Date: " + departureDate + "\n" +
                "Departure Time: " + departureTime);

        // Show the flight information alert and wait for the user's acknowledgment
        flightInfoAlert.showAndWait();

        // Create an alert for payment confirmation
        Alert paymentAlert = new Alert(AlertType.CONFIRMATION);
        paymentAlert.setTitle("Payment Confirmation");
        paymentAlert.setHeaderText("Proceed to Payment?");
        paymentAlert.setContentText("Click 'OK' to proceed to payment, or click 'Cancel' to cancel the reservation.");

        // Show the payment alert and wait for the user's response
        paymentAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    new PaymentUI(reservation).start(primaryStage);

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } else if (response == ButtonType.CANCEL) {
                handleCancelReservationButton();
            }
        });
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
       
