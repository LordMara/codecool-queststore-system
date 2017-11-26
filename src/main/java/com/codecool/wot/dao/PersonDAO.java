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
        loadPersonsFromDatabase();
    }

    public static PersonDAO getInstance() {
        return INSTANCE;
    }

    private void loadPersonsFromDatabase() {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createSelectPreparedStatement(con);
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

    public List<Account> read() {
        return persons;
    }

    public void add(Account person) throws SQLException {
        persons.add(person);
        addPersonToDatabase(person);
    }

    private void addPersonToDatabase(Account person) throws SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createAddPreparedStatement(con, person)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();
        }
    }

    private PreparedStatement createSelectPreparedStatement(Connection con) throws SQLException {
        String query = "SELECT * FROM persons LEFT JOIN persons_classes ON persons_classes.personId = persons.personId;";
        PreparedStatement ps = con.prepareStatement(query);

        return ps;
    }

    private PreparedStatement createAddPreparedStatement(Connection con, Account person) throws SQLException {
        String role = "";
        String query = "INSERT INTO persons (personId, name, surname, email,  phone, login, password, role)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        if (person instanceof Mentor) {
            role = "mentor";
        } else if (person instanceof Student) {
            role = "student";
        }

        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, person.getId());
        ps.setString(2, person.getName());
        ps.setString(3, person.getSurname());
        ps.setString(4, person.getEmail());
        ps.setString(5, person.getPhone());
        ps.setString(6, person.getLogin());
        ps.setString(7, person.getPassword());
        ps.setString(8, role);

        return ps;
    }
}

