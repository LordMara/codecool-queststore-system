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

            Integer Id = rs.getInt("personId");
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String email = rs.getString("email");
            String login = rs.getString("login");
            String password = rs.getString("password");
            Integer classId = rs.getInt("classId");


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

    public String insertionQuery(Student student) {

        String values = String.format("(%d, '%s', '%s', '%s', '%s', '%s', '%s', 'student')", student.getId(), student.getName(), student.getSurname()
                , student.getEmail(), student.getPhone(), student.getLogin(), student.getPassword());

        String values2 = String.format("('%d', '%d')", student.getId(), student.getClassId());

        String query1 = "INSERT INTO persons (personId, name, surname, email,  phone, login, password, role) VALUES " + values;

        String query2 = "INSERT INTO persons_classes (personId, classId) VALUES " + values2;

        return query1+query2;
    }

}

