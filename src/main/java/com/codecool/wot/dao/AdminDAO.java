package com.codecool.wot.dao;

import com.codecool.wot.model.Admin;
import java.sql.*;

public class AdminDAO extends AbstractDAO<Admin, String> {


    public AdminDAO(Connection connection) throws SQLException {
        this.connection = connection;
        String loadQuery = "SELECT * FROM persons WHERE role ='administrator'";
        load(loadQuery);

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

    public String updateQuery(Admin admin) {
        return "";
    }

    public boolean getByCondition(Admin admin, String login) {
        return false;
    }

    public String insertionQuery(String ... args) {

        String values = String.format("('%s', 'admin')",String.join("', '", args));
        String query = "INSERT INTO persons (personId, name, surname, email, login, password, role) VALUES " + values;

        return query;
    }

    public String getIDFromDBQuery(String login) {
        return "SELECT personId FROM persons WHERE login = " + String.format("'%s';", login);
    }

}

