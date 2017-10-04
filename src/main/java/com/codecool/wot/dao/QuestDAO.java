package com.codecool.wot.dao;

import com.codecool.wot.model.Quest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestDAO extends AbstractDAO<Quest, String> {

    public QuestDAO(Connection connection) throws SQLException {
        this.connection = connection;
        String loadQuery = "";
        load(loadQuery);
    }


    public void loadObjectsToLocalList(ResultSet rs) throws SQLException{

        while (rs.next()) {

            // code to get Artifact attributes

        }

    }

    public String updateQuery(Quest quest) {
        return "";
    }

    public boolean getByCondition(Quest quest, String id) {
        return false;
    }

    public String insertionQuery(String ... args) {
        return "";
    }

    public String getIDFromDBQuery(String name) {
        return "SELECT questId FROM quests WHERE name = " + String.format("'%s';", name);
    }
}
