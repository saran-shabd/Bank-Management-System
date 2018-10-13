import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginPage implements Initializable {
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private ChoiceBox<String> bankName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        login(bankName);
    }

    static void login(ChoiceBox<String> bankName) {
        bankName.getItems().add("Bank 1");
        bankName.getItems().add("Bank 2");
        bankName.getItems().add("Bank 3");
        bankName.getItems().add("Bank 4");
        bankName.getSelectionModel().selectFirst();
    }

    @FXML
    public void LogInOnAction() throws Exception {
        if (!validateUserLoginInput(username, password)) {
            ResetOnAction();
            return;
        }

        Pane newPane = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        Main.primaryStage.setScene(new Scene(newPane, 1000, 600));
        Main.primaryStage.show();
    }

    static boolean validateUserLoginInput(TextField username, PasswordField password) {
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            Alert emptyFields = new Alert(Alert.AlertType.ERROR);
            emptyFields.setContentText("Some Fields are missing");
            emptyFields.show();
            return false;
        } else if (!username.getText().equals("username") || !password.getText().equals("password")) {
            Alert invalidInput = new Alert(Alert.AlertType.ERROR);
            invalidInput.setContentText("Invalid Username or Password");
            invalidInput.show();
            return false;
        }

        return true;
    }

    @FXML
    public void ResetOnAction() {
        username.clear();
        password.clear();
        bankName.getSelectionModel().selectFirst();
    }
}