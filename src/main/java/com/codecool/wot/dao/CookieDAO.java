package com.codecool.wot.dao;

import java.sql.*;

public class CookieDAO {

    private Connection connection;

    public CookieDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveToDatabase(Integer userId, String cookie){

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

    public Integer getByCoockie(String sessionId){

        try{
            String query = "SELECT userID FROM  cookies  WHERE cookie = ?";
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, sessionId);
            ResultSet rs = stmt.executeQuery();

            Integer userId = rs.getInt("userID");

            rs.close();
            stmt.close();

            return userId;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



}
