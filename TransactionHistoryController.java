import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class TransactionHistoryController {

    @FXML
    private TableView transactionTable;

    @FXML
    private TableColumn timestampColumn;

    @FXML
    private TableColumn typeColumn;

    @FXML
    private TableColumn amountColumn;

    @FXML
    private TableColumn balanceColumn;

    @FXML
    public void initialize() {
        // populate the table if transaction data is available (left as future enhancement)
    }

    @FXML
    private void handleBackAction(ActionEvent event) {
        loadNewScene(event, "dashboard.fxml", "Oakridge Financial - Dashboard");
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
