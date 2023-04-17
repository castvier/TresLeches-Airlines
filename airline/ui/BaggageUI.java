package com.airline.ui;

import com.airline.models.Baggage;

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

public class BaggageUI extends Application {
    private Baggage baggage;
    private TextField weightField;
    private TextField sizeField;
    private HBox baggageHBox;
    private Label baggageLabel;
    private Button checkInButton;
    private Button modifyButton;
    private Button removeButton;

    private BorderPane borderPane; // declare borderPane as an instance variable
    
    @Override
    public void start(Stage primaryStage) {
        baggage = new Baggage();
        BorderPane borderPane = new BorderPane();
        VBox vBox = new VBox();
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        HBox hBox3 = new HBox();
        GridPane gridPane = new GridPane();
        Label headerLabel = new Label("Baggage Console");
        Label weightLabel = new Label("Weight:");
        Label sizeLabel = new Label("Size:");
        weightField = new TextField();
        sizeField = new TextField();
        checkInButton = new Button("Check-in Baggage");
        modifyButton = new Button("Modify Baggage");
        removeButton = new Button("Remove Baggage");
        baggageLabel = new Label();
        baggageHBox = new HBox();
        baggageHBox.getChildren().addAll(baggageLabel);
        hBox1.getChildren().add(headerLabel);
        hBox2.getChildren().addAll(weightLabel, weightField, sizeLabel, sizeField, checkInButton);
        hBox3.getChildren().addAll(modifyButton, removeButton);
        vBox.getChildren().addAll(hBox1, hBox2, hBox3, gridPane, baggageHBox);
        gridPane.add(baggageLabel, 0, 0);
        VBox.setMargin(hBox2, new Insets(10, 0, 10, 0));
        VBox.setMargin(hBox3, new Insets(10, 0, 10, 0));
        VBox.setMargin(gridPane, new Insets(10, 0, 10, 0));
        gridPane.setAlignment(Pos.CENTER);
        hBox1.setAlignment(Pos.CENTER);
        hBox2.setAlignment(Pos.CENTER);
        hBox3.setAlignment(Pos.CENTER);
        checkInButton.setOnAction(e -> handleCheckInButton());
        modifyButton.setOnAction(e -> handleModifyButton());
        removeButton.setOnAction(e -> handleRemoveButton());
        Scene scene = new Scene(borderPane, 600, 450);
        borderPane.setCenter(vBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Baggage Console");
        primaryStage.show();
    }

    // Handle check-in button event
    public void handleCheckInButton() {
        String baggageWeight = weightField.getText();
        String baggageSize = sizeField.getText();
        baggage.checkIn(baggageWeight, baggageSize);
        baggageLabel.setText(baggage.viewBaggage());
    }
    // Handle modify button event
    public void handleModifyButton() {
        String baggageWeight = weightField.getText();
        String baggageSize = sizeField.getText();
        baggage.modify(baggageWeight, baggageSize);
        baggageLabel.setText(baggage.viewBaggage());
    }
    // Handle remove button event
    public void handleRemoveButton() {
        baggage.remove();
        baggageLabel.setText(baggage.viewBaggage());
    }
    // Return the instance variable borderPane as the root of the UI
    public Parent getRoot7() {
        return borderPane; // Return the instance variable borderPane as the root of the UI
    }
}