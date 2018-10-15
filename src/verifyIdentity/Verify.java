package verifyIdentity;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Verify extends VerifyPassword {
    public boolean verifyPassword() {
        try {
            Pane tempPane = FXMLLoader.load(getClass().getResource("verifyPassword.fxml"));
            tempStage = new Stage();
            tempStage.setScene(new Scene(tempPane, 600, 400));
            tempStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result) {
            result = false;
            return true;
        }

        return false;
    }
}