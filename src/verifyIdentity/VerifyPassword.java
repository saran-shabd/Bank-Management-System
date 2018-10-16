package verifyIdentity;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import loginAndHome.LoginPage;

import java.sql.*;

public class VerifyPassword {
    @FXML protected static Stage tempStage;
    @FXML private PasswordField password;

    protected static boolean result = false;

    @FXML public void confirmOnAction() {
        try {
            String url = "jdbc:mysql://localhost:3306/bank_details?useSSL=false";
            String user = "root";
            String pass = "password";
            Connection sqlConnection = DriverManager.getConnection(url, user, pass);
            Statement sqlStatement = sqlConnection.createStatement();
            String query = "select password from employees_details where username=\'" + LoginPage.usernameString + "\'";
            ResultSet sqlResult = sqlStatement.executeQuery(query);
            if (sqlResult.next()) {
                if (sqlResult.getString("password").equals(password.getText())) {
                    VerifyPassword.result = true;
                }
            }
            tempStage.close();
        } catch (SQLException e) {
            Alert internetPoor = new Alert(Alert.AlertType.ERROR);
            internetPoor.setContentText("Connection Failed due to poor Internet Connection");
            internetPoor.showAndWait();
        }
    }

    @FXML public void cancelOnAction() {
        tempStage.close();
    }
}