package createAccount;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import loginAndHome.LoginPage;
import loginAndHome.Main;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SavingsCreateAccount implements Initializable {
    @FXML private TextField name, residentialAddress1, residentialAddress2, residentialAddress3, phoneNumber, emailAddress, aadharNumber;
    @FXML private DatePicker dob;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dob.setValue(LocalDate.of(1999, 11, 4));
    }

    @FXML public void submitOnAction() {
        if (name.getText().isEmpty() ||
            residentialAddress1.getText().isEmpty() ||
            residentialAddress2.getText().isEmpty() ||
            residentialAddress3.getText().isEmpty() ||
            phoneNumber.getText().isEmpty() ||
            emailAddress.getText().isEmpty() ||
            aadharNumber.getText().isEmpty()) {

            Alert emptyFields = new Alert(Alert.AlertType.ERROR);
            emptyFields.setContentText("Some Fields are missing");
            emptyFields.show();
            return;
        }

        try {
            String url = "jdbc:mysql://db4free.net:3306/user_details?useSSL=false";
            String user = "bank_user";
            String pass = "password";
            Connection sqlConnection = DriverManager.getConnection(url, user, pass);
            Statement sqlStatement = sqlConnection.createStatement();

            String query = "select account_number from account_details";
            ResultSet sqlResult = sqlStatement.executeQuery(query);
            String temp = null;
            while (sqlResult.next()) {
                temp = sqlResult.getString("account_number");
            }

            String accountNumber;
            if (null == temp) {
                accountNumber = 6049 + LoginPage.bankCodeString + "00000001";
            } else {
                accountNumber = "6049" + LoginPage.bankCodeString;
                accountNumber += accountNumberGenerator(temp.substring(8));
            }

            query = "insert into account_details(name, dob, address, phone_number, email, aadhar_number, bank_branch_code, account_balance, account_number) values" +
                    "(\'" + name.getText() +"\'," +
                    "\'" + dob.getValue() + "\'," +
                    "\'" + residentialAddress1.getText() + "," + residentialAddress2.getText() + "," + residentialAddress3.getText() + "\'," +
                    "\'" + phoneNumber.getText() + "\'," +
                    "\'" + emailAddress.getText() + "\'," +
                    "\'" + aadharNumber.getText() + "\'," +
                    "\'" + LoginPage.bankCodeString + "\'," +
                    "\'" + 2000 + "\'," +
                    "\'" + accountNumber + "\')";
            sqlStatement.executeUpdate(query);

            Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
            confirmation.setHeaderText("Confirmation");
            confirmation.setContentText("Account Created Successfully");
            confirmation.showAndWait();

            Pane newPane = FXMLLoader.load(getClass().getResource("/loginAndHome/homePage.fxml"));
            Main.primaryStage.setScene(new Scene(newPane, 1000, 600));
            Main.primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML public void resetOnAction() {
        name.clear();
        residentialAddress1.clear();
        residentialAddress2.clear();
        residentialAddress3.clear();
        phoneNumber.clear();
        emailAddress.clear();
        aadharNumber.clear();
        dob.setValue(LocalDate.of(1999, 11, 4));
    }

    private String accountNumberGenerator(String prev) {
        long val = Long.parseLong(prev);
        ++val;
        long temp = val;
        int count = 0;
        while (temp > 0) {
            temp /= 10;
            ++count;
        }
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 8 - count; ++i)
            accountNumber.append(0);
        accountNumber.append(val);
        return accountNumber.toString();
    }
}