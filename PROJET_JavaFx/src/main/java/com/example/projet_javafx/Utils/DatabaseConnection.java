package com.example.projet_javafx.Utils;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/esprit_2", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
    }

    public static Connection getConnection() {
        return connection;
    }
}