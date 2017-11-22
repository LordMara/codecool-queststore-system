package com.codecool.wot.dao;

import java.sql.*;

public class CookieDAO {
    private static final CookieDAO INSTANCE = new CookieDAO();

    public static CookieDAO getInstance() {
        return INSTANCE;
    }

    public void saveToDatabase(Integer userId, String cookie) {

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createPreparedStatement(con, userId, cookie)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer getUserId(String sessionId){
        Integer userId = null;

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createPreparedStatement(con, sessionId);
             ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                userId = result.getInt("userID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId;
    }

    private PreparedStatement createPreparedStatement(Connection con, Integer userId, String cookie) throws SQLException {
        String query = "INSERT INTO cookies (userID, cookie) VALUES  (?, ?);";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, userId);
        ps.setString(2, cookie);

        return ps;
    }

    private PreparedStatement createPreparedStatement(Connection con, String sessionId) throws SQLException {
        String query = "SELECT userID FROM  cookies  WHERE cookie = ?;";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, sessionId);

        return ps;
    }
}
