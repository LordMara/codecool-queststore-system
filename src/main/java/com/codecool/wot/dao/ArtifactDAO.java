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

    private PreparedStatement createSelectPreparedStatement(Connection con) throws SQLException {
        String query = "SELECT * FROM artifacts;";
        PreparedStatement ps = con.prepareStatement(query);

        return ps;
    }
}
