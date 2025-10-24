package com.oakridge.financial;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class MainController {

    @FXML
    private Button loginButton;

    @FXML
    private Button createAccountButton;

    @FXML
    public void initialize() {
        System.out.println("Main Menu Controller initialized.");
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        System.out.println("Login button clicked! Loading Login Screen.");
        
        try {
            Node source = (Node) event.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScreen.fxml"));
            Parent loginRoot = loader.load();
            
            currentStage.setScene(new Scene(loginRoot));
            currentStage.setTitle("Oakridge Financial - User Login");
            currentStage.show();

        } catch (IOException e) {
            System.err.println("Error loading LoginScreen.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCreateAccountButtonAction(ActionEvent event) {
        System.out.println("Create Account button clicked!");
    }
}