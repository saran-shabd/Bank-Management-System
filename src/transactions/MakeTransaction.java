package transactions;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import loginAndHome.LoginPage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class MakeTransaction {
    @FXML private TextField chequeNumber, fromAccount, toAccount, amount;

    @FXML public void submitOnAction() {
        try {
            Statement sqlStatement = databaseConnection.Connect.connectUserDB();

            String query = "select unique_code, validity from cheque_details where account_number = " + fromAccount.getText();
            ResultSet sqlResult = sqlStatement.executeQuery(query);

            while (sqlResult.next()) {
                if (sqlResult.getString("unique_code").equals(chequeNumber.getText()) &&
                sqlResult.getString("validity").equals("0")) {

                    Statement tempSqlStatement = databaseConnection.Connect.connectUserDB();
                    String tempQuery = "select * from account_details where account_number = " + toAccount.getText();
                    ResultSet tempSqlResult = tempSqlStatement.executeQuery(tempQuery);
                    if (!tempSqlResult.next()) {
                        Alert receiverAccountDoesNotExist = new Alert(Alert.AlertType.ERROR);
                        receiverAccountDoesNotExist.setContentText("Receiver's account number is incorrect");
                        receiverAccountDoesNotExist.showAndWait();
                        return;
                    }
                    int receiverBalance = Integer.parseInt(tempSqlResult.getString("account_balance"));

                    tempQuery = "select account_balance from account_details where account_number = " + fromAccount.getText();
                    tempSqlResult = tempSqlStatement.executeQuery(tempQuery);

                    int currBalance = 0;

                    if (tempSqlResult.next()) {
                        if (Integer.parseInt(tempSqlResult.getString("account_balance")) <
                                Integer.parseInt(amount.getText())) {

                            Alert insufficientAmount = new Alert(Alert.AlertType.ERROR);
                            insufficientAmount.setContentText("Insufficient balance");
                            insufficientAmount.showAndWait();
                            return;
                        }

                        currBalance = Integer.parseInt(tempSqlResult.getString("account_balance"));
                    }


                    tempQuery = "update account_details set account_balance = " + (receiverBalance + Integer.parseInt(amount.getText()) + " where account_number = " + toAccount.getText());
                    tempSqlStatement.executeUpdate(tempQuery);

                    tempQuery = "update account_details set account_balance = " + (currBalance - Integer.parseInt(amount.getText()) + " where account_number = " + fromAccount.getText());
                    tempSqlStatement.executeUpdate(tempQuery);

                    tempQuery = "update cheque_details set validity = 1 where account_number = " + fromAccount.getText() + " and unique_code = " + chequeNumber.getText();
                    tempSqlStatement.executeUpdate(tempQuery);

                    tempQuery = "insert into transactions(account_number, transfer_amount, date, medium, bank_branch_code, to_account_number) values " +
                    "(\'" + fromAccount.getText() + "\'," +
                    "\'" + amount.getText() + "\'," +
                    "\'" + LocalDate.now().toString() + "\'," +
                    "\'" + "CHEQUE" + "\'," +
                    "\'" + LoginPage.bankCodeString + "\'," +
                    "\'" + toAccount.getText() + "\')";
                    tempSqlStatement.executeUpdate(tempQuery);

                    Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
                    confirmation.setContentText("Money successfully transferred");
                    confirmation.showAndWait();

                    chequeNumber.clear();
                    fromAccount.clear();
                    toAccount.clear();
                    amount.clear();

                    return;
                }
            }

            if (!sqlResult.next()) {
                Alert invalidInput = new Alert(Alert.AlertType.ERROR);
                invalidInput.setContentText("Cheque number or sender's account number is incorrect");
                invalidInput.show();
            }

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