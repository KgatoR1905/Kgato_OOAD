import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DepositController {

    @FXML private TextField accountNumberField;
    @FXML private TextField amountField;

    @FXML
    public void handleDepositAction() {
        try {
            String accountNumber = accountNumberField.getText();
            double amount = Double.parseDouble(amountField.getText());

            if (amount <= 0) {
                showAlert("Invalid Amount", "Amount must be greater than 0");
                return;
            }

            BankingSystem.BANK.deposit(Integer.parseInt(accountNumber), amount);
            showAlert("Success", "Deposit successful!");
            accountNumberField.clear();
            amountField.clear();
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid amount");
        } catch (Exception e) {
            showAlert("Error", e.getMessage());
        }
    }

    @FXML
    public void handleBackAction() {
        loadNewScene("dashboard.fxml");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void loadNewScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) accountNumberField.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.println("Error loading FXML file: " + fxmlFile + ". Check file path and existence.");
            e.printStackTrace();
        }
    }
}
