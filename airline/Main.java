package com.airline; // Replace this with your actual package name

import com.airline.ui.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
public class Main extends Application {

    // The main method of the program that launches the application.
    public static void main(String[] args) {
        // Launch the individual user interface.
//        Application.launch(ReservationUI.class, args);
//        Application.launch(PassengerUI.class, args);
//        Application.launch(PaymentUI.class, args);
        Application.launch(AirplaneUI.class, args);
//        Application.launch(AirportUI.class, args);
//        Application.launch(BaggageUI.class, args);
//        Application.launch(FlightManagementUI.class, args);
//        Application.launch(FlightUI.class, args);
//        Application.launch(ManagerUI.class, args);
    }

    // The starting point of the application.
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create a new scene with a border pane layout of size 800x450.
        Scene scene = new Scene(new BorderPane(), 800, 450);

        // Set the scene for the primary stage.
        primaryStage.setScene(scene);

        // Set the title of the primary stage to "Tres Leches Airlines".
        primaryStage.setTitle("Tres Leches Airlines");

        // Create a new instance of the ReservationUI class.
        ReservationUI reservationUI = new ReservationUI();
        PaymentUI paymentUI = new PaymentUI();
        PassengerUI passengerUI = new PassengerUI();
        AirplaneUI airplaneUI = new AirplaneUI();
        AirportUI airportUI = new AirportUI();
        BaggageUI baggageUI = new BaggageUI();
        FlightManagementUI flightManagementUI = new FlightManagementUI();
        FlightUI flightUI = new FlightUI();
        ManagerUI managerUI = new ManagerUI();

        // Set the root node of the scene to the root node of the reservation user interface.
        scene.setRoot(reservationUI.getRoot());
        scene.setRoot(passengerUI.getRoot2());
        scene.setRoot(paymentUI.getRoot3());
        scene.setRoot(managerUI.getRoot4());
        scene.setRoot(flightUI.getRoot5());
        scene.setRoot(flightManagementUI.getRoot6());
        scene.setRoot(baggageUI.getRoot7());
        scene.setRoot(airportUI.getRoot8());
        scene.setRoot(airplaneUI.getRoot9());
        // Show the primary stage.
        primaryStage.show();
    }
}
