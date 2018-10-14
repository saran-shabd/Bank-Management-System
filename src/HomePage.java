import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class HomePage {
    @FXML private BorderPane container;

    @FXML public void homeScreen() {
        try {
            Pane tempPane = FXMLLoader.load(getClass().getResource("homePage.fxml"));
            Main.primaryStage.setScene(new Scene(tempPane, 1000, 600));
            Main.primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML public void close() {
        Main.primaryStage.close();
    }

    @FXML public void savingsCreateAccount() {
        if (new Verify().verifyPassword()) {
            try {
                Pane tempPane = FXMLLoader.load(getClass().getResource("savingsCreateAccount.fxml"));
                container.setCenter(tempPane);
            } catch (Exception e) {
                e.printStackTrace();
            }
       }
    }
}
