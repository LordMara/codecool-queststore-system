package com.codecool.wot.dao;

import com.codecool.wot.model.Admin;
import java.sql.*;

public class AdminDAO extends AbstractCodecoolerDAO<Admin> {


    public AdminDAO(Connection connection) throws SQLException {
        this.connection = connection;
        load("SELECT * FROM persons WHERE role ='administrator'");

    }

    public void loadObjectsToLocalList(ResultSet rs) throws SQLException{

        while (rs.next()) {

            String Id = rs.getString("personId");
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String email = rs.getString("email");
            String login = rs.getString("login");
            String password = rs.getString("password");


            objectsList.add(new Admin(name, surname, email, login, password, Id));
        }

    }

}

