package com.codecool.wot.dao;

import com.codecool.wot.model.*;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PersonDAO {
    private static final PersonDAO INSTANCE = new PersonDAO();

    private List<Account> persons;

    private PersonDAO() {
        this.persons = new LinkedList<>();
        loadPersonsFromDatabase();
    }

    public static PersonDAO getInstance() {
        return INSTANCE;
    }

    public List<Account> read() {
        return this.persons;
    }

    public void add(Account person) {
        try {
            addPersonToDatabase(person);
            this.persons.add(person);
            if (person instanceof Student) {
                WalletDAO.getInstance().add(new Wallet(person.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void update(Account person) {
        try {
            updatePersonInDatabase(person);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void remove(Account person) {
        try {
            if (person instanceof Student || person instanceof Mentor) {
                removeFromApplication(person);
            }
            deletePersonFromDatabase(person);
            this.persons.remove(person);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public Account getPerson(String login, String password) {
        Account person = null;
        for (Account candidate : this.persons) {
            if (candidate.getLogin().equals(login) && candidate.getPassword().equals(password)) {
                person = candidate;
            }
        }
        return person;
    }

    public Account getPerson(Integer id) {
        Account person = null;
        for (Account candidate : this.persons) {
            if (candidate.getId().equals(id)) {
                person = candidate;
            }
        }
        return person;
    }

    public Account getPersonByFullName(String name, String surname) {
        Account person = null;
        for (Account candidate : this.persons) {
            if (candidate.getName().equals(name) && candidate.getSurname().equals(surname)) {
                person = candidate;
            }
        }
        return person;
    }

    public List<Account> getStudents() {
        List<Account> foundPersons = new LinkedList<>();
        for (Account candidate : this.persons) {
            if (candidate instanceof Student) {
                foundPersons.add(candidate);
            }
        }
        return foundPersons;
    }

    public List<Account> getMentors() {
        List<Account> foundPersons = new LinkedList<>();
        for (Account candidate : this.persons) {
            if (candidate instanceof Mentor) {
                foundPersons.add(candidate);
            }
        }
        return foundPersons;
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

    private void addPersonToDatabase(Account person) throws SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createAddPreparedStatement(con, person)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();
        }
    }

    private void updatePersonInDatabase(Account person) throws SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createUpdatePreparedStatement(con, person)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();
        }
    }

    private void deletePersonFromDatabase(Account person) throws SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createDeletePreparedStatement(con, person)) {
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
        String query = "INSERT INTO persons (name, surname, email, login, password, role)" +
                " VALUES (?, ?, ?, ?, ?, ?);";

        if (person instanceof Mentor) {
            role = "mentor";
        } else if (person instanceof Student) {
            role = "student";
        }

        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, person.getName());
        ps.setString(2, person.getSurname());
        ps.setString(3, person.getEmail());
        ps.setString(4, person.getLogin());
        ps.setString(5, person.getPassword());
        ps.setString(6, role);

        return ps;
    }

    private PreparedStatement createUpdatePreparedStatement(Connection con, Account person) throws SQLException {
        String query = "UPDATE persons SET name = ?, surname = ?, email = ?,  login = ?, password = ?" +
                " WHERE personId = ?;";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, person.getName());
        ps.setString(2, person.getSurname());
        ps.setString(3, person.getEmail());
        ps.setString(4, person.getLogin());
        ps.setString(5, person.getPassword());
        ps.setInt(6, person.getId());

        return ps;
    }

    private PreparedStatement createDeletePreparedStatement(Connection con, Account person) throws SQLException {
        String query = "DELETE FROM persons WHERE personId = ?;";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, person.getId());

        return ps;
    }

    private void removeFromApplication(Account person) {
        if (ClassDAO.getInstance().getClass(person) != null) {
            ClassDAO.getInstance().removePerson(person);
        }
        if (person instanceof Student) {
            BillDAO.getInstance().removeAllBills(person);
            PersonalArtifactDAO.getInstance().removeAllPersonalArtifacts(person);
            removePersonWallet(person);
        }

    }

    private void removePersonWallet(Account person) {
        Wallet wallet = WalletDAO.getInstance().getWallet(person);
        WalletDAO.getInstance().remove(wallet);
    }
}
