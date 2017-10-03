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

            String Id = rs.getString("personId");
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String email = rs.getString("email");
            String login = rs.getString("login");
            String password = rs.getString("password");
            String classId = rs.getString("classId");

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

    public String insertionQuery(String ... args) {

        String values = String.format("('%s' , 'mentor,)",String.join("', '", args));
        String query = "INSERT INTO persons (personId, name, surname, email, login, password, role) VALUES " + values;

        return query;
    }

    public String getIDFromDBQuery(String login) {
        return "SELECT personId FROM persons WHERE login = " + String.format("'%s';", login);
    }

}
