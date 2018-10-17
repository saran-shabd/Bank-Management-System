package viewAccount;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import loginAndHome.LoginPage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SavingsViewAccount implements Initializable {
    @FXML private TableView<UserDetailsClass> table;
    @FXML TableColumn<UserDetailsClass, String> name, dob, address, phoneNumber, email, aadharNumber, bankBranchCode, accountBalance, accountNumber;
    @FXML private TextField recordsAccountNumber;

    private ObservableList<UserDetailsClass> data;

    private Statement sqlStatement;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            sqlStatement = databaseConnection.Connect.connectUserDB();
        } catch (com.mysql.cj.jdbc.exceptions.CommunicationsException e) {
            Alert internetProblem = new Alert(Alert.AlertType.ERROR);
            internetProblem.setContentText("Connection Failed due to poor internet connection");
            internetProblem.showAndWait();
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML public void viewAllRecordsOnAction() {
        try {
            data = FXCollections.observableArrayList();
            String query = "select * from account_details where bank_branch_code = " + LoginPage.bankCodeString;
            ResultSet sqlResult = sqlStatement.executeQuery(query);

            while (sqlResult.next()) {
                data.add(new UserDetailsClass(sqlResult.getString("name"), sqlResult.getString("dob"),
                        sqlResult.getString("address"), sqlResult.getString("phone_number"),
                        sqlResult.getString("email"), sqlResult.getString("aadhar_number"),
                        sqlResult.getString("bank_branch_code"), sqlResult.getString("account_balance"), sqlResult.getString("account_number")));
            }

            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
            address.setCellValueFactory(new PropertyValueFactory<>("address"));
            phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            aadharNumber.setCellValueFactory(new PropertyValueFactory<>("aadharNumber"));
            bankBranchCode.setCellValueFactory(new PropertyValueFactory<>("bankBranchCode"));
            accountBalance.setCellValueFactory(new PropertyValueFactory<>("accountBalance"));
            accountNumber.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));

            table.setItems(null);
            table.setItems(data);

        } catch (com.mysql.cj.jdbc.exceptions.CommunicationsException e) {
            Alert internetProblem = new Alert(Alert.AlertType.ERROR);
            internetProblem.setContentText("Connection Failed due to poor internet connection");
            internetProblem.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML public void viewSpecificRecordsOnAction() {
        try {
            data = FXCollections.observableArrayList();
            String query = "select * from account_details where bank_branch_code = " + LoginPage.bankCodeString + " and account_number = " + recordsAccountNumber.getText();
            ResultSet sqlResult = sqlStatement.executeQuery(query);

            while (sqlResult.next()) {
                data.add(new UserDetailsClass(sqlResult.getString("name"), sqlResult.getString("dob"),
                        sqlResult.getString("address"), sqlResult.getString("phone_number"),
                        sqlResult.getString("email"), sqlResult.getString("aadhar_number"),
                        sqlResult.getString("bank_branch_code"), sqlResult.getString("account_balance"), sqlResult.getString("account_number")));
            }

            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
            address.setCellValueFactory(new PropertyValueFactory<>("address"));
            phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            aadharNumber.setCellValueFactory(new PropertyValueFactory<>("aadharNumber"));
            bankBranchCode.setCellValueFactory(new PropertyValueFactory<>("bankBranchCode"));
            accountBalance.setCellValueFactory(new PropertyValueFactory<>("accountBalance"));
            accountNumber.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));

            table.setItems(null);
            table.setItems(data);

        } catch (com.mysql.cj.jdbc.exceptions.CommunicationsException e) {
            Alert internetProblem = new Alert(Alert.AlertType.ERROR);
            internetProblem.setContentText("Connection Failed due to poor internet connection");
            internetProblem.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}