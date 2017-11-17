package com.codecool.wot.dao;
import com.codecool.wot.model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class StudentDAO extends AbstractCodecoolerDAO<Student>{

    private Connection connection;

    public StudentDAO(Connection connection) {
        this.connection = connection;
        loadStudents();
    }

    @Override
    public void add(Student object) {
        super.add(object);
        saveToDataBase(object);
    }

    private void loadStudents() {

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

    private void saveToDataBase(Student student) {

        try {

            Statement stmt = connection.createStatement();

            String values = String.format("(%d, '%s', '%s', '%s', '%s', '%s', '%s', 'student')", student.getId(), student.getName(), student.getSurname()
                    , student.getEmail(), student.getPhone(), student.getLogin(), student.getPassword());

            String values2 = String.format("('%d', '%d')", student.getId(), student.getClassId());

            String query1 = "INSERT INTO persons (personId, name, surname, email,  phone, login, password, role) VALUES " + values;

            String query2 = "INSERT INTO persons_classes (personId, classId) VALUES " + values2;

            stmt.executeUpdate(query1);

            stmt.executeUpdate(query2);
            stmt.close();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {

        try {
            Statement stmt = connection.createStatement();

            String query = String.format("UPDATE persons SET name = '%s', surname = '%s', login = '%s', password = '%s'	WHERE personId = %d ;",
                    student.getName(), student.getSurname(), student.getLogin(), student.getPassword(), student.getId());

            stmt.executeUpdate(query);

            stmt.close();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
