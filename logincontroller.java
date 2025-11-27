import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.control.Alert;

public class logincontroller {

	@FXML private TextField usernameField;
	@FXML private PasswordField passwordField;
	@FXML private Button loginButton;
	@FXML private Button backButton;

	@FXML
	public void initialize() {
		System.out.println("logincontroller initialized.");
	}

	@FXML
	private void handleLoginAction(ActionEvent event) {
		String username = usernameField.getText();
		String password = passwordField.getText();
		try {
			BankingSystem.CUSTOMER_SERVICE.login(username, password);
			Node source = (Node) event.getSource();
			Stage currentStage = (Stage) source.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
			Parent root = loader.load();
			currentStage.setScene(new Scene(root));
			currentStage.setTitle("Oakridge Financial - Dashboard");
			currentStage.show();
		} catch (IllegalArgumentException ex) {
			Alert a = new Alert(Alert.AlertType.ERROR);
			a.setTitle("Login Failed");
			a.setHeaderText("Invalid credentials");
			a.setContentText("Username or password is incorrect. Please try again.");
			a.showAndWait();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	@FXML
	private void handleBackAction(ActionEvent event) {
		try {
			Node source = (Node) event.getSource();
			Stage currentStage = (Stage) source.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("main_menu.fxml"));
			Parent root = loader.load();
			currentStage.setScene(new Scene(root));
			currentStage.setTitle("Oakridge Financial - Main Menu");
			currentStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}





