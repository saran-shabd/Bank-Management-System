import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        Main.primaryStage.setTitle("Bank");
        Pane newPane = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        Main.primaryStage.setScene(new Scene(newPane));
        Main.primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}