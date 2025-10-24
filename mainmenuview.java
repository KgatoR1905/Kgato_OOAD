package com.oakridge.financial; // Use your actual package name

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuView extends Application {

    // This method is the main entry point for all JavaFX applications.
    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            // 1. Load the FXML file (assuming it's named MainMenu.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            Parent root = loader.load();

            // 2. Set up the Scene
            Scene scene = new Scene(root);

            // 3. Set up the Stage (Window)
            primaryStage.setTitle("Oakridge Financial - Main Menu");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (IOException e) {
            System.err.println("Failed to load MainMenu.fxml: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    // A utility method for other classes to call and launch the GUI
    public static void showMenu() {
        // Launches the JavaFX runtime, which calls the start() method above.
        launch(MainMenuView.class);
    }
}