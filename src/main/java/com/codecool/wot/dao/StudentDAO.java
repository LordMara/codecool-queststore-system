package com.codecool.wot.dao;
import com.codecool.wot.model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class StudentDAO extends AbstractDAO<Student, String>{

    public StudentDAO(Connection connection) throws SQLException {
        this.connection = connection;
        String loadQuery = "SELECT * FROM persons JOIN persons_classes ON persons_classes.personId = persons.personId WHERE role ='student'";
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


            objectsList.add(new Student(name, surname, email, login, password, Id, classId));
        }

    }

    public String updateQuery(Student student) {
        return String.format("UPDATE persons SET name = '%s', surname = '%s', login = '%s', password = '%s'	WHERE personId = %d ;",
                student.getName(), student.getSurname(), student.getLogin(), student.getPassword(), student.getId());
    }

    public boolean getByCondition(Student student, String login) {
        return student.getLogin().equals(login);
    }

    public String insertionQuery(String ... args) {

        String values = String.format("('%s', 'student')",String.join("', '", args));
        String query = "INSERT INTO persons (name, surname, email, login, password, role) VALUES " + values;

        return query;
    }

    public String getIDFromDBQuery(String login) {
        return "SELECT personId FROM persons WHERE login = " + String.format("'%s';", login);
    }

}

