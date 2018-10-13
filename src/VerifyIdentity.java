import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class VerifyIdentity implements Initializable {
    protected static boolean result = false;
    protected static Stage tempStage;

    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private ChoiceBox<String> bankName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoginPage.login(bankName);
    }

    @FXML
    public void LogInOnAction() {
        if (!LoginPage.validateUserLoginInput(username, password)) {
            ResetOnAction();
            return;
        }

        result = true;
        tempStage.close();
    }

    @FXML
    public void ResetOnAction() {
        username.clear();
        password.clear();
        bankName.getSelectionModel().selectFirst();
    }
}