package com.codecool.wot.dao;

import com.codecool.wot.model.SchoolClass;

import java.sql.*;

public class ClassDAO extends AbstractDAO<SchoolClass, String> {

    public ClassDAO(Connection connection) throws SQLException {

        this.connection = connection;
        String loadQuery = "SELECT * FROM classes";
        load(loadQuery);
    }

    public void loadObjectsToLocalList(ResultSet rs) throws SQLException{

        while (rs.next()) {

            String classId = rs.getString("classId");
            String name = rs.getString("name");

            objectsList.add(new SchoolClass(classId, name));
        }

    }

    public String updateQuery(SchoolClass schoolClass) {
        return String.format("UPDATE classes SET name = '%s' WHERE classId = %d ;", schoolClass.getName(), schoolClass.getId());
    }

    public boolean getByCondition(SchoolClass schoolClass, String name) {
        return (schoolClass.getName().equals(name));
    }

    public String insertionQuery(String ... args) {

        String values = String.format("('%s')",String.join("', '", args));
        return "INSERT INTO classes (classId, name) VALUES " + values;
    }


}
