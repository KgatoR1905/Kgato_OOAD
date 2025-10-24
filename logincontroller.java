package com.oakridge.financial;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Button loginButton;

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
            closeWindow(event);
        } catch (IllegalArgumentException e) {
            System.err.println("Login Failed: " + e.getMessage());
        }
    }
    
    @FXML
    private void handleBackAction(ActionEvent event) {
        System.out.println("Returning to Main Menu.");
        closeWindow(event);
        MainMenuView.showMenu();
    }

    private void closeWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}