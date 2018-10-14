import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

class Verify extends VerifyPassword {
    boolean verifyPassword() {
        try {
            Pane tempPane = FXMLLoader.load(getClass().getResource("verifyPassword.fxml"));
            VerifyPassword.tempStage = new Stage();
            VerifyPassword.tempStage.setScene(new Scene(tempPane, 1000, 600));
            VerifyPassword.tempStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return VerifyPassword.result;
    }
}