package com.codecool.wot.dao;

import com.codecool.wot.model.Artifact;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

public class ArtifactDAO extends AbstractDAO<Artifact, String> {

    public ArtifactDAO(Connection connection) throws SQLException{
        this.connection = connection;
        String loadQuery = "";
        load(loadQuery);
        }

    public void loadObjectsToLocalList(ResultSet rs) throws SQLException{

        while (rs.next()) {

            // code to get Artifact attributes

            objectsList.add(new Artifact());
        }

    }

    public String updateQuery(Artifact artifact) {
        return "";
    }

    public boolean getByCondition(Artifact artifact, String id) {
        return false;
    }

    public String insertionQuery(String ... args) {
        return "";
    }

    public String getIDFromDBQuery(String name) {
        return "SELECT artifactId FROM artifacts WHERE name = " + String.format("'%s';", name);
    }
}
