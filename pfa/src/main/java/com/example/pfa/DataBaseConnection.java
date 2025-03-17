package com.example.pfa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private Connection databaseLink;

    public Connection getConnection() {
        String dataBaseName = "poc_base";
        String dataBaseUser = "root";
        String dataBasePassword = "aliali";
        String dataBaseURL = "jdbc:mysql://127.0.0.1:3306/" + dataBaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(dataBaseURL, dataBaseUser, dataBasePassword);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
        }

        return databaseLink;
    }
}
