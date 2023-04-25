package com.airline.ui;

import com.airline.models.DatabaseManager;
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
import com.airline.models.Reservation;


public class PaymentUI extends Application {
    // Declare UI elements and Payment object
    private Payment payment;
    private TextField paymentProcessorField;
    private ComboBox<String> paymentMethodBox;
    private TextField cardNumberField;
    private TextField cardHolderNameField;
    private TextField cardExpiryField;
    private TextField cardCVCField;
    private Label paymentLabel;
    private Button makePaymentButton;

    private Button addReservationButton;
    private Button deleteReservationButton;
    private Button updateReservationButton;

    private TextField reservationIdField;
    private TextField reservationDetailsField;

    private BorderPane borderPane;

    @Override
    public void start(Stage primaryStage) {
        // Initialize UI layout elements
        borderPane = new BorderPane();
        VBox vBox = new VBox();
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        GridPane gridPane = new GridPane();

        addReservationButton = new Button("Add Reservation");
        deleteReservationButton = new Button("Delete Reservation");
        updateReservationButton = new Button("Update Reservation");
        reservationIdField = new TextField();
        reservationDetailsField = new TextField();

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
        vBox.getChildren().addAll(addReservationButton, deleteReservationButton, updateReservationButton);
        vBox.getChildren().addAll(new Label("Reservation ID:"), reservationIdField, new Label("Reservation Details:"), reservationDetailsField);
        hBox1.setAlignment(Pos.CENTER);
        hBox2.setAlignment(Pos.CENTER);
        addReservationButton.setOnAction(e -> handleAddReservationButton());
        deleteReservationButton.setOnAction(e -> handleDeleteReservationButton());
        updateReservationButton.setOnAction(e -> handleUpdateReservationButton());

        // Set button click event handler
        makePaymentButton.setOnAction(e -> handleMakePaymentButton());

        // Set up and show the main application window
        Scene scene = new Scene(borderPane, 1100, 200);
        borderPane.setCenter(vBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Payment Console");
        primaryStage.show();
    }

    private void handleAddReservationButton() {
        // Get reservation details from the UI
        String reservationDetails = reservationDetailsField.getText();

        // Split the reservation details string into an array
        String[] details = reservationDetails.split(",");

        // Check if the details array has the correct number of arguments
        if (details.length != 7) {
            paymentLabel.setText("Invalid reservation details format.");
            return;
        }

        // Create a Reservation object with the necessary details
        Reservation newReservation = new Reservation(details[0], details[1], details[2], details[3], details[4], details[5], details[6]);

        // Call the addReservation method in DatabaseManager
        DatabaseManager dbManager = new DatabaseManager();
        dbManager.addReservation(newReservation);
        paymentLabel.setText("Reservation added successfully!");
    }




    // Handle click event for the "Delete Reservation" button
    private void handleDeleteReservationButton() {
        // Get reservation ID from the UI
        int reservationId = Integer.parseInt(reservationIdField.getText());

        // Call the deleteReservation method in DatabaseManager
        DatabaseManager dbManager = new DatabaseManager();
        dbManager.deleteReservation(reservationId);
        paymentLabel.setText("Reservation deleted successfully!");
    }

    // Handle click event for the "Update Reservation" button
    private void handleUpdateReservationButton() {
        // Get reservation ID and updated details from the UI
        int reservationId = Integer.parseInt(reservationIdField.getText());
        String updatedReservationDetails = reservationDetailsField.getText();

        // Split the updated reservation details string into an array
        String[] details = updatedReservationDetails.split(",");

        // Check if the details array has the correct number of arguments
        if (details.length != 7) {
            paymentLabel.setText("Invalid reservation details format.");
            return;
        }

        // Create a Reservation object with the necessary details
        Reservation updatedReservation = new Reservation(details[0], details[1], details[2], details[3], details[4], details[5], details[6]);

        // Call the updateReservation method in DatabaseManager
        DatabaseManager dbManager = new DatabaseManager();
        dbManager.updateReservation(reservationId, updatedReservation);
        paymentLabel.setText("Reservation updated successfully!");
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

        // Add the payment to the database
        DatabaseManager dbManager = new DatabaseManager();
        dbManager.addPayment(payment);

        // Set the paymentLabel text to indicate successful payment
        paymentLabel.setText("Payment successful!");
    }

    public Parent getRoot3() {
        return borderPane; // Return the instance variable borderPane as the root of the UI
    }

    public static void main(String[] args) {
        launch(args);
    }
}
