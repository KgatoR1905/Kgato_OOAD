package com.oakridge.financial;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginController {

    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    public void initialize() {
        System.out.println("Login Controller initialized.");
    }

    @FXML
    private void handleLoginAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            BankingSystem.CUSTOMER_SERVICE.login(username, password);
            System.out.println("Login successful!");
            
            // Load and display the Dashboard
            loadDashboard(event);

        } catch (IllegalArgumentException e) {
            System.err.println("Login Failed: " + e.getMessage());
            showAlert(AlertType.ERROR, "Login Failed", e.getMessage());
        }
    }
    
    @FXML
    private void handleBackAction(ActionEvent event) {
        System.out.println("Returning to Main Menu.");
        closeWindow(event);
        MainMenuView.showMenu();
    }

    private void loadDashboard(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage currentStage = (Stage) source.getScene().getWindow();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardScreen.fxml"));
        Parent root = loader.load();
        
        currentStage.setScene(new Scene(root));
        currentStage.setTitle("Oakridge Financial - Customer Dashboard");
        currentStage.show();
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private void closeWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}