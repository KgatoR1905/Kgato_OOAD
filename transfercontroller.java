package com.oakridge.financial;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

public class TransferController {

    @FXML
    private TextField fromAccountField;

    @FXML
    private TextField toAccountField;

    @FXML
    private TextField amountField;

    @FXML
    public void initialize() {
        if (BankingSystem.CUSTOMER_SERVICE.isLoggedIn()) {
            Customer customer = BankingSystem.CUSTOMER_SERVICE.getCurrentCustomer();
            if (!customer.getAccountNumbers().isEmpty()) {
                fromAccountField.setText(String.valueOf(customer.getAccountNumbers().get(0)));
            }
        }
    }

    @FXML
    private void handleTransferAction(ActionEvent event) {
        try {
            int fromAcc = Integer.parseInt(fromAccountField.getText());
            int toAcc = Integer.parseInt(toAccountField.getText());
            double amount = Double.parseDouble(amountField.getText());

            if (amount <= 0) {
                showAlert(AlertType.ERROR, "Invalid Amount", "Transfer amount must be greater than P0.00.");
                return;
            }

            BankingSystem.BANK.transfer(fromAcc, toAcc, amount);

            showAlert(AlertType.INFORMATION, "Success", String.format("P %.2f transferred from %d to %d successfully.", amount, fromAcc, toAcc));
            
            amountField.clear();

        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Input Error", "All fields must be valid numerical inputs.");
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