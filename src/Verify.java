import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

class Verify extends VerifyPassword {
    boolean verifyPassword() {
        try {
            Pane tempPane = FXMLLoader.load(getClass().getResource("verifyPassword.fxml"));
            VerifyPassword.tempStage = new Stage();
            VerifyPassword.tempStage.setScene(new Scene(tempPane, 600, 400));
            VerifyPassword.tempStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (VerifyPassword.result) {
            VerifyPassword.result = false;
            return true;
        }

        return false;
    }
}