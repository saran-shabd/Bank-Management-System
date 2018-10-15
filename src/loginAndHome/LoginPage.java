package loginAndHome;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginPage implements Initializable {
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private ChoiceBox<String> bankName;

    private static Statement sqlStatement;

    public static String usernameString;
    public static String passwordString;
    public static String bankNameString;
    public static String bankCodeString;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        login(bankName);
    }

    static void login(ChoiceBox<String> bankName) {
        try {
            String url = "jdbc:mysql://db4free.net:3306/bank_details?useSSL=false";
            String username = "bank_root";
            String password = "password";
            Connection sqlConnection = DriverManager.getConnection(url, username, password);
            sqlStatement = sqlConnection.createStatement();
            @SuppressWarnings("SqlDialectInspection")
            String query = "select distinct bank_branch_name from employees_details;";
            ResultSet sqlResult = sqlStatement.executeQuery(query);
            while (sqlResult.next()) {
                bankName.getItems().add(sqlResult.getString("bank_branch_name"));
            }
            bankName.getSelectionModel().selectFirst();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void LogInOnAction() throws Exception {
        if (!validateUserLoginInput(username, password, bankName)) {
            ResetOnAction();
            return;
        }

        usernameString = username.getText();
        passwordString = password.getText();
        bankNameString = bankName.getValue();

        String query = "select bank_branch_code from employees_details where bank_branch_name=\'" + bankNameString + "\'";
        ResultSet sqlResult = sqlStatement.executeQuery(query);
        if (sqlResult.next()) {
            bankCodeString = sqlResult.getString("bank_branch_code");
        }

        Pane newPane = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        Main.primaryStage.setScene(new Scene(newPane, 1000, 600));
        Main.primaryStage.show();
    }

    static boolean validateUserLoginInput(TextField username, PasswordField password, ChoiceBox<String> bankName) {
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            Alert emptyFields = new Alert(Alert.AlertType.ERROR);
            emptyFields.setContentText("Some Fields are missing");
            emptyFields.show();
            return false;
        }

        try {
            String query = "select username, password, bank_branch_name from employees_details";
            ResultSet sqlResult = sqlStatement.executeQuery(query);
            while (sqlResult.next()) {
                if (sqlResult.getString("username").equals(username.getText()) &&
                sqlResult.getString("password").equals(password.getText()) &&
                sqlResult.getString("bank_branch_name").equals(bankName.getValue())) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Alert invalidInput = new Alert(Alert.AlertType.ERROR);
        invalidInput.setContentText("Invalid credentials");
        invalidInput.show();

        return false;
    }

    @FXML
    public void ResetOnAction() {
        username.clear();
        password.clear();
        bankName.getSelectionModel().selectFirst();
    }
}