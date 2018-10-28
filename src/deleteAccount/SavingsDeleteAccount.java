package deleteAccount;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import loginAndHome.Main;

import java.sql.*;

public class SavingsDeleteAccount {
    @FXML TextField accountNumber, aadharNumber;

    public void submitOnAction() {
        try {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setContentText("Are you sure?");
            confirmation.showAndWait();
            if (confirmation.getResult() == ButtonType.OK) {

                Statement sqlStatement = databaseConnection.Connect.connectUserDB();

                String query = "select aadhar_number from account_details where account_number = " + accountNumber.getText();
                ResultSet sqlResult = sqlStatement.executeQuery(query);

                if (!sqlResult.next()) {
                    Alert invalidCredentials = new Alert(Alert.AlertType.ERROR);
                    invalidCredentials.setContentText("Records does not exist");
                    invalidCredentials.showAndWait();
                    return;
                } else if (!sqlResult.getString("aadhar_number").equals(aadharNumber.getText())) {
                    Alert invalidCredentials = new Alert(Alert.AlertType.ERROR);
                    invalidCredentials.setContentText("Records does not exist");
                    invalidCredentials.showAndWait();
                    return;
                }

                query = "delete from account_details where account_number = " + accountNumber.getText();
                sqlStatement.executeUpdate(query);

                query = "delete from cheque_details where account_number = " + accountNumber.getText();
                sqlStatement.executeUpdate(query);


                Alert accountDeleted = new Alert(Alert.AlertType.INFORMATION);
                accountDeleted.setContentText("This account has been deleted");
                accountDeleted.showAndWait();

                Pane newPane = FXMLLoader.load(getClass().getResource("/loginAndHome/homePage.fxml"));
                Main.primaryStage.setScene(new Scene(newPane, 1000, 600));
                Main.primaryStage.show();
            }
        } catch (SQLException e) {
            Alert internetPoor = new Alert(Alert.AlertType.ERROR);
            internetPoor.setContentText("Connection failed due to poor internet connection");
            internetPoor.showAndWait();
        } catch (Exception e) {
            Alert homePageNotLoad = new Alert(Alert.AlertType.ERROR);
            homePageNotLoad.setContentText("Account could not be delete. Try Again");
            homePageNotLoad.showAndWait();
        }
    }
}