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

    @FXML
    public void initialize() {
        if (BankingSystem.CUSTOMER_SERVICE != null && BankingSystem.CUSTOMER_SERVICE.isLoggedIn()) {
            String name = BankingSystem.CUSTOMER_SERVICE.getCurrentCustomer().getFullName();
            welcomeLabel.setText("Welcome, " + name + "!");
        }
        primaryBalanceLabel.setText(String.format("P %.2f", 0.00));
    }

    @FXML
    private void handleDepositAction(ActionEvent event) {
        loadNewScene(event, "deposit_screen.fxml", "Oakridge Financial - Deposit");
    }

    @FXML
    private void handleWithdrawAction(ActionEvent event) {
        loadNewScene(event, "withdrawal_screen.fxml", "Oakridge Financial - Withdraw");
    }

    @FXML
    private void handleTransferAction(ActionEvent event) {
        loadNewScene(event, "transfer_screen.fxml", "Oakridge Financial - Transfer");
    }

    @FXML
    private void handleHistoryAction(ActionEvent event) {
        loadNewScene(event, "transaction_history_screen.fxml", "Oakridge Financial - Transaction History");
    }

    @FXML
    private void handleLogoutAction(ActionEvent event) {
        if (BankingSystem.CUSTOMER_SERVICE != null) BankingSystem.CUSTOMER_SERVICE.logout();
        loadNewScene(event, "main_menu.fxml", "Oakridge Financial - Main Menu");
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
