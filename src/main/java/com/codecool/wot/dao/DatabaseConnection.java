package com.codecool.wot.dao;

import java.sql.*;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException{
        Connection connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/queststore.db");
        return connection;
    }
}
