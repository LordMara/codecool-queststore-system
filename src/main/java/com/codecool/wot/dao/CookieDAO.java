package com.codecool.wot.dao;

import java.sql.*;

public class CookieDAO {

    private Connection connection;

    public CookieDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveToDatabase(Integer userId, String cookie) {

        try {

            String query = "INSERT INTO cookies (userID, cookie) VALUES  (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setInt(1, userId);
            stmt.setString(2, cookie);

            stmt.executeUpdate();
            stmt.close();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer getUserIdBySessionId(String sessionId){
        Integer userId = null;

        try{
            String query = "SELECT userID FROM  cookies  WHERE cookie = ?";
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, sessionId);
            ResultSet rs = stmt.executeQuery();

            userId = rs.getInt("userID");

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId;
    }
}
