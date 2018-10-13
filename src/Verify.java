import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

class Verify extends VerifyIdentity {
    boolean checkIdentity() {
        try {
            Pane tempPane = FXMLLoader.load(getClass().getResource("verifyIdentity.fxml"));
            VerifyIdentity.tempStage = new Stage();
            VerifyIdentity.tempStage.setScene(new Scene(tempPane, 1000, 600));
            VerifyIdentity.tempStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return VerifyIdentity.result;
    }
}