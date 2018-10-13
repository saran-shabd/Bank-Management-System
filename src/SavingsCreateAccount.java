import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
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
}