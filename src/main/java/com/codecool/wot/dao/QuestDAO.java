package com.codecool.wot.dao;

import com.codecool.wot.model.Quest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestDAO extends AbstractDAO<Quest, Integer> {

    public QuestDAO(Connection connection) throws SQLException {
        this.connection = connection;
        String loadQuery = "";
        load(loadQuery);
    }


    public void loadObjectsToLocalList(ResultSet rs) throws SQLException{

        while (rs.next()) {

            // code to get Artifact attributes

            objectsList.add(new Quest("", "", (float)0));
        }

    }

    public String updateQuery(Quest quest) {
        return "";
    }

    public boolean getByCondition(Quest quest, Integer id) {
        return false;
    }

    public String insertionQuery(Quest quest) {
        return "";
    }
}
