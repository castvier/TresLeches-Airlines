package com.airline.ui;
import javafx.scene.Parent;
import com.airline.models.Airport;
import com.airline.models.SeatClass;
import com.airline.models.Seat;
import com.airline.management.FlightManagement;
import com.airline.models.Airplane;
import com.airline.models.Flight;
import com.airline.models.Passenger;
import com.airline.models.FlightStatus;
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
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;


/**
 * PassengerUI
 */
public class PassengerUI extends Application {
    // Declare Passenger object and UI input fields
    Airport airport = new Airport("Airport Name");
    String defaultFlightNumber = "ABC123"; // Replace with a valid flight number
    FlightManagement flightManagement = new FlightManagement(airport);

//    private FlightManagement flightManagement = new FlightManagement();
    private VBox rootPane; // This should be the root container for your PassengerUI layout
    private Passenger passenger;
    private TextField nameField;
    private TextField flightNumberField;
    private TextField seatNumberField;

    private Flight selectedFlight;
    private BorderPane borderPane; // declare borderPane as an instance variable
    @Override
    public void start(Stage primaryStage) {
        // Initialize Passenger object with sample data
        // Initialize Passenger object with sample data
        passenger = new Passenger("John Doe", "john.doe@example.com", "ABC123");
//        Airport airport = new Airport("Airport Name");
//
//        FlightManagement flightManagement = new FlightManagement(airport);
        PassengerUI passengerUI = new PassengerUI(flightManagement);


//        // Create a FlightManagement object and pass it to the PassengerUI constructor
//        FlightManagement flightManagement = new FlightManagement();
//        PassengerUI passengerUI = new PassengerUI(flightManagement);
        selectedFlight = getFlightByFlightNumber(defaultFlightNumber);
        displayAvailableSeats();
        rootPane = new VBox(); // Initialize rootPane here
        GridPane gridPane = new GridPane();
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        VBox vBox = new VBox();

        // Set up the scene
        Scene scene = new Scene(rootPane, 1024, 1000); // Replace 'vBox' with 'rootPane'
        scene.getStylesheets().add(getClass().getResource("passengerUIStyles.css").toExternalForm());
        // Add layout containers to the main container and display the application window
        rootPane.getChildren().addAll(hBox1, gridPane); // Replace 'vBox' with 'rootPane'


        // Initialize and set UI labels
        Label headerLabel = new Label("Passenger Console");
        Label nameLabel = new Label("Name:");
        Label flightNumberLabel = new Label("Flight Number:");
        Label seatNumberLabel = new Label("Seat Number:");

        // Initialize and set UI buttons
        Button reserveButton = new Button("Reserve Seat");
        Button viewAvailableSeatsButton = new Button("View Available Seats");
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
        hBox2.getChildren().addAll(reserveButton, viewAvailableSeatsButton, cancelBookingButton);
        gridPane.add(hBox2, 1, 3);

        // Set layout margins and alignment
        GridPane.setMargin(hBox2, new Insets(10, 0, 0, 0));
        VBox.setMargin(gridPane, new Insets(10));
        hBox1.setAlignment(Pos.CENTER);
        hBox2.setAlignment(Pos.CENTER);
        gridPane.setAlignment(Pos.CENTER);

        // Set button click event handlers
        reserveButton.setOnAction(e -> handleReserveButton(primaryStage));


        viewAvailableSeatsButton.setOnAction(e -> handleViewAvailableSeatsButton());
        cancelBookingButton.setOnAction(e -> handleCancelBookingButton());

//        // Add layout containers to the main container and display the application window
//        vBox.getChildren().addAll(hBox1, gridPane);

// Add the back button to the UI
        MainUI mainUI = new MainUI();
        Button backButton = mainUI.createBackButton(primaryStage);
        rootPane.getChildren().add(backButton); // Add the backButton to the rootPane


        primaryStage.setScene(scene);
        primaryStage.setTitle("Passenger Console");
        primaryStage.show();
        displayAvailableSeats();
    }

