package com.codecool.wot.dao;

import com.codecool.wot.model.Mentor;

import java.sql.*;


public class MentorDAO extends AbstractDAO<Mentor, String> {

    public MentorDAO(Connection connection) throws SQLException {

        this.connection = connection;
        String loadQuery = "SELECT * FROM persons JOIN persons_classes ON persons_classes.personId = persons.personId WHERE role ='mentor'";
        load(loadQuery);
    }

    public void loadObjectsToLocalList(ResultSet rs) throws SQLException {

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
    }

    public  String updateQuery(Mentor mentor) {
        return String.format("UPDATE persons SET name = '%s', surname = '%s', login = '%s', password = '%s'	WHERE personId = %d ;",
                mentor.getName(), mentor.getSurname(), mentor.getLogin(), mentor.getPassword(), mentor.getId());
    }

    public boolean getByCondition(Mentor mentor, String login) {

        return mentor.getLogin().equals(login);
    }

    public String insertionQuery(Mentor mentor) {

        String values = String.format(" ('%d', '%s', '%s', '%s', '%s', '%s', '%s', 'mentor');", mentor.getId(), mentor.getName(), mentor.getSurname()
                , mentor.getEmail(), "0000", mentor.getLogin(), mentor.getPassword());

        String values2 = String.format("('%d', '%d');", mentor.getId(), mentor.getClassId());

        String query1 = "INSERT INTO persons (personId, name, surname, email,  phone, login, password, role) VALUES " + values;

        String query2 = "INSERT INTO persons_classes (personId, classId) VALUES " + values2;

        return query1+query2;


    }

}
