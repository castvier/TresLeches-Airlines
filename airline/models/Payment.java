package com.airline.models;
import javafx.scene.control.Label; // Import the Label class from the JavaFX library

public class Payment {
  private String paymentProcessor; // The payment processor for the payment
  private String paymentMethod; // The payment method (e.g. credit card, PayPal, etc.)
  private String cardNumber; // The credit card number for the payment
  private String cardHolderName; // The name on the credit card for the payment
  private String cardExpiry; // The expiry date of the credit card for the payment
  private String cardCVC; // The CVC number on the back of the credit card for the payment

  public Payment(String paymentProcessor, String paymentMethod, String cardNumber,
                 String cardHolderName, String cardExpiry, String cardCVC) {
    this.paymentProcessor = ""; // Initialize the payment processor
    this.paymentMethod = ""; // Initialize the payment method
    this.cardNumber = ""; // Initialize the credit card number
    this.cardHolderName = ""; // Initialize the name on the credit card
    this.cardExpiry = ""; // Initialize the credit card expiry date
    this.cardCVC = ""; // Initialize the CVC number on the credit card
  }

  public String getPaymentProcessor() {
    return paymentProcessor; // Get the payment processor for the payment
  }

  public String getPaymentMethod() {
    return paymentMethod; // Get the payment method for the payment
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod; // Set a new payment method for the payment
  }

  public String getCardNumber() {
    return cardNumber; // Get the credit card number for the payment
  }

  public String getCardHolderName() {
    return cardHolderName; // Get the name on the credit card for the payment
  }

  public String getCardExpiry() {
    return cardExpiry; // Get the credit card expiry date for the payment
  }

  public String getCardCVC() {
    return cardCVC; // Get the CVC number on the credit card for the payment
  }

  public void payment(Label paymentLabel) {
    // code to communicate with payment gateway API
    System.out.println("Payment successful!"); // Print a success message to the console
    paymentLabel.setText("Payment successful!"); // Set the text of a Label object to indicate payment success
  }


}
