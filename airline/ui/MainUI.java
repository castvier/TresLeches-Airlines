package com.airline.ui;

import com.airline.models.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;


/**
 * This class represents the main user interface of the airline application.
 * It displays buttons that allow the user to navigate to different UIs.
 */
public class MainUI extends Application {

    /**
     * The main method that launches the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The method that sets up and displays the user interface.
     *
     * @param primaryStage the primary stage of the application
     */
    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        // Create dummy flight and passenger for testing purposes
        Flight dummyFlight = new Flight("Dummy Flight", "Dummy Origin", "Dummy Destination", "01/01/2023", "00:00", "00:00", 100.0, 100, 100, new Airplane("", 0, 0, ""), FlightStatus.ONTIME);
        Passenger passenger = new Passenger("Dummy Passenger", "dummy@gmail.com", "1234567890");

        // Add logo at the top of the UI
        ImageView logoImageView = new ImageView(new Image(getClass().getResource("tresLecheslogo.png").toExternalForm()));
        logoImageView.setFitWidth(200);
        logoImageView.setFitHeight(100);
        root.getChildren().add(logoImageView);

        // Create a dummy reservation for testing purposes
        Reservation dummyReservation = new Reservation(dummyFlight.getFlightNumber(), dummyFlight.getOrigin(), dummyFlight.getDestination(), dummyFlight.getDateAsString(), dummyFlight.getDepartureTime(), dummyFlight.getArrivalTime(), passenger.getName());

        // Create buttons to navigate to different UIs
        Button reservationButton = createButton("Reservation UI", primaryStage, new ReservationUI());
        Button flightButton = createButton("Flight UI", primaryStage, new FlightUI());
        Button managerButton = createButton("Manager UI", primaryStage, new ManagerUI(), event -> showManagerLogin(primaryStage, new ManagerUI()));


        // Create a grid pane to hold the buttons
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Set column constraints
        for (int i = 0; i < 2; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(50);
            columnConstraints.setHgrow(Priority.ALWAYS);
            gridPane.getColumnConstraints().add(columnConstraints);
        }

        // Set row constraints
        for (int i = 0; i < 4; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(25);
            rowConstraints.setVgrow(Priority.ALWAYS);
            gridPane.getRowConstraints().add(rowConstraints);
        }

        // Add buttons to the grid pane
        gridPane.add(reservationButton, 0, 0);
        gridPane.add(flightButton, 0, 3);
        gridPane.add(managerButton, 1, 3);

        // Set GridPane constraints
        GridPane.setHgrow(gridPane, Priority.ALWAYS);
        GridPane.setVgrow(gridPane, Priority.ALWAYS);

        root.getChildren().add(gridPane);

        // Set up and display the scene
        Scene scene = new Scene(root, 1024, 1000);
        scene.getStylesheets().add(getClass().getResource("mainUIStyles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Main UI");
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    /**
     * Sets the position constraints of a given button in an AnchorPane.
     *
     * @param button the button to set constraints for
     * @param xRatio the x-coordinate ratio of the button's position in the pane (0.0 to 1.0)
     * @param yRatio the y-coordinate ratio of the button's position in the pane (0.0 to 1.0)
     */
    private void setButtonConstraints(Button button, double xRatio, double yRatio) {
        AnchorPane.setTopAnchor(button, yRatio * 100);
        AnchorPane.setLeftAnchor(button, xRatio * 100);
        AnchorPane.setRightAnchor(button, (1 - xRatio) * 100);
        AnchorPane.setBottomAnchor(button, (1 - yRatio) * 100);
    }

    /**
     * Creates a button with the given text and sets its size and action.
     *
     * @param buttonText   The text to display on the button.
     * @param primaryStage The main stage of the application.
     * @param uiInstance   The instance of the UI to launch when the button is clicked.
     * @return The created button.
     */
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

    /**
     * Creates a back button and sets its size and action to return to the main UI.
     *
     * @param primaryStage The main stage of the application.
     * @return The created back button.
     */
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
    private Button createButton(String buttonText, Stage primaryStage, Application uiInstance, EventHandler<ActionEvent> handler) {
        Button button = new Button(buttonText);
        button.setMinSize(150, 50);
        if (handler != null) {
            button.setOnAction(handler);
        } else {
            button.setOnAction(event -> {
                try {
                    uiInstance.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        return button;
    }

    private void showManagerLogin(Stage primaryStage, Application uiInstance) {
        TextInputDialog usernameDialog = new TextInputDialog();
        usernameDialog.setTitle("Manager Login");
        usernameDialog.setHeaderText("Please enter your username:");
        Optional<String> usernameResult = usernameDialog.showAndWait();

        TextInputDialog passwordDialog = new TextInputDialog();
        passwordDialog.setTitle("Manager Login");
        passwordDialog.setHeaderText("Please enter your password:");
        Optional<String> passwordResult = passwordDialog.showAndWait();

        String correctUsername = "manager"; // Replace with the correct manager username
        String correctPassword = "password"; // Replace with the correct manager password

        if (usernameResult.isPresent() && passwordResult.isPresent()) {
            String username = usernameResult.get();
            String password = passwordResult.get();

            if (username.equals(correctUsername) && password.equals(correctPassword)) {
                try {
                    uiInstance.start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // Show an alert if the username or password is incorrect
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText("Incorrect username or password");
                alert.setContentText("Please try again.");
                alert.showAndWait();
            }
        }
    }

}