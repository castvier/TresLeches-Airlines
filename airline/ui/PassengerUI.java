package com.airline.ui;
import javafx.scene.Parent;


import com.airline.models.Airplane;
import com.airline.models.Flight;
import com.airline.models.Passenger;
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
/**
 * PassengerUI
 */
public class PassengerUI extends Application {
    // Declare Passenger object and UI input fields
    private Passenger passenger;
    private TextField nameField;
    private TextField flightNumberField;
    private TextField seatNumberField;

    private BorderPane borderPane; // declare borderPane as an instance variable

    @Override
    public void start(Stage primaryStage) {
        // Initialize Passenger object with sample data
        passenger = new Passenger("John Doe", 30, "ABC123", "123 Main St.");

        // Initialize layout elements
        GridPane gridPane = new GridPane();
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        VBox vBox = new VBox();

        // Set up the scene
        Scene scene = new Scene(vBox, 350, 200);
        scene.getStylesheets().add(getClass().getResource("passengerUIStyles.css").toExternalForm());

        // Initialize and set UI labels
        Label headerLabel = new Label("Passenger Console");
        Label nameLabel = new Label("Name:");
        Label flightNumberLabel = new Label("Flight Number:");
        Label seatNumberLabel = new Label("Seat Number:");

        // Initialize and set UI buttons
        Button reserveButton = new Button("Reserve Seat");
        Button viewBookingButton = new Button("View Booking");
        Button cancelBookingButton = new Button("Cancel Booking");

        // Initialize and set UI input fields
        nameField = new TextField();
        flightNumberField = new TextField();
        seatNumberField = new TextField();

        // Add UI elements to layout containers
        hBox1.getChildren().add(headerLabel);
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(flightNumberLabel, 0, 1);
        gridPane.add(flightNumberField, 1, 1);
        gridPane.add(seatNumberLabel, 0, 2);
        gridPane.add(seatNumberField, 1, 2);
        hBox2.getChildren().addAll(reserveButton, viewBookingButton, cancelBookingButton);
        gridPane.add(hBox2, 1, 3);

        // Set layout margins and alignment
        GridPane.setMargin(hBox2, new Insets(10, 0, 0, 0));
        VBox.setMargin(gridPane, new Insets(10));
        hBox1.setAlignment(Pos.CENTER);
        hBox2.setAlignment(Pos.CENTER);
        gridPane.setAlignment(Pos.CENTER);

        // Set button click event handlers
        reserveButton.setOnAction(e -> handleReserveButton());
        viewBookingButton.setOnAction(e -> handleViewBookingButton());
        cancelBookingButton.setOnAction(e -> handleCancelBookingButton());

        // Add layout containers to the main container and display the application window
        vBox.getChildren().addAll(hBox1, gridPane);

// Add the back button to the UI
        MainUI mainUI = new MainUI();
        Button backButton = mainUI.createBackButton(primaryStage);
        vBox.getChildren().add(backButton); // Add the backButton to the vBox

        primaryStage.setScene(scene);
        primaryStage.setTitle("Passenger Console");
        primaryStage.show();
    }

    // Handle click event for the "Reserve Seat" button
    public void handleReserveButton() {
        // Get input values from UI elements
        String name = nameField.getText();
        String flightNumber = flightNumberField.getText();
        String seatNumber = seatNumberField.getText();

        // Update Passenger object and create new Seat, Airplane, and Flight objects
        passenger.setName(name);
        Seat seat= new Seat(seatNumber);
        Airplane airplane = new Airplane("Boeing 747", 366, 10000, "ABC123");
        String departureDate = "2023-06-01"; // Add departure date
        String departureTime = "10:00";
        String arrivalDate = "2023-06-01"; // Add arrival date
        String arrivalTime = "13:00";
        int duration = 3; // Add duration
        Flight flight = new Flight(flightNumber, "Origin Airport", "Destination Airport", departureDate, departureTime, arrivalDate, 500.0, 200, duration, airplane);

        // Uncomment the following lines to create a reservation and add it to the ReservationManager
        // Reservation reservation = new Reservation(seat, passenger, flight);
        // ReservationManager.getInstance().addReservation(reservation);
    }




    // Handle click event for the "View Booking" button
    public void handleViewBookingButton() {
        String name = nameField.getText();

        // Uncomment the following line to search for reservations by passenger name and print them
        // ReservationManager.getInstance().searchReservationsByPassengerName(name).forEach(System.out::println);
    }

    // Handle click event for the "Cancel Booking" button
    public void handleCancelBookingButton() {
        String name = nameField.getText();

        // Uncomment the following line to cancel reservations by passenger name
        // ReservationManager.getInstance().cancelReservationByPassengerName(name);
    }

    // Seat class definition
    public class Seat {
        private String number;
        private boolean isAvailable;

        public Seat(String number) {
            this.number = number;
            this.isAvailable = true;
        }

        public String getNumber() {
            return number;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void setAvailable(boolean isAvailable) {
            this.isAvailable = isAvailable;
        }
    }

    public Parent getRoot2() {
        return borderPane; // Return the instance variable borderPane as the root of the UI
    }

}

