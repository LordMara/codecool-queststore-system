package com.codecool.wot.dao;

import java.sql.Connection;

public class DAOs {

    Connection connection = DatabaseConnection.getDBConnection().getConnection();

    ArtifactDAO arDAO = new ArtifactDAO();
    QuestDAO qDAO = new QuestDAO();
    ClassDAO cDAO = new ClassDAO(connection);

    AdminDAO aDAO = new AdminDAO(connection);
    MentorDAO mDAO = new MentorDAO(connection);
    StudentDAO sDAO = new StudentDAO(connection);

    public DAOs() {

    }
}
