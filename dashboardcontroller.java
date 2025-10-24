package com.oakridge.financial;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class DashboardController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label primaryBalanceLabel;
    
    private Customer currentCustomer;

    @FXML
    public void initialize() {
        currentCustomer = BankingSystem.CUSTOMER_SERVICE.getCurrentCustomer();
        if (currentCustomer != null && !currentCustomer.getAccountNumbers().isEmpty()) {
            welcomeLabel.setText("Welcome, " + currentCustomer.getFullName() + "!");
            
            int primaryAccNum = currentCustomer.getAccountNumbers().get(0);
            Account primaryAccount = BankingSystem.BANK.findAccountByNumber(primaryAccNum);
            
            if (primaryAccount != null) {
                primaryBalanceLabel.setText(
                    String.format("P %.2f", primaryAccount.getBalance())
                );
            } else {
                primaryBalanceLabel.setText("P 0.00 (Account not found)");
            }
        } else {
            welcomeLabel.setText("Welcome!");
            primaryBalanceLabel.setText("P 0.00");
        }
    }

    @FXML
    private void handleLogoutAction(ActionEvent event) {
        BankingSystem.CUSTOMER_SERVICE.logout();
        
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
        MainMenuView.showMenu(); 
    }

    @FXML
    private void handleDepositAction(ActionEvent event) {
        loadNewScene(event, "DepositScreen.fxml", "Deposit Funds");
    }

    @FXML
    private void handleWithdrawAction(ActionEvent event) {
        loadNewScene(event, "WithdrawalScreen.fxml", "Withdraw Cash");
    }

    @FXML
    private void handleTransferAction(ActionEvent event) {
        loadNewScene(event, "TransferScreen.fxml", "Transfer Money");
    }
    
    @FXML
    private void handleHistoryAction(ActionEvent event) {
        loadNewScene(event, "TransactionHistoryScreen.fxml", "Transaction History");
    }
    
    private void loadNewScene(ActionEvent event, String fxmlPath, String title) {
        try {
            Node source = (Node) event.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            
            currentStage.setScene(new Scene(root));
            currentStage.setTitle("Oakridge Financial - " + title);
            currentStage.show();

        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + fxmlPath + ". Check file path and existence.");
            e.printStackTrace();
        }
    }
}