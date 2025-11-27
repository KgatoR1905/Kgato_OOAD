import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TransferController {

    @FXML private TextField fromAccountField;
    @FXML private TextField toAccountField;
    @FXML private TextField amountField;

    @FXML
    public void handleTransferAction() {
        try {
            String fromAccount = fromAccountField.getText();
            String toAccount = toAccountField.getText();
            double amount = Double.parseDouble(amountField.getText());

            if (amount <= 0) {
                showAlert("Invalid Amount", "Amount must be greater than 0");
                return;
            }

            if (fromAccount.equals(toAccount)) {
                showAlert("Error", "Cannot transfer to the same account");
                return;
            }

            BankingSystem.BANK.transfer(Integer.parseInt(fromAccount), Integer.parseInt(toAccount), amount);
            showAlert("Success", "Transfer successful!");
            fromAccountField.clear();
            toAccountField.clear();
            amountField.clear();
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid account numbers and amount");
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
            Stage stage = (Stage) fromAccountField.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.err.println("Error loading FXML file: " + fxmlFile + ". Check file path and existence.");
            e.printStackTrace();
        }
    }
}
