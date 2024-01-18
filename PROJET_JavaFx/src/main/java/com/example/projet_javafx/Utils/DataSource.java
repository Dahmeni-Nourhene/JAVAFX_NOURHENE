package com.example.projet_javafx.Utils;


import java.sql.*;

public class DataSource {
    private static DataSource data;
    private Connection con;
    private String url = "jdbc:mysql://localhost:3306/foodiefinder";
    private String user = "root";
    private String pwd = "";

    private DataSource() {
        try {
            con = DriverManager.getConnection(url, user, pwd);
            System.out.println("connexion établie");
        } catch (SQLException e) {
            System.out.println("waaaa3333");
            System.out.println(e);
        }
    }

    public Connection getCon() {
        return con;
    }

    public static DataSource getInstance() {
        if (data == null) {
            data = new DataSource();
        }
        return data;
    }

}