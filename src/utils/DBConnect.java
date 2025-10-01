package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static final String URL = "jdbc:postgresql://localhost:5433/banque_db";
    private static final String USER = "bank_user";
    private static final String PASSWORD = "123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

