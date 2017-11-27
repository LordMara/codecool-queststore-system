package com.codecool.wot.dao;

import com.codecool.wot.model.Account;
import com.codecool.wot.model.Artifact;
import com.codecool.wot.model.PersonalArtifact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

public class PersonalArtifactDAO {
    private static final PersonalArtifactDAO INSTANCE = new PersonalArtifactDAO();

    private List<PersonalArtifact> personalArtifacts;

    private PersonalArtifactDAO() {
        this.personalArtifacts = new LinkedList<>();
        loadPersonalArtifactFromDatabase();
    }

    public static PersonalArtifactDAO getInstance() {
        return INSTANCE;
    }

    public List<PersonalArtifact> read() {
        return this.personalArtifacts;
    }

    public void add(PersonalArtifact personalArtifact) {
        try {
            addPersonalArtifactToDatabase(personalArtifact);
            this.personalArtifacts.add(personalArtifact);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void update(PersonalArtifact personalArtifact) {
        try {
            updatePersonalArtifactInDatabase(personalArtifact);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public PersonalArtifact getPersonalArtifact(Integer id) {
        PersonalArtifact personalArtifact = null;
        for (PersonalArtifact candidate : this.personalArtifacts) {
            if (candidate.getId().equals(id)) {
                personalArtifact = candidate;
            }
        }
        return personalArtifact;
    }

    public PersonalArtifact getPersonalArtifact(Account person, Artifact artifact) {
        PersonalArtifact personalArtifact = null;
        for (PersonalArtifact candidate : this.personalArtifacts) {
            if (candidate.getPerson().equals(person) && candidate.getArtifact().equals(artifact)) {
                personalArtifact = candidate;
            }
        }
        return personalArtifact;
    }

    public List<PersonalArtifact> getPersonalArtifacts(Account person) {
        List<PersonalArtifact> personalArtifactsList = new LinkedList<>();
        for (PersonalArtifact candidate : this.personalArtifacts) {
            if (candidate.getPerson().equals(person)) {
                personalArtifactsList.add(candidate);
            }
        }
        return personalArtifactsList;
    }

    private void loadPersonalArtifactFromDatabase() {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createSelectPreparedStatement(con);
             ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                Integer personalArtifactId = result.getInt("personal_artifacts_id");
                Integer personId = result.getInt("personId");
                Integer artifactId = result.getInt("artifactId");
                String status = result.getString("status");
                String purchaseDate = result.getString("purchase_date");

                this.personalArtifacts.add(new PersonalArtifact(personalArtifactId, personId,
                        artifactId, status, purchaseDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void addPersonalArtifactToDatabase(PersonalArtifact personalArtifact) throws SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createAddPreparedStatement(con, personalArtifact)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();
        }
    }

    private void updatePersonalArtifactInDatabase(PersonalArtifact personalArtifact) throws SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createUpdatePreparedStatement(con, personalArtifact)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();
        }
    }

    private PreparedStatement createSelectPreparedStatement(Connection con) throws SQLException {
        String query = "SELECT * FROM persons_artifacts;";
        PreparedStatement ps = con.prepareStatement(query);

        return ps;
    }

    private PreparedStatement createAddPreparedStatement(Connection con, PersonalArtifact personalArtifact) throws SQLException {
        String query = "INSERT INTO persons_artifacts (personId, artifactId, status, purchase_date)" +
                " VALUES (?, ?, ?, ?);";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, personalArtifact.getPerson().getId());
        ps.setInt(2, personalArtifact.getArtifact().getId());
        ps.setString(3, personalArtifact.parseStatus());
        ps.setString(4, personalArtifact.parseDate());

        return ps;
    }

    private PreparedStatement createUpdatePreparedStatement(Connection con, PersonalArtifact personalArtifact) throws SQLException {
        String query = "UPDATE persons_artifacts SET personId = ?, artifactId = ?, status = ?,  purchase_date = ?" +
                " WHERE personal_artifacts_id = ?;";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, personalArtifact.getPerson().getId());
        ps.setInt(2, personalArtifact.getArtifact().getId());
        ps.setString(3, personalArtifact.parseStatus());
        ps.setString(4, personalArtifact.parseDate());
        ps.setInt(5, personalArtifact.getId());

        return ps;
    }
}
