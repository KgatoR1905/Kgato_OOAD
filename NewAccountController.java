import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class NewAccountController {

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<AccountType> accountTypeComboBox;

    @FXML
    private TextField initialDepositField;

    @FXML
    public void initialize() {
        // populate account type options
        try {
            accountTypeComboBox.getItems().addAll(AccountType.values());
        } catch (Exception e) {
            // ignore if AccountType not available at compile time (defensive)
        }
    }

    @FXML
    private void handleRegisterAction(ActionEvent event) {
        String fullName = fullNameField.getText().trim();
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        AccountType type = accountTypeComboBox.getValue();
        double initialDeposit = 0.0;

        if (fullName.isEmpty() || username.isEmpty() || password.isEmpty() || type == null) {
            showAlert(AlertType.ERROR, "Missing Information", "Please fill in all required fields.");
            return;
        }

        try {
            initialDeposit = Double.parseDouble(initialDepositField.getText());
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Invalid Amount", "Initial deposit must be a valid number.");
            return;
        }

        if (initialDeposit < 1.0) {
            showAlert(AlertType.ERROR, "Invalid Amount", "Initial deposit must be at least P1.00.");
            return;
        }

        // create customer and persist
        Customer c = new Customer(username, password, fullName);
        try {
            BankingSystem.CUSTOMER_SERVICE.registerCustomer(c);
        } catch (IllegalArgumentException ex) {
            showAlert(AlertType.ERROR, "Registration Failed", ex.getMessage());
            return;
        }

        // create bank account and link to customer
        Account acc = BankingSystem.BANK.createAccount(fullName, type, initialDeposit);
        BankingSystem.CUSTOMER_SERVICE.addAccountToCustomer(username, acc.getAccountNumber());

        showAlert(AlertType.INFORMATION, "Success", String.format("Account %d created for %s.", acc.getAccountNumber(), fullName));

        // return to main menu
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

    @FXML
    private void handleBackToLoginAction(ActionEvent event) {
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

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
