package com.airline.ui;
import com.airline.models.Reservation;
import com.airline.ui.ReservationUI;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import com.airline.models.Payment;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaymentUI extends Application {
    // Declare UI elements and Payment object
    private Reservation reservation;
    private Stage primaryStage;

    public PaymentUI(Reservation reservation) {

        this.reservation = reservation;
        paymentScene = new Scene(rootLayout, 600, 450);

    }
    BorderPane rootLayout = new BorderPane(); // Initialize the root layout container


    private Payment payment;
    private Scene paymentScene; // Declare a Scene instance variable at the class level
    private TextField paymentProcessorField;
    private ComboBox<String> paymentMethodBox;
    private TextField cardNumberField;
    private TextField cardHolderNameField;
    private TextField cardExpiryField;
    private TextField cardCVCField;
    private Label paymentLabel;
    private Button makePaymentButton;

    private BorderPane borderPane; // declare borderPane as an instance variable



    @Override
    public void start(Stage primaryStage) {
        // Initialize UI layout elements
        BorderPane borderPane = new BorderPane();
        VBox vBox = new VBox();
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        GridPane gridPane = new GridPane();

        // Initialize and set UI labels
        Label headerLabel = new Label("Payment Console");
        Label paymentProcessorLabel = new Label("Payment Processor:");
        Label paymentMethodLabel = new Label("Payment Method:");
        Label cardNumberLabel = new Label("Card Number:");
        Label cardHolderNameLabel = new Label("Card Holder Name:");
        Label cardExpiryLabel = new Label("Card Expiry:");
        Label cardCVCLabel = new Label("Card CVC:");

        // Initialize and set UI input fields and buttons
        paymentProcessorField = new TextField();
        paymentMethodBox = new ComboBox<>();
        paymentMethodBox.getItems().addAll("Credit Card", "Debit Card");
        paymentMethodBox.getSelectionModel().selectFirst();
        cardNumberField = new TextField();
        cardHolderNameField = new TextField();
        cardExpiryField = new TextField();
        cardCVCField = new TextField();
        makePaymentButton = new Button("Make Payment");
        paymentLabel = new Label();

        // Add UI elements to layout containers
        hBox1.getChildren().add(headerLabel);
        hBox2.getChildren().addAll(paymentProcessorLabel, paymentProcessorField, paymentMethodLabel, paymentMethodBox, cardNumberLabel, cardNumberField, cardHolderNameLabel, cardHolderNameField, cardExpiryLabel, cardExpiryField, cardCVCLabel, cardCVCField);
        vBox.getChildren().addAll(hBox1, hBox2, gridPane, makePaymentButton, paymentLabel);

        // Set layout margins and alignment
        VBox.setMargin(hBox2, new Insets(10, 0, 10, 0));
        VBox.setMargin(gridPane, new Insets(10, 0, 10, 0));
        hBox1.setAlignment(Pos.CENTER);
        hBox2.setAlignment(Pos.CENTER);

        // Set button click event handler
        makePaymentButton.setOnAction(e -> handleMakePaymentButton());

        // Set up and show the main application window
        Scene scene = new Scene(borderPane, 1100, 200);
        scene.getStylesheets().add(getClass().getResource("paymentUIStyles.css").toExternalForm());
        borderPane.setCenter(vBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Payment Console");
        primaryStage.show();

//        PaymentUI paymentUI = new PaymentUI(reservation);
//        paymentUI.show();
//        paymentUI.waitUntilClosed();

        MainUI mainUI = new MainUI();
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            try {
                new ReservationUI().start(primaryStage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        vBox.getChildren().add(backButton); // Add the backButton to the vBox
    }

    public void waitUntilClosed() {
        while (primaryStage.isShowing()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    // Handle click event for the "Make Payment" button
    public void handleMakePaymentButton() {
        // Get input values from UI elements
        String paymentProcessor = paymentProcessorField.getText();
        String paymentMethod = paymentMethodBox.getValue();
        String cardNumber = cardNumberField.getText();
        String cardHolderName = cardHolderNameField.getText();
        String cardExpiry = cardExpiryField.getText();
        String cardCVC = cardCVCField.getText();

        // Create a new Payment object using the input values
        payment = new Payment(paymentProcessor, paymentMethod, cardNumber, cardHolderName, cardExpiry, cardCVC);

        // Call the payment method and pass the paymentLabel to display the result
        payment.payment(paymentLabel);

        // Set the paymentLabel text to indicate successful payment
        paymentLabel.setText("Payment successful!");

        // Show the confirmation alert with reservation information and payment success
        Alert confirmationAlert = new Alert(AlertType.INFORMATION);
        confirmationAlert.setTitle("Payment Confirmation");
        confirmationAlert.setHeaderText("Payment Successful!");
        confirmationAlert.setContentText("Reservation Information:\n" +
                "Passenger Name: " + reservation.getPassengerName() + "\n" +
                "Passenger Email: " + reservation.getPassengerEmail() + "\n" +
                "Flight Number: " + reservation.getFlightNumber() + "\n" +
                "Departure Airport: " + reservation.getDepartureAirport() + "\n" +
                "Arrival Airport: " + reservation.getArrivalAirport() + "\n" +
                "Departure Date: " + reservation.getDepartureDate() + "\n" +
                "Departure Time: " + reservation.getDepartureTime());

        confirmationAlert.showAndWait();
    }


    public Scene getScene() {
        return paymentScene;
    }
    public Parent getRoot3() {
        return borderPane; // Return the instance variable borderPane as the root of the UI
    }

}
