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
}

