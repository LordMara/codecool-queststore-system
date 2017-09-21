package com.codecool.wot.dao;

import java.sql.*;

public class DatabaseConnection {

    private static DatabaseConnection instance = new DatabaseConnection();

    private Connection connection;

    private DatabaseConnection() {

        try {

            String url = "jdbc:sqlite:queststore";
            setConnection(url);

        } catch ( SQLException e ) {
            e.printStackTrace();
        }

    }

    private void setConnection(String url) throws SQLException {

        this.connection = DriverManager.getConnection(url);

    }

    public static DatabaseConnection getDBConnection(){
        return instance;
    }

    public Connection getConnection() {
        return this.connection;
    }
}
