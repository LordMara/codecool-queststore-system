package com.codecool.wot.dao;

import java.sql.*;

public class CookieDAO {
    private static final CookieDAO INSTANCE = new CookieDAO();

    private CookieDAO() {

    }

    public static CookieDAO getInstance() {
        return INSTANCE;
    }

    public void add(Integer userId, String cookie) {

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createPreparedStatement(con, userId, sessionId)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFromDatabase(String sessionId) {

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createDeletePreparedStatement(con, sessionId)) {
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
             PreparedStatement ps = createSelectPreparedStatement(con, sessionId);
             ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                userId = result.getInt("userID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId;
    }

    private PreparedStatement createAddPreparedStatement(Connection con, Integer userId, String cookie) throws SQLException {
        String query = "INSERT INTO cookies (userID, cookie) VALUES  (?, ?);";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, userId);
        ps.setString(2, sessionId);

        return ps;
    }

    private PreparedStatement createSelectPreparedStatement(Connection con, String sessionId) throws SQLException {
        String query = "SELECT userID FROM  cookies  WHERE cookie = ?;";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, sessionId);

        return ps;
    }

    private PreparedStatement createDeletePreparedStatement(Connection con, String sessionId) throws SQLException {
        String query = "DELETE FROM  cookies  WHERE cookie = ?;";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, sessionId);

        return ps;
    }
}
