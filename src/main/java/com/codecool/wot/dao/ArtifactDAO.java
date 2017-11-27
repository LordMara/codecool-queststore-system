package com.codecool.wot.dao;

import com.codecool.wot.model.Artifact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ArtifactDAO {
    private static final ArtifactDAO INSTANCE = new ArtifactDAO();

    private List<Artifact> artifacts;

    private ArtifactDAO() {
        this.artifacts = new LinkedList<>();
        loadArtifactsFromDatabase();
    }

    public static ArtifactDAO getInstance() {
        return INSTANCE;
    }

    public List<Artifact> read() {
        return this.artifacts;
    }

    public void add(Artifact artifact) {
        try {
            addArtifactToDatabase(artifact);
            this.artifacts.add(artifact);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void update(Artifact artifact) {
        try {
            updateArtifactInDatabase(artifact);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void remove(Artifact artifact) {
        try {
            PersonalArtifactDAO.getInstance().removeAllPersonalArtifacts(artifact);
            deleteArtifactFromDatabase(artifact);
            this.artifacts.remove(artifact);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public Artifact getArtifact(Integer id) {
        Artifact artifact = null;
        for (Artifact candidate : this.artifacts) {
            if (candidate.getId().equals(id)) {
                artifact = candidate;
            }
        }
        return artifact;
    }

    public Artifact getArtifact(String name) {
        Artifact artifact = null;
        for (Artifact candidate : this.artifacts) {
            if (candidate.getName().equals(name)) {
                artifact = candidate;
            }
        }
        return artifact;
    }

    private void loadArtifactsFromDatabase() {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createSelectPreparedStatement(con);
             ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                Integer id = result.getInt("personId");
                String name = result.getString("name");
                String description = result.getString("description");
                Double price = result.getDouble("price");

                artifacts.add(new Artifact(id, name, description, price));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void addArtifactToDatabase(Artifact artifact) throws SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createAddPreparedStatement(con, artifact)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();
        }
    }

    private void updateArtifactInDatabase(Artifact artifact) throws SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createUpdatePreparedStatement(con, artifact)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();
        }
    }

    private void deleteArtifactFromDatabase(Artifact artifact) throws SQLException {
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = createDeletePreparedStatement(con, artifact)) {
            con.setAutoCommit(false);
            ps.executeUpdate();
            con.commit();
        }
    }

    private PreparedStatement createSelectPreparedStatement(Connection con) throws SQLException {
        String query = "SELECT * FROM artifacts;";
        PreparedStatement ps = con.prepareStatement(query);

        return ps;
    }

    private PreparedStatement createAddPreparedStatement(Connection con, Artifact artifact) throws SQLException {
        String query = "INSERT INTO artifacts (name, description, price) VALUES (?, ?, ?);";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, artifact.getName());
        ps.setString(2, artifact.getDescription());
        ps.setDouble(3, artifact.getPrice());

        return ps;
    }

    private PreparedStatement createUpdatePreparedStatement(Connection con, Artifact artifact) throws SQLException {
        String query = "UPDATE artifacts SET name = ?, description = ?, price= ? WHERE artifactId = ?;";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, artifact.getName());
        ps.setString(2, artifact.getDescription());
        ps.setDouble(3, artifact.getPrice());
        ps.setInt(4, artifact.getId());

        return ps;
    }

    private PreparedStatement createDeletePreparedStatement(Connection con, Artifact artifact) throws SQLException {
        String query = "DELETE FROM artifacts WHERE artifactId = ?;";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setInt(1, artifact.getId());

        return ps;
    }
}
