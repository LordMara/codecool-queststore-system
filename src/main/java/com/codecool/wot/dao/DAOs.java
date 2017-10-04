package com.codecool.wot.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DAOs {
//
//    ArtifactDAO arDAO;
    QuestDAO qDAO;
    ClassDAO cDAO;

    AdminDAO aDAO;
    MentorDAO mDAO;
    StudentDAO sDAO;

    public DAOs(Connection connection) throws SQLException {

//        arDAO = new ArtifactDAO(connection);
//        qDAO = new QuestDAO(connection);
        cDAO = new ClassDAO(connection);
        aDAO = new AdminDAO(connection);
        mDAO = new MentorDAO(connection);
        sDAO = new StudentDAO(connection);

    }

//
//    public ArtifactDAO getArDAO() {
//        return arDAO;
//    }
//
//    public QuestDAO getqDAO() {
//        return qDAO;
//    }

    public ClassDAO getcDAO() {
        return cDAO;
    }

    public AdminDAO getaDAO() {
        return aDAO;
    }

    public MentorDAO getmDAO() {
        return mDAO;
    }

    public StudentDAO getsDAO() {
        return sDAO;
    }
}
