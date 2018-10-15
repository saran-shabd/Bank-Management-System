package deleteAccount;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import loginAndHome.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SavingsDeleteAccount {
    @FXML TextField accountNumber, aadharNumber;

    public void submitOnAction() {
        try {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setContentText("Are you sure?");
            confirmation.showAndWait();
            if (confirmation.getResult() == ButtonType.OK) {
                String url = "jdbc:mysql://localhost:3306/bank_user_details?useSSL=false";
                String user = "root";
                String pass = "password";
                Connection sqlConnection = DriverManager.getConnection(url, user, pass);
                Statement sqlStatement = sqlConnection.createStatement();

                String query = "select aadhar_number from user_details where account_number = " + accountNumber.getText();
                ResultSet sqlResult = sqlStatement.executeQuery(query);

                if (!sqlResult.next()) {
                    Alert invalidCredentials = new Alert(Alert.AlertType.ERROR);
                    invalidCredentials.setContentText("Records does not exist");
                    invalidCredentials.showAndWait();
                    return;
                } else if (sqlResult.getString("aadhar_number").equals(aadharNumber.getText())) {
                    Alert invalidCredentials = new Alert(Alert.AlertType.ERROR);
                    invalidCredentials.setContentText("Records does not exist");
                    invalidCredentials.showAndWait();
                    return;
                }

                query = "delete from user_details where account_number = " + accountNumber.getText();
                sqlStatement.executeUpdate(query);


                Alert accountDeleted = new Alert(Alert.AlertType.INFORMATION);
                accountDeleted.setContentText("This account has been deleted");
                accountDeleted.showAndWait();

                Pane newPane = FXMLLoader.load(getClass().getResource("/loginAndHome/homePage.fxml"));
                Main.primaryStage.setScene(new Scene(newPane, 1000, 600));
                Main.primaryStage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}