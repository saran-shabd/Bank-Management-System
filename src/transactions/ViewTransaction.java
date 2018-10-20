package transactions;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import loginAndHome.LoginPage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewTransaction {
    @FXML private TextField accountNumber;
    @FXML private TableView<TransactionDetails> table;
    @FXML private TableColumn<TransactionDetails, String> serialNumber, toAccountNumber, fromAccountNumber, transferAmount, date, medium;

    private ObservableList<TransactionDetails> data;

    private boolean isEmpty() {
        if (accountNumber.getText().equals("")) {
            Alert invalidCredentials = new Alert(Alert.AlertType.ERROR);
            invalidCredentials.setContentText("Enter account number");
            invalidCredentials.show();
            return true;
        }
        return false;
    }

    @FXML public void specificTransactionsOnAction() {
        try {
            if (isEmpty())  return;

            data = FXCollections.observableArrayList();
            Statement sqlStatement = databaseConnection.Connect.connectUserDB();
            String query = "select * from transactions where account_number = " + accountNumber.getText() + " OR to_account_number = " + accountNumber.getText();
            ResultSet sqlResult = sqlStatement.executeQuery(query);

            int i = 1;
            while (sqlResult.next()) {
                if (LoginPage.bankCodeString.equals(sqlResult.getString("bank_branch_code"))) {
                    data.add(new TransactionDetails(Integer.toString(i), sqlResult.getString("date"),
                            sqlResult.getString("account_number"), sqlResult.getString("to_account_number"),
                            sqlResult.getString("transfer_amount"), sqlResult.getString("medium")));
                    ++i;
                }
            }

            serialNumber.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
            toAccountNumber.setCellValueFactory(new PropertyValueFactory<>("toAccountNumber"));
            fromAccountNumber.setCellValueFactory(new PropertyValueFactory<>("fromAccountNumber"));
            transferAmount.setCellValueFactory(new PropertyValueFactory<>("transferAmount"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            medium.setCellValueFactory(new PropertyValueFactory<>("medium"));

            Alert dataReady = new Alert(Alert.AlertType.INFORMATION);
            dataReady.setContentText("Transactions loaded");
            dataReady.show();

            table.setItems(null);
            table.setItems(data);

        } catch (SQLException e) {
            e.printStackTrace();
            Alert internetPoor = new Alert(Alert.AlertType.ERROR);
            internetPoor.setContentText("Connection failed due to poor internet connection");
            internetPoor.showAndWait();
        } catch (Exception e) {
            Alert homePageNotLoad = new Alert(Alert.AlertType.ERROR);
            homePageNotLoad.setContentText("Account could not be created. Try Again");
            homePageNotLoad.showAndWait();
        }
    }

    @FXML public void allTransactionsOnAction() {
        try {
            data = FXCollections.observableArrayList();
            Statement sqlStatement = databaseConnection.Connect.connectUserDB();
            String query = "select * from transactions";
            ResultSet sqlResult = sqlStatement.executeQuery(query);

            int i = 1;
            while (sqlResult.next()) {
                if (LoginPage.bankCodeString.equals(sqlResult.getString("bank_branch_code"))) {
                    data.add(new TransactionDetails(Integer.toString(i), sqlResult.getString("date"),
                            sqlResult.getString("account_number"), sqlResult.getString("to_account_number"),
                            sqlResult.getString("transfer_amount"), sqlResult.getString("medium")));
                    ++i;
                }
            }

            serialNumber.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
            toAccountNumber.setCellValueFactory(new PropertyValueFactory<>("toAccountNumber"));
            fromAccountNumber.setCellValueFactory(new PropertyValueFactory<>("fromAccountNumber"));
            transferAmount.setCellValueFactory(new PropertyValueFactory<>("transferAmount"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            medium.setCellValueFactory(new PropertyValueFactory<>("medium"));

            Alert dataReady = new Alert(Alert.AlertType.INFORMATION);
            dataReady.setContentText("Transactions loaded");
            dataReady.show();

            table.setItems(null);
            table.setItems(data);

        } catch (SQLException e) {
            e.printStackTrace();
            Alert internetPoor = new Alert(Alert.AlertType.ERROR);
            internetPoor.setContentText("Connection failed due to poor internet connection");
            internetPoor.showAndWait();
        } catch (Exception e) {
            Alert homePageNotLoad = new Alert(Alert.AlertType.ERROR);
            homePageNotLoad.setContentText("Account could not be created. Try Again");
            homePageNotLoad.showAndWait();
        }
    }
}