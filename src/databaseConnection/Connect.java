package databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
    public static Statement connectBankDB() throws SQLException {
        String url = "jdbc:mysql://db4free.net:3306/bank_details?useSSL=false";
        String user = "bank_root";
        String password = "password";
        Connection sqlConnect = DriverManager.getConnection(url, user, password);
        return sqlConnect.createStatement();
    }

    public static Statement connectUserDB() throws SQLException {
        String url = "jdbc:mysql://db4free.net:3306/user_details?useSSL=false";
        String user = "bank_user";
        String password = "password";
        Connection sqlConnect = DriverManager.getConnection(url, user, password);
        return sqlConnect.createStatement();
    }
}