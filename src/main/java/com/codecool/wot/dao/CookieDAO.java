package com.codecool.wot.dao;

import com.codecool.wot.model.Cookie;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CookieDAO {
    private static final CookieDAO INSTANCE = new CookieDAO();

    private List<Cookie> cookies;

    private CookieDAO() {
        cookies = new LinkedList<>();
        loadCookiesFromDatabase();
    }

    public static CookieDAO getInstance() {
        return INSTANCE;
    }

    public void add(Cookie cookie) {
        if (getCookie(cookie.getSessionId()) == null) {
            try (Connection con = DatabaseConnection.getConnection();
                 PreparedStatement ps = createAddPreparedStatement(con, cookie)) {
                con.setAutoCommit(false);
                ps.executeUpdate();
                con.commit();

                cookies.add(cookie);

            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }

    public void remove(Cookie cookie) {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createDeletePreparedStatement(con, cookie)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();

            cookies.remove(cookie);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public Cookie getCookie(String sessionId) {
        Cookie cookie = null;
        for (Cookie candidate : this.cookies) {
            if (candidate.getSessionId().equals(sessionId)) {
                cookie = candidate;
            }
        }
        return cookie;
    }

    private void loadCookiesFromDatabase() {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createSelectPreparedStatement(con);
             ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                Integer userId = result.getInt("userID");
                String sessionId = result.getString("cookie");

                cookies.add(new Cookie(userId, sessionId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private PreparedStatement createSelectPreparedStatement(Connection con) throws SQLException {
        String query = "SELECT * FROM  cookies;";
        PreparedStatement ps = con.prepareStatement(query);

        return ps;
    }

    private PreparedStatement createAddPreparedStatement(Connection con, Cookie cookie) throws SQLException {
        String query = "INSERT INTO cookies (userID, cookie) VALUES  (?, ?);";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, cookie.getUserId());
        ps.setString(2, cookie.getSessionId());

        return ps;
    }

    private PreparedStatement createDeletePreparedStatement(Connection con, Cookie cookie) throws SQLException {
        String query = "DELETE FROM cookies WHERE cookie = ?;";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, cookie.getSessionId());

        return ps;
    }
}
