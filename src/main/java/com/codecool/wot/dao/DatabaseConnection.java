package com.codecool.wot.dao;

import java.sql.*;

public class DatabaseConnection {

    private static DatabaseConnection instance = new DatabaseConnection();

    private Connection connection;

    private DatabaseConnection() {


            String url = "jdbc:sqlite:src/main/resources/db/queststore.db";

            setConnection(url);

    }

    private void setConnection(String url) {

        try {
            this.connection = DriverManager.getConnection(url);
            this.connection.setAutoCommit(false);

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static DatabaseConnection getDBConnection(){
        return instance;
    }

    public Connection getConnection() {
        return this.connection;
    }
}
