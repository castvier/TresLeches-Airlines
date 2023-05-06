package com.airline.ui;
import com.airline.models.Reservation;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
//        Scene mainScene = new Scene(root, 400, 600);

        // Create a dummy reservation object to pass to PaymentUI
        Reservation dummyReservation = new Reservation("", "", "", "", "", "", "");

        // Pass the dummyReservation object when creating PaymentUI
        Button paymentButton = createButton("Payment UI", primaryStage, new PaymentUI(dummyReservation));


        Button reservationButton = createButton("Reservation UI", primaryStage, new ReservationUI());
        Button passengerButton = createButton("Passenger UI", primaryStage, new PassengerUI());
//        Button paymentButton = createButton("Payment UI", primaryStage, new PaymentUI());
        Button airplaneButton = createButton("Airplane UI", primaryStage, new AirplaneUI());
        Button airportButton = createButton("Airport UI", primaryStage, new AirportUI());
        Button baggageButton = createButton("Baggage UI", primaryStage, new BaggageUI());
        Button flightManagementButton = createButton("Flight Management UI", primaryStage, new FlightManagementUI());
        Button flightButton = createButton("Flight UI", primaryStage, new FlightUI());
        Button managerButton = createButton("Manager UI", primaryStage, new ManagerUI());
//        Button reservationButton = createButton("Reservation UI", primaryStage, new ReservationUI());
//        gridPane.add(reservationButton, 0, 0);


        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(reservationButton, 0, 0);
        gridPane.add(passengerButton, 1, 0);
        gridPane.add(paymentButton, 0, 1);
        gridPane.add(airplaneButton, 1, 1);
        gridPane.add(airportButton, 0, 2);
        gridPane.add(baggageButton, 1, 2);
        gridPane.add(flightManagementButton, 0, 3);
        gridPane.add(flightButton, 1, 3);
        gridPane.add(managerButton, 0, 4);

        root.getChildren().add(gridPane);


        primaryStage.setScene(new Scene(root, 400, 600)); // Add this line
        primaryStage.setTitle("Main UI");
        primaryStage.show();
    }

    private Button createButton(String buttonText, Stage primaryStage, Application uiInstance) {
        Button button = new Button(buttonText);
        button.setMinSize(150, 50);
        button.setOnAction(event -> {
            try {
                uiInstance.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return button;
    }


    public Button createBackButton(Stage primaryStage) {
        Button backButton = new Button("Back");
        backButton.setMinSize(100, 30);
        backButton.setOnAction(event -> {
            try {
                start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return backButton;
    }
}
