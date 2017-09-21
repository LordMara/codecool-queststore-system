package com.codecool.wot.dao;

import com.codecool.wot.model.Mentor;

import java.sql.*;


public class MentorDAO extends AbstractCodecoolerDAO<Mentor> {

    public MentorDAO(Connection connection) {
        loadMentors(connection);
    }

    private void loadMentors(Connection connection) {

        try {
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();

            String query = "SELECT * FROM persons JOIN persons_classes ON persons_classes.personId = persons.personId WHERE role ='mentor'";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {

                Integer Id = rs.getInt("personId");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String login = rs.getString("login");
                String password = rs.getString("password");
                Integer classId = rs.getInt("classId");

                objectsList.add(new Mentor(name, surname, email, login, password, Id, classId));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
