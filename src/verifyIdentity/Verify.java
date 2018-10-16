package verifyIdentity;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Verify extends VerifyPassword {
    public boolean verifyPassword() {
        try {
            Pane tempPane = FXMLLoader.load(getClass().getResource("verifyPassword.fxml"));
            tempStage = new Stage();
            tempStage.setScene(new Scene(tempPane, 600, 400));
            tempStage.showAndWait();
        } catch (Exception e) {
            Alert homePageNotLoad = new Alert(Alert.AlertType.ERROR);
            homePageNotLoad.setContentText("Password could not be verified");
            homePageNotLoad.showAndWait();
            return false;
        }

        if (result) {
            result = false;
            return true;
        }

        return false;
    }
}