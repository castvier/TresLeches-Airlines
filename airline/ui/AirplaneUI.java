package com.airline.ui;

import com.airline.models.Airplane;
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

public class AirplaneUI extends Application {
    private Airplane airplane;
    private TextField modelField;
    private TextField capacityField;
    private TextField rangeField;
    private TextField serialNumberField;
    private Label airplaneLabel;
    private Button createAirplaneButton;

    private BorderPane borderPane; // declare borderPane as an instance variable

    @Override
    public void start(Stage primaryStage) {
        // Initialize layout containers
        BorderPane borderPane = new BorderPane();
        VBox vBox = new VBox();
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        GridPane gridPane = new GridPane();

        // Initialize UI controls
        Label headerLabel = new Label("Airplane Console");
        Label modelLabel = new Label("Model:");
        Label capacityLabel = new Label("Capacity:");
        Label rangeLabel = new Label("Range:");
        Label serialNumberLabel = new Label("Serial Number:");
        modelField = new TextField();
        capacityField = new TextField();
        rangeField = new TextField();
        serialNumberField = new TextField();
        createAirplaneButton = new Button("Create Airplane");
        airplaneLabel = new Label();

        // Add UI controls to layout containers
        hBox1.getChildren().add(headerLabel);
        hBox2.getChildren().addAll(modelLabel, modelField, capacityLabel, capacityField, rangeLabel, rangeField, serialNumberLabel, serialNumberField, createAirplaneButton);
        vBox.getChildren().addAll(hBox1, hBox2, gridPane, airplaneLabel);

        // Set layout properties
        VBox.setMargin(hBox2, new Insets(10, 0, 10, 0));
        VBox.setMargin(gridPane, new Insets(10, 0, 10, 0));
        hBox1.setAlignment(Pos.CENTER);
        hBox2.setAlignment(Pos.CENTER);

        // Set event handler for createAirplaneButton
        createAirplaneButton.setOnAction(e -> handleCreateAirplaneButton());

        // Set up the scene and stage
        Scene scene = new Scene(borderPane, 800, 450);
        borderPane.setCenter(vBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Airplane Console");
        primaryStage.show();
    }

    public void handleCreateAirplaneButton() {
        // Get input values from text fields
        String model = modelField.getText();
        int capacity = Integer.parseInt(capacityField.getText());
        int range = Integer.parseInt(rangeField.getText());
        String serialNumber = serialNumberField.getText();

        // Create a new Airplane instance
        airplane = new Airplane(model, capacity, range, serialNumber);

        // Update airplaneLabel with the new Airplane's details
        airplaneLabel.setText("Airplane Created: Model - " + airplane.getModel() + ", Capacity - " + airplane.getCapacity() + ", Range - " + airplane.getRange() + ", Serial Number - " + airplane.getSerialNumber());
    }

    public Parent getRoot9() {
        return borderPane; // Return the instance variable borderPane as the root of the UI
    }
}

