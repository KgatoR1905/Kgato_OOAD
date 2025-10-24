package com.oakridge.financial;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

public class DepositController {

    @FXML
    private TextField accountNumberField;

    @FXML
    private TextField amountField;

    @FXML
    public void initialize() {
        if (BankingSystem.CUSTOMER_SERVICE.isLoggedIn()) {
            Customer customer = BankingSystem.CUSTOMER_SERVICE.getCurrentCustomer();
            if (!customer.getAccountNumbers().isEmpty()) {
                accountNumberField.setText(String.valueOf(customer.getAccountNumbers().get(0)));
            }
        }
    }

    @FXML
    private void handleDepositAction(ActionEvent event) {
        try {
            int accountNumber = Integer.parseInt(accountNumberField.getText());
            double amount = Double.parseDouble(amountField.getText());

            if (amount <= 0) {
                showAlert(AlertType.ERROR, "Invalid Amount", "Deposit amount must be greater than P0.00.");
                return;
            }

            BankingSystem.BANK.deposit(accountNumber, amount);

            showAlert(AlertType.INFORMATION, "Success", String.format("P %.2f deposited successfully into account %d.", amount, accountNumber));
            
            // Clear the amount field after a successful deposit
            amountField.clear();

        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Input Error", "Account Number and Amount must be valid numerical inputs.");
        } catch (IllegalArgumentException e) {
            showAlert(AlertType.ERROR, "Transaction Failed", e.getMessage());
        }
    }

    @FXML
    private void handleBackAction(ActionEvent event) {
        closeWindowAndLoadDashboard(event);
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void closeWindowAndLoadDashboard(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
        
    }
}