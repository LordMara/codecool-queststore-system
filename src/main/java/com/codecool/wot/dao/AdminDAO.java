package com.codecool.wot.dao;

import com.codecool.wot.model.Admin;
import java.sql.*;

public class AdminDAO extends AbstractCodecoolerDAO<Admin> {

    private Connection connection;

    public AdminDAO(Connection connection) {
        this.connection = connection;
        loadAdmins(connection);

    }

        private void loadAdmins(Connection connection) {

            try {
                connection.setAutoCommit(false);
                Statement stmt = connection.createStatement();

                String query = "SELECT * FROM persons WHERE role ='administrator'";

                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {

                    Integer Id = rs.getInt("personId");
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    String email = rs.getString("email");
                    String login = rs.getString("login");
                    String password = rs.getString("password");


                    objectsList.add(new Admin(name, surname, email, login, password, Id));
                }

                rs.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

}
