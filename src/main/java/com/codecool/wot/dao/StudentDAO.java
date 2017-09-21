package com.codecool.wot.dao;
import com.codecool.wot.model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class StudentDAO extends AbstractCodecoolerDAO<Student>{

    public StudentDAO(Connection connection) {
        loadStudents(connection);
    }

    private void loadStudents(Connection connection) {

        try {
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();

            String query = "SELECT * FROM persons JOIN persons_classes ON persons_classes.personId = persons.personId WHERE role ='student'";

            ResultSet rs = stmt.executeQuery(query);

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

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveToDataBase(Connection connection, Student student) {

        try {

            Statement stmt = connection.createStatement();

            String values = String.format("(%d, %s, %s, %s, %s, %s, %s, student)", student.getId(), student.getName(), student.getSurname()
                    , student.getEmail(), student.getPhone(), student.getLogin(), student.getPassword());

            String values2 = String.format("(%d, %d", student.getId(), student.getClassId());

            String query1 = "INSERT INTO persons (personId, name, surname, email,  phone, login, password, role) VALUES " + values;

            String query2 = "INSERT INTO persons_classes (personId, classId) VALUES " + values2;

            stmt.executeUpdate(query1);
            stmt.executeQuery(query2);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

