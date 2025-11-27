import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class MainMenuController {

    @FXML
    private Button loginButton;

    @FXML
    private Button createAccountButton;
    
    @FXML
    private Button dashboardButton;

    @FXML
    private Button depositButton;

    @FXML
    private Button withdrawButton;

    @FXML
    private Button transferButton;

    @FXML
    private Button historyButton;

    @FXML
    public void initialize() {
        System.out.println("Main Menu Controller initialized.");
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        System.out.println("Login button clicked! Loading Login Screen.");
        loadNewScene(event, "LogIn.fxml", "Oakridge Financial - User Login");
    }

    @FXML
    private void handleCreateAccountButtonAction(ActionEvent event) {
        System.out.println("Create Account button clicked! Loading Registration Screen.");
        loadNewScene(event, "new_account_screen.fxml", "Oakridge Financial - Register New Account");
    }

    @FXML
    private void handleDashboardAction(ActionEvent event) {
        System.out.println("Dashboard button clicked! Loading Dashboard.");
        loadNewScene(event, "dashboard.fxml", "Oakridge Financial - Dashboard");
    }

    @FXML
    private void handleDepositAction(ActionEvent event) {
        System.out.println("Deposit button clicked! Loading Deposit Screen.");
        loadNewScene(event, "deposit_screen.fxml", "Oakridge Financial - Deposit");
    }

    @FXML
    private void handleWithdrawAction(ActionEvent event) {
        System.out.println("Withdraw button clicked! Loading Withdrawal Screen.");
        loadNewScene(event, "withdrawal_screen.fxml", "Oakridge Financial - Withdraw");
    }

    @FXML
    private void handleTransferAction(ActionEvent event) {
        System.out.println("Transfer button clicked! Loading Transfer Screen.");
        loadNewScene(event, "transfer_screen.fxml", "Oakridge Financial - Transfer");
    }

    @FXML
    private void handleHistoryAction(ActionEvent event) {
        System.out.println("History button clicked! Loading Transaction History.");
        loadNewScene(event, "transaction_history_screen.fxml", "Oakridge Financial - Transaction History");
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
