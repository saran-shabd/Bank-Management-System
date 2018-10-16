package viewAccount;

import javafx.beans.property.SimpleStringProperty;

public class UserDetailsClass {
    private SimpleStringProperty name, dob, address, phoneNumber, email, aadharNumber, bankBranchCode, accountBalance, accountNumber;

    public UserDetailsClass(String name, String dob, String address, String phoneNumber, String email, String aadharNumber, String bankBranchCode, String accountBalance, String accountNumber) {
        this.name = new SimpleStringProperty(name);
        this.dob = new SimpleStringProperty(dob);
        this.address = new SimpleStringProperty(address);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.email = new SimpleStringProperty(email);
        this.aadharNumber = new SimpleStringProperty(aadharNumber);
        this.bankBranchCode = new SimpleStringProperty(bankBranchCode);
        this.accountBalance = new SimpleStringProperty(accountBalance);
        this.accountNumber = new SimpleStringProperty(accountNumber);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty dobProperty() {
        return dob;
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public SimpleStringProperty aadharNumberProperty() {
        return aadharNumber;
    }

    public SimpleStringProperty bankBranchCodeProperty() {
        return bankBranchCode;
    }

    public SimpleStringProperty accountBalanceProperty() {
        return accountBalance;
    }

    public SimpleStringProperty accountNumberProperty() {
        return accountNumber;
    }
}