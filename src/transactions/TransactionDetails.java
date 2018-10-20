package transactions;

public class TransactionDetails {
    private String serialNumber, date, toAccountNumber, fromAccountNumber, transferAmount, medium;

    TransactionDetails(String serialNumber, String date, String fromAccountNumber, String toAccountNumber, String transferAmount, String medium) {
        this.serialNumber = serialNumber;
        this.date = date;
        this.toAccountNumber = toAccountNumber;
        this.fromAccountNumber = fromAccountNumber;
        this.transferAmount = transferAmount;
        this.medium = medium;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getDate() {
        return date;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public String getTransferAmount() {
        return transferAmount;
    }

    public String getMedium() {
        return medium;
    }
}