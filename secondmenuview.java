package your.app.package; // **IMPORTANT: Update this to your actual package name**

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Boundary class to display the Second Menu FXML.
 */
public class SecondMenuView extends Application {

    private static Stage mainStage;

    /**
     * The standard JavaFX start method.
     * @param stage The primary stage for this application.
     * @throws IOException If the FXML file cannot be loaded.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Set the static mainStage so other methods can access it (optional, but useful)
        mainStage = stage; 

        // 1. Load the FXML file
        Parent root = FXMLLoader.load(getClass().getResource("/path/to/second_menu.fxml")); 
        // **IMPORTANT: Update the path above to the correct location of your FXML file**
        
        // 2. Create the scene
        Scene scene = new Scene(root);

        // 3. Set up the stage (window)
        stage.setTitle("Main Account Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Convenience method to launch the menu, typically called from Bank.java.
     */
    public static void displayMenu() {
        // Call the standard JavaFX launch method
        // Note: JavaFX applications must be launched this way.
        launch(); 
    }
    
    // Optional: Getter for the stage if you need to close/switch scenes later
    public static Stage getMainStage() {
        return mainStage;
    }
}