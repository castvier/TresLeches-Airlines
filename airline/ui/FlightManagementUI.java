//package com.airline.ui;
//
//import com.airline.models.Airport;
//import com.airline.management.FlightManagement;
//import com.airline.models.Airplane;
//import com.airline.models.Flight; // Add this import statement
//import java.util.List;
//import java.util.List;
//import java.util.ArrayList;
//
//
//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ListView;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class FlightManagementUI extends Application {
//    private FlightManagement flightManagement;
//    private ListView<String> flightListView;
//    private TextField originAirportField;
//    private TextField destinationAirportField;
//    private TextField departureTimeField;
//    private TextField arrivalTimeField;
//
//    private Button addButton;
//    private Button deleteButton;
//    private Button updateButton;
//    private List<Flight> flights;
//
//    private TextField dateField;
//    private BorderPane borderPane;
//    private HBox inputFieldsHBox;
//
//
//    @Override
//    public void start(Stage primaryStage) {
//        Airport airport = new Airport("Sample Airport");
//        flightManagement = new FlightManagement(airport);
//
//        dateField = new TextField();
//        inputFieldsHBox = new HBox();
//        inputFieldsHBox.getChildren().addAll(new Label("Origin Airport:"), originAirportField, new Label("Destination Airport:"), destinationAirportField, new Label("Date:"), dateField, new Label("Departure Time:"), departureTimeField, new Label("Arrival Time:"), arrivalTimeField);
//
//        BorderPane borderPane = new BorderPane();
//        HBox headerHBox = new HBox();
//        HBox buttonsHBox = new HBox();
//        VBox vBox = new VBox(headerHBox, inputFieldsHBox, buttonsHBox, flightListView);
//
//        Label headerLabel = new Label("Flight Management Console");
//        originAirportField = new TextField();
//        destinationAirportField = new TextField();
//        departureTimeField = new TextField();
//        arrivalTimeField = new TextField();
//        flightListView = new ListView<>();
//
//        addButton = new Button("Add Flight");
//        deleteButton = new Button("Delete Flight");
//        updateButton = new Button("Update Flight");
//        Button searchButton = new Button("Search Flights");
//
//        headerHBox.getChildren().add(headerLabel);
//        buttonsHBox.getChildren().addAll(addButton, deleteButton, updateButton, searchButton);
//        searchButton.setOnAction(e -> handleSearchButton());
//
//        borderPane.setCenter(new VBox(inputFieldsHBox, buttonsHBox, flightListView));
//
//        VBox.setMargin(inputFieldsHBox, new Insets(10, 0, 10, 0));
//        VBox.setMargin(buttonsHBox, new Insets(10, 0, 10, 0));
//
//        headerHBox.setAlignment(Pos.CENTER);
//        inputFieldsHBox.setAlignment(Pos.CENTER);
//        buttonsHBox.setAlignment(Pos.CENTER);
//
//        addButton.setOnAction(e -> handleAddButton());
//        deleteButton.setOnAction(e -> handleDeleteButton());
//        updateButton.setOnAction(e -> handleUpdateButton());
//
//        Scene scene = new Scene(borderPane, 800, 450);
//        scene.getStylesheets().add(getClass().getResource("flightManagementUIStyles.css").toExternalForm());
//
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Flight Management Console");
//        primaryStage.show();
//
//        MainUI mainUI = new MainUI();
//        Button backButton = mainUI.createBackButton(primaryStage);
//        vBox.getChildren().add(backButton);
//
//        borderPane.setCenter(vBox);
//    }
//
//
////    // Add this method to your FlightManagement class
////    public List<Flight> searchFlights(String departureTime, String destination) {
////        List<Flight> matchingFlights = new ArrayList<>();
////        for (Flight flight : flights) {
////            if (flight.getDepartureTime().equals(departureTime) && flight.getDestination().equals(destination)) {
////                matchingFlights.add(flight);
////            }
////        }
////        return matchingFlights;
////    }
//
//    // Handle add button event
//    private void handleAddButton() {
//        String originAirport = originAirportField.getText();
//        String destinationAirport = destinationAirportField.getText();
//        String departureTime = departureTimeField.getText();
//        String arrivalTime = arrivalTimeField.getText();
//
//        // Add these lines to provide the missing parameters
//        String flightNumber = "AB123"; // Replace with a suitable flight number
//        Airplane airplane = new Airplane("Boeing 737", 180, 30, "SomeString"); // Replace with the actual values required by your Airplane constructor
//        double ticketPrice = 100.0;
//        int availableSeats = 180;
//
//        Flight flight = new Flight(flightNumber, originAirport, destinationAirport, departureTime, arrivalTime, airplane, ticketPrice, availableSeats);
//        flightManagement.addFlight(flight);
//        flightListView.getItems().add(flight.getDetails());
//    }
//
//    // Handle delete button event
//    private void handleDeleteButton() {
//        int selectedIndex = flightListView.getSelectionModel().getSelectedIndex();
//        if (selectedIndex != -1) {
//            Flight selectedFlight = flights.get(selectedIndex);
//            flightManagement.deleteFlight(selectedFlight);
//            flightListView.getItems().remove(selectedIndex);
//        }
//    }
//
//    // Handle search button event
//    private void handleSearchButton() {
//        String destinationAirport = destinationAirportField.getText();
//        String departureTime = departureTimeField.getText();
//        String date = dateField.getText(); // Add this line to get the date input
//        List<Flight> matchingFlights = flightManagement.searchFlights(destinationAirport, date, departureTime); // Pass the date argument
//        flightListView.getItems().clear();
//        for (Flight flight : matchingFlights) {
//            flightListView.getItems().add(flight.getDetails());
//        }
//    }
//
//
//    public List<Flight> searchFlights(String destination, String date, String departureTime) {
//        List<Flight> matchingFlights = new ArrayList<>();
//        for (Flight flight : flights) {
//            if (flight.getDestination().equalsIgnoreCase(destination) && flight.getDepartureTime().startsWith(date) && flight.getDepartureTime().endsWith(departureTime)) {
//                matchingFlights.add(flight);
//            }
//        }
//        return matchingFlights;
//    }
//
//
//
//    // Handle update button event
//    private void handleUpdateButton() {
//        int selectedIndex = flightListView.getSelectionModel().getSelectedIndex();
//        if (selectedIndex != -1) {
//            Flight oldFlight = flightManagement.getFlights().get(selectedIndex);
//            String flightNumber = oldFlight.getFlightNumber(); // Assuming flight number does not change
//            String originAirport = originAirportField.getText();
//            String destinationAirport = destinationAirportField.getText();
//            String departureTime = departureTimeField.getText();
//            String arrivalTime = arrivalTimeField.getText();
//            Airplane airplane = oldFlight.getAirplane(); // Assuming airplane does not change
//            double ticketPrice = oldFlight.getTicketPrice(); // Assuming ticket price does not change
//            int availableSeats = oldFlight.getAvailableSeats(); // Assuming available seats do not change
//
//            Flight updatedFlight = new Flight(flightNumber, originAirport, destinationAirport, departureTime, arrivalTime, airplane, ticketPrice, availableSeats);
//            flightManagement.updateFlight(oldFlight, updatedFlight);
//            flightListView.getItems().set(selectedIndex, updatedFlight.getDetails());
//        }
//    }
//
//    public Parent getRoot6() {
//        return borderPane; // Return the instance variable borderPane as the root of the UI
//    }
//
//}
//
