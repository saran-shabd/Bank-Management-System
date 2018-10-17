package loginAndHome;

import javafx.scene.control.Alert;
import verifyIdentity.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class HomePage {
    @FXML private BorderPane container;

    @SuppressWarnings("Duplicates")
    @FXML public void homeScreen() {
        try {
            Pane tempPane = FXMLLoader.load(getClass().getResource("homePage.fxml"));
            Main.primaryStage.setScene(new Scene(tempPane, 1000, 600));
            Main.primaryStage.show();
        } catch (Exception e) {
            Alert homePageNotLoad = new Alert(Alert.AlertType.ERROR);
            homePageNotLoad.setContentText("Home Page could not be loaded");
            homePageNotLoad.showAndWait();
        }
    }

    @FXML public void close() {
        Main.primaryStage.close();
    }

    @FXML public void logoutOnAction() {
        try {
            Pane tempPane = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
            Main.primaryStage.setScene(new Scene(tempPane, 1000, 600));
            Main.primaryStage.show();
        } catch (Exception e) {
            Alert homePageNotLoad = new Alert(Alert.AlertType.ERROR);
            homePageNotLoad.setContentText("Home Page could not be loaded");
            homePageNotLoad.showAndWait();
        }
    }

    @FXML public void savingsCreateAccount() {
        if (new Verify().verifyPassword()) {
            try {
                Pane tempPane = FXMLLoader.load(getClass().getResource("/createAccount/savingsCreateAccount.fxml"));
                container.setCenter(tempPane);
            } catch (Exception e) {
                Alert homePageNotLoad = new Alert(Alert.AlertType.ERROR);
                homePageNotLoad.setContentText("Create Account Page could not be loaded");
                homePageNotLoad.showAndWait();
            }
        }
    }

    @FXML public void savingsDeleteAccount() {
        if (new Verify().verifyPassword()) {
            try {
                Pane tempPane = FXMLLoader.load(getClass().getResource("/deleteAccount/savingsDeleteAccount.fxml"));
                container.setCenter(tempPane);
            } catch (Exception e) {
                Alert homePageNotLoad = new Alert(Alert.AlertType.ERROR);
                homePageNotLoad.setContentText("Delete Account Page could not be loaded");
                homePageNotLoad.showAndWait();
            }
        }
    }

    @FXML public void savingsViewAccount() {
        try {
            Pane tempPane = FXMLLoader.load(getClass().getResource("/viewAccount/savingsViewAccount.fxml"));
            container.setCenter(tempPane);
        } catch (Exception e) {
            Alert homePageNotLoad = new Alert(Alert.AlertType.ERROR);
            homePageNotLoad.setContentText("View Account Page could not be loaded");
            homePageNotLoad.showAndWait();
        }
    }

    @FXML public void makeTransactionOnAction() {
        if (new Verify().verifyPassword()) {
            try {
                Pane tempPane = FXMLLoader.load(getClass().getResource("/transactions/makeTransaction.fxml"));
                container.setCenter(tempPane);
            } catch (Exception e) {
                Alert homePageNotLoad = new Alert(Alert.AlertType.ERROR);
                homePageNotLoad.setContentText("Make Transaction Page could not be loaded");
                homePageNotLoad.showAndWait();
            }
        }
    }

    @FXML public void viewTransactionsOnAction() {
        try {
            Pane tempPane = FXMLLoader.load(getClass().getResource("/transactions/viewTransaction.fxml"));
            container.setCenter(tempPane);
        } catch (Exception e) {
            Alert homePageNotLoad = new Alert(Alert.AlertType.ERROR);
            homePageNotLoad.setContentText("View Transactions Page could not be loaded");
            homePageNotLoad.showAndWait();
        }
    }
}
