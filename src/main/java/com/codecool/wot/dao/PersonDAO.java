package com.codecool.wot.dao;

import com.codecool.wot.model.Account;
import com.codecool.wot.model.Admin;
import com.codecool.wot.model.Mentor;
import com.codecool.wot.model.Student;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PersonDAO {
    private static final PersonDAO INSTANCE = new PersonDAO();

    private List<Account> persons;

    private PersonDAO() {
        persons = new LinkedList<>();
        loadPersons();
    }

    public static PersonDAO getInstance() {
        return INSTANCE;
    }

    private void loadPersons() {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createPreparedStatement(con);
             ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                Integer Id = result.getInt("personId");
                String name = result.getString("name");
                String surname = result.getString("surname");
                String email = result.getString("email");
                String login = result.getString("login");
                String password = result.getString("password");
                String role = result.getString("role");
                Integer classId = result.getInt("classId");

                if (role.equals("administrator")) {
                    persons.add(new Admin(name, surname, email, login, password, Id));
                } else if (role.equals("mentor")) {
                    persons.add(new Mentor(name, surname, email, login, password, Id, classId));
                } else if (role.equals("student")) {
                    persons.add(new Student(name, surname, email, login, password, Id, classId));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public Account getPerson(String login, String password) {
        Account person = null;
        for (Account candidate : this.persons){
            if (candidate.getLogin().equals(login) && candidate.getPassword().equals(password)){
                person = candidate;
            }
        }
        return person;
    }

    public Account getPerson(Integer id) {
        Account person = null;
        for (Account candidate : this.persons){
            if (candidate.getId().equals(id)){
                person = candidate;
            }
        }
        return person;
    }

    private PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        String query = "SELECT * FROM persons LEFT JOIN persons_classes ON persons_classes.personId = persons.personId;";
        PreparedStatement ps = con.prepareStatement(query);

        return ps;
    }
}

