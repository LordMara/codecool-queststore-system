package com.codecool.wot.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DAOs {

    Connection connection = DatabaseConnection.getDBConnection().getConnection();

    ArtifactDAO arDAO;
    QuestDAO qDAO;
    ClassDAO cDAO;

    AdminDAO aDAO;
    MentorDAO mDAO;
    StudentDAO sDAO);

    public DAOs() throws SQLException {

        arDAO = new ArtifactDAO(connection);
        qDAO = new QuestDAO(connection);
        cDAO = new ClassDAO(connection);

        aDAO = new AdminDAO(connection);
        mDAO = new MentorDAO(connection);
        sDAO = new StudentDAO(connection);

    }
}
