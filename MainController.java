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
        loadNewScene(event, "LoginScreen.fxml", "Oakridge Financial - User Login");
    }

    @FXML
    private void handleCreateAccountButtonAction(ActionEvent event) {
        System.out.println("Create Account button clicked! Loading Registration Screen.");
        loadNewScene(event, "NewAccountScreen.fxml", "Oakridge Financial - Register New Account");
    }
    
    
    private void loadNewScene(ActionEvent event, String fxmlPath, String title) {
        try {
            
            Node source = (Node) event.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            
           
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            
           
            currentStage.setScene(new Scene(root));
            currentStage.setTitle(title);
            currentStage.show();

        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + fxmlPath + ". Check file path and existence.");
            e.printStackTrace();
        }
    }
}