package com.codecool.wot.dao;

import com.codecool.wot.model.Artifact;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

public class ArtifactDAO extends AbstractDAO<Artifact, Integer> {

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

    public String updateQuery(Artifact artifact) {}

    public boolean getByCondition(Artifact artifact, Integer login) {}

    public String insertionQuery(Artifact artifact) {}
}