    // Handle click event for the "View Available Seats" button
    // Handle click event for the "View Available Seats" button
    public void handleViewAvailableSeatsButton() {
        String flightNumber = flightNumberField.getText();
        Flight flight = getFlightByFlightNumber(flightNumber);
        if (flight != null) {
            List<com.airline.models.Seat> availableSeats = flight.getAvailableSeats();
            availableSeats.forEach(seat -> System.out.println("Available seat: " + seat.getSeatNumber())); // Changed to getSeatNumber()
        } else {
            System.out.println("No flight found with the provided flight number.");
        }
    }


    public PassengerUI(FlightManagement flightManagement) {
        this.flightManagement = flightManagement;
//
//        // Initialize and set up the UI components, and set the 'root' variable
//        // For example, if you are using a BorderPane as the main layout:
//        rootPane = new VBox();
    }


    public Flight getFlightByFlightNumber(String flightNumber) {
        // Retrieve the flights list from the FlightManagement class
        List<Flight> flights = flightManagement.getFlights();

        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight;
            }
        }
        return null;
    }




    public PassengerUI() {
//        // Initialize and set up the UI components, and set the 'root' variable
//        // For example, if you are using a BorderPane as the main layout:
//        rootPane = new VBox();
    }
    public Scene getScene() {
        // Return a new Scene object based on the rootPane
        return new Scene(rootPane, 1024, 1000); // You can adjust the size of the scene as needed
    }
    // Add a getRoot() method to return the root Parent object
//    public Parent getRoot() {
//        return root;
//    }
    // Handle click event for the "Reserve Seat" button
    public void handleReserveButton(Stage primaryStage) {
        // Get input values from UI elements
        String name = nameField.getText();
        String flightNumber = flightNumberField.getText();
        String seatNumber = seatNumberField.getText();

        // Update Passenger object and create new Seat, Airplane, and Flight objects
        passenger.setName(name);

        // Example: Set the seat class to ECONOMY for all seats
        SeatClass seatClass = SeatClass.ECONOMY; // Or another logic to determine the seat class
        Seat seat = new Seat(seatNumber, seatClass);
//        Seat seat= new Seat(seatNumber);
        Airplane airplane = new Airplane("Boeing 747", 366, 10000, "ABC123");
        String departureDate = "2023-06-01"; // Add departure date
        String departureTime = "10:00";
        String arrivalDate = "2023-06-01"; // Add arrival date
        String arrivalTime = "13:00";
        int duration = 3; // Add duration
        Flight flight = new Flight(flightNumber, "Origin Airport", "Destination Airport", departureDate, departureTime, arrivalDate, 500.0, 200, duration, airplane, FlightStatus.ONTIME);

        // Uncomment the following lines to create a reservation and add it to the ReservationManager
        // Reservation reservation = new Reservation(seat, passenger, flight);
        // ReservationManager.getInstance().addReservation(reservation);
        // Create a new instance of PassengerUI and set the primary stage's scene
        PassengerUI passengerUI = new PassengerUI();
        passengerUI.switchToPassengerUI(primaryStage);
        primaryStage.show();
        displayAvailableSeats();
    }


    public void switchToPassengerUI(Stage primaryStage) {
        primaryStage.setScene(getScene());
        primaryStage.setTitle("Passenger Console");
        primaryStage.show();
    }


    // Handle click event for the "View Booking" button
    public void handleViewBookingButton() {
        String name = nameField.getText();

        // Uncomment the following line to search for reservations by passenger name and print them
        // ReservationManager.getInstance().searchReservationsByPassengerName(name).forEach(System.out::println);
    }
    // Display available seats
    private void displayAvailableSeats() {
        List<Seat> availableSeats = selectedFlight.getAvailableSeats();
        for (Seat seat : availableSeats) {
            // Display seat information
            // You can modify this line to display the seat information in the desired format
            System.out.println("Seat: " + seat.getSeatNumber() + " | Class: " + seat.getSeatClass());
        }
    }

    // Handle click event for the "Cancel Booking" button
    public void handleCancelBookingButton() {
        String name = nameField.getText();

        // Uncomment the following line to cancel reservations by passenger name
        // ReservationManager.getInstance().cancelReservationByPassengerName(name);
    }

    public PassengerUI(Flight selectedFlight) {
        this.selectedFlight = selectedFlight;
    }


    public Parent getRoot2() {
        return borderPane; // Return the instance variable borderPane as the root of the UI
    }

}

