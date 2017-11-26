package com.codecool.wot.dao;

import com.codecool.wot.model.Account;
import com.codecool.wot.model.SchoolClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ClassDAO {
    private static final ClassDAO INSTANCE = new ClassDAO();

    private List<SchoolClass> classes;

    private ClassDAO() {
        this.classes = new LinkedList<>();
        loadClassesFromDatabase();
        loadPersonsToClasses();
    }

    public static ClassDAO getInstance() {
        return INSTANCE;
    }

    public void add(SchoolClass schoolClass) {
        try {
            addClassToDatabase(schoolClass);
            this.classes.add(schoolClass);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public SchoolClass getClass(Integer classId) {
        SchoolClass schoolClass = null;
        for (SchoolClass candidate : this.classes) {
            if (candidate.getId().equals(classId)) {
                schoolClass = candidate;
            }
        }
        return schoolClass;
    }

    public SchoolClass getClass(String className) {
        SchoolClass schoolClass = null;
        for (SchoolClass candidate : this.classes) {
            if (candidate.getName().equals(className)) {
                schoolClass = candidate;
            }
        }
        return schoolClass;
    }

    public void addPerson(SchoolClass schoolClass, Account person) {
        try {
            addPersonToClassInDatabase(schoolClass, person);
            schoolClass.assignPerson(person);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void removePerson(SchoolClass schoolClass, Account person) {
        try {
            removePersonFromClassInDatabase(schoolClass, person);
            schoolClass.removePerson(person);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void loadClassesFromDatabase() {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createSelectPreparedStatement(con);
             ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                Integer classId = result.getInt("classId");
                String name = result.getString("name");

                classes.add(new SchoolClass(classId, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void loadPersonsToClasses() {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createSelectPersonsPreparedStatement(con);
             ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                Integer classId = result.getInt("classId");
                Integer personId = result.getInt("personId");

                SchoolClass schoolClass = getClass(classId);
                Account person = PersonDAO.getInstance().getPerson(personId);
                schoolClass.assignPerson(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void addClassToDatabase(SchoolClass schoolClass) throws SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createAddPreparedStatement(con, schoolClass)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();
        }
    }

    private void addPersonToClassInDatabase(SchoolClass schoolClass, Account person) throws SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createAddPersonPreparedStatement(con, schoolClass, person)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();
        }
    }

    private void removePersonFromClassInDatabase(SchoolClass schoolClass, Account person) throws SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createDeletePersonPreparedStatement(con, schoolClass, person)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();
        }
    }

    private PreparedStatement createSelectPreparedStatement(Connection con) throws SQLException {
        String query = "SELECT * FROM classes;";
        PreparedStatement ps = con.prepareStatement(query);

        return ps;
    }

    private PreparedStatement createSelectPersonsPreparedStatement(Connection con) throws SQLException {
        String query = "SELECT * FROM persons_classes;";
        PreparedStatement ps = con.prepareStatement(query);

        return ps;
    }

    private PreparedStatement createAddPreparedStatement(Connection con, SchoolClass schoolClass) throws SQLException {
        String query = "INSERT INTO classes (name) VALUES (?);";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, schoolClass.getName());

        return ps;
    }

    private PreparedStatement createAddPersonPreparedStatement(Connection con, SchoolClass schoolClass, Account person) throws SQLException {
        String query = "INSERT INTO persons_classes (personId, classId) VALUES (?, ?);";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, person.getId());
        ps.setInt(2, schoolClass.getId());

        return ps;
    }

    private PreparedStatement createDeletePersonPreparedStatement(Connection con, SchoolClass schoolClass, Account person) throws SQLException {
        String query = "DELETE FROM persons_classes WHERE personId = ? AND classId = ?;";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, person.getId());
        ps.setInt(2, schoolClass.getId());

        return ps;
    }
}
